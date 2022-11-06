package com.orange.verify.adminweb.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.common.convert.ConvertObject;
import com.orange.verify.adminweb.common.util.RsaUtil;
import com.orange.verify.adminweb.mapper.*;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.constant.*;
import com.orange.verify.api.entity.po.*;
import com.orange.verify.api.entity.vo.admin.SoftAccountPageResultVO;
import com.orange.verify.api.entity.vo.open.*;
import com.orange.verify.api.util.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SoftAccountService extends ServiceImpl<SoftAccountMapper, SoftAccountPO> {

    @Autowired
    private SoftMapper softMapper;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CardTypeMapper cardTypeMapper;

    @Autowired
    private AccountRegisterLogMapper accountRegisterLogMapper;

    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Autowired
    private BaiduMapService baiduMapService;

    @Autowired
    private ConvertObject convertObject;

    public Response page(String softId, String createIp,String username, Integer offset, Integer limit) {
        Page<Object> page = new Page<>(offset, limit);
        List<SoftAccountPageResultVO> softAccountPageResultVOList =
                super.baseMapper.page(softId, createIp, username, page);
        Map<String, Object> build = ResultBuilder
                .create()
                .setList(softAccountPageResultVOList)
                .setTotal(page.getTotal())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response getCount() {
        int count = super.count();
        Map<String, Object> build = ResultBuilder
                .create()
                .setCount(count)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response blacklist(String id, Integer blacklist) {
        SoftAccountPO softAccountPO = new SoftAccountPO();
        softAccountPO.setId(id);
        softAccountPO.setBlacklist(blacklist);
        int update = super.baseMapper.updateById(softAccountPO);
        return update > 0 ? Response.success() : Response.error();
    }

    public Response delete(String id) {
        int delete = super.baseMapper.deleteById(id);
        return delete > 0 ? Response.success() : Response.error();
    }

    public Response getPublicKey() {
        Map<String, Object> initKey = RsaUtil.initKey();
        String publicKeyToBase64 = RsaUtil.getPublicKeyToBase64(initKey);
        String privateKeyToBase64 = RsaUtil.getPrivateKeyToBase64(initKey);

        redisTemplateUtil.set(publicKeyToBase64, privateKeyToBase64,10, TimeUnit.MINUTES);

        return Response.build(RspCode.QUERY_SUCCESS, publicKeyToBase64);
    }

    public void saveVerificationCode(String publicKey, String code) {
        String privateKey = redisTemplateUtil.get(publicKey, String.class);
        if (!StrUtil.isBlank(privateKey)) {
            redisTemplateUtil.set("vc=" + publicKey, code,10,TimeUnit.MINUTES);
        }
    }

    public Response register(AccountRegisterParamVO accountRegisterParamVO) {
        String privateKey = redisTemplateUtil.get(accountRegisterParamVO.getPublicKey(), String.class);
        if (StrUtil.isBlank(privateKey)) {
            return Response.build(RspCode.KEY_NOT_EXIST_WARN);
        }

        // 验证码不匹配直接返回
        String vc = redisTemplateUtil.get("vc=" + accountRegisterParamVO.getPublicKey(), String.class);
        if (StrUtil.isBlank(vc)) {
            return Response.build(RspCode.KEY_NOT_EXIST_WARN);
        }
        if (!accountRegisterParamVO.getVc().equals(vc)) {
            return Response.build(RspCode.VC_MISMATCHES_WARN);
        }

        int countByUsername = super.baseMapper.selectCountByUsername(accountRegisterParamVO.getUsername());
        if (countByUsername > 0) {
            return Response.build(RspCode.REGISTER_USERNAME_ALREADY_EXIST_WARN);
        }

        SoftPO softPO = softMapper.selectById(accountRegisterParamVO.getSoftId());
        if (null == softPO) {
            return Response.build(RspCode.SOFT_NOT_EXIST_WARN);
        } else if (softPO.getServiceStatus().equals(SoftServiceStatusConstant.CLOSE.getType())) {
            return Response.build(RspCode.SOFT_CLOSE_WARN, softPO.getAppCloseMsg());
        } else if (softPO.getRegisterStatus().equals(SoftRegisterStatusConstant.CLOSE.getType())) {
            return Response.build(RspCode.REGISTER_CLOSE_WARN, softPO.getRegisterCloseMsg());
        }

        // 进行解密 -> password 和 code -> 解密成真实文本
        String password = null;
        String code = null;
        try {
            password = RsaUtil.decodeRsa(accountRegisterParamVO.getPassword(), privateKey);
            code = RsaUtil.decodeRsa(accountRegisterParamVO.getCode(), privateKey);
        } catch (Exception e) {
            return Response.build(RspCode.KEY_ERROR);
        }
        if (StrUtil.hasBlank(password, code)) {
            return Response.build(RspCode.KEY_ERROR);
        } else if (password.length() > 10 || password.length() < 5) {
            return Response.build(RspCode.PASSWORD_LENGTH_WARN);
        }

        // 查询ip信息
        String addressByIp = "";
        try {
            addressByIp = baiduMapService.getIpInfo(accountRegisterParamVO.getIp());
        } catch (Exception e) {
            return Response.build(RspCode.BAIDU_API_ERROR);
        }

        // 进行转型然后插入数据库
        accountRegisterParamVO.setPassword(password);
        accountRegisterParamVO.setCode(code);

        SoftAccountPO softAccountPO = convertObject.fromVo(accountRegisterParamVO);
        softAccountPO.setCreateIpInfo(addressByIp);

        try {
            int insert = super.baseMapper.insert(softAccountPO);
            if (insert > 0) {
                AccountRegisterLogPO accountRegisterLogPO = new AccountRegisterLogPO();
                accountRegisterLogPO.setAccountId(softAccountPO.getId());
                accountRegisterLogPO.setIp(softAccountPO.getCreateIp());
                accountRegisterLogPO.setIpInfo(addressByIp);
                accountRegisterLogPO.setSoftId(softAccountPO.getSoftId());

                accountRegisterLogMapper.insert(accountRegisterLogPO);
                redisTemplateUtil.delete("vc=" + accountRegisterParamVO.getPublicKey());

                return Response.build(RspCode.REGISTER_SUCCESS);
            } else {
                return Response.build(RspCode.REGISTER_ERROR);
            }
        }catch (DuplicateKeyException e) {
            return Response.build(RspCode.ACCOUNT_ALREADY_EXIST_WARN);
        }
    }

    public Response login(AccountLoginParamVO accountLoginParamVO) {
        String privateKey = redisTemplateUtil.get(accountLoginParamVO.getPublicKey(), String.class);
        if (StrUtil.isBlank(privateKey)) {
            return Response.build(RspCode.KEY_NOT_EXIST_WARN);
        }

        SoftPO softPO = softMapper.selectById(accountLoginParamVO.getSoftId());
        if (null == softPO) {
            return Response.build(RspCode.SOFT_NOT_EXIST_WARN);
        } else if (softPO.getServiceStatus().equals(SoftServiceStatusConstant.CLOSE.getType())) {
            return Response.build(RspCode.SOFT_CLOSE_WARN, softPO.getAppCloseMsg());
        }

        int selectCountByUsernameAndSoftId = super.baseMapper.selectCountByUsernameAndSoftId(
                accountLoginParamVO.getUsername(),
                accountLoginParamVO.getSoftId()
        );
        if (0 == selectCountByUsernameAndSoftId) {
            return Response.build(RspCode.USER_NOT_EXIST_WARN);
        }

        // 进行解密 -> password 和 code -> 解密成真实文本
        String password = null;
        String code = null;
        try {
            password = RsaUtil.decodeRsa(accountLoginParamVO.getPassword(), privateKey);
            code = RsaUtil.decodeRsa(accountLoginParamVO.getCode(), privateKey);
        } catch (Exception e) {
            return Response.build(RspCode.KEY_ERROR);
        }
        if (StrUtil.hasBlank(password, code)) {
            return Response.build(RspCode.KEY_ERROR);
        } else if (password.length() > 10 || password.length() < 5) {
            return Response.build(RspCode.PASSWORD_LENGTH_WARN);
        }

        SoftAccountPO softAccountPO = super.baseMapper.selectByUsernameAndSoftIdAndPassword(
                accountLoginParamVO.getUsername(),
                accountLoginParamVO.getSoftId(),
                password
        );
        if (null == softAccountPO) {
            return Response.build(RspCode.LOGIN_PASSWORD_WARN);
        }

        // 只支持单机进行机器码控制打开软件
        if (softPO.getDosingStrategyType().equals(SoftDosingTypeConstant.ONE_MACHINE.getType())) {
            if (!softAccountPO.getMachineCode().equals(code)) {
                return Response.build(RspCode.DO_NOT_SUPPORT_REPLACEMENT_WARN);
            }
        }
        if (softAccountPO.getBlacklist().equals(SoftAccountBlacklistConstant.YES.getType())) {
            return Response.build(RspCode.ACCOUNT_BLACKLIST_WARN);
        }

        if (softPO.getServiceStatus().equals(SoftServiceStatusConstant.CHARGE.getType())) {
            String cardId = softAccountPO.getCardId();
            if (StrUtil.hasBlank(cardId)) {
                return Response.build(RspCode.ACCOUNT_NOT_BOUND_CARD_WARN);
            }
            CardPO cardPO = cardMapper.selectById(cardId);
            if (null == cardPO) {
                return Response.build(RspCode.ACCOUNT_NOT_BOUND_CARD_WARN);
            } else if (cardPO.getCloseStatus().equals(CardCloseStatusConstant.YES.getType())) {
                return Response.build(RspCode.CARD_CLOSURE_WARN);
            }
            long time = DateUtil.date(cardPO.getEndDate()).getTime();
            long totalTime = time - System.currentTimeMillis();
            if (totalTime < 1) {
                return Response.build(RspCode.CARD_PAST_DUE_WARN);
            }
        }

        // 查询ip信息
        String addressByIp = "";
        try {
            addressByIp = baiduMapService.getIpInfo(accountLoginParamVO.getIp());
        } catch (Exception e) {
            return Response.build(RspCode.BAIDU_API_ERROR);
        }

        AccountLoginLogPO accountLoginLogPO = new AccountLoginLogPO();
        accountLoginLogPO.setAccountId(softAccountPO.getId());
        accountLoginLogPO.setIp(accountLoginParamVO.getIp());
        accountLoginLogPO.setIpInfo(addressByIp);
        accountLoginLogPO.setSoftId(accountLoginParamVO.getSoftId());
        accountLoginLogMapper.insert(accountLoginLogPO);

        return Response.build(RspCode.LOGIN_SUCCESS);
    }

    public Response bindingCard(AccountBindingCardParamVO accountBindingCardParamVO) {

        String privateKey = redisTemplateUtil.get(accountBindingCardParamVO.getPublicKey(), String.class);
        if (StrUtil.isBlank(privateKey)) {
            return Response.build(RspCode.KEY_NOT_EXIST_WARN);
        }

        SoftPO softPO = softMapper.selectById(accountBindingCardParamVO.getSoftId());
        if (null == softPO) {
            return Response.build(RspCode.SOFT_NOT_EXIST_WARN);
        } else if (softPO.getServiceStatus().equals(SoftServiceStatusConstant.CLOSE.getType())) {
            return Response.build(RspCode.SOFT_CLOSE_WARN, softPO.getAppCloseMsg());
        } else if (softPO.getServiceStatus().equals(SoftServiceStatusConstant.FREE.getType())) {
            return Response.build(RspCode.SOFT_FREE_WARN);
        }

        int selectCountByUsernameAndSoftId = super.baseMapper.selectCountByUsernameAndSoftId(
                accountBindingCardParamVO.getUsername(),
                accountBindingCardParamVO.getSoftId()
        );
        if (0 == selectCountByUsernameAndSoftId) {
            return Response.build(RspCode.USER_NOT_EXIST_WARN);
        }

        // 进行解密 -> password 和 code -> 解密成真实文本
        String password = null;
        String code = null;
        try {
            password = RsaUtil.decodeRsa(accountBindingCardParamVO.getPassword(), privateKey);
            code = RsaUtil.decodeRsa(accountBindingCardParamVO.getCode(), privateKey);
        } catch (Exception e) {
            return Response.build(RspCode.KEY_ERROR);
        }
        if (StrUtil.hasEmpty(password, code)) {
            return Response.build(RspCode.KEY_ERROR);
        } else if (password.length() > 10 || password.length() < 5) {
            return Response.build(RspCode.PASSWORD_LENGTH_WARN);
        }

        SoftAccountPO softAccountPO = super.baseMapper.selectByUsernameAndSoftIdAndPassword(
                accountBindingCardParamVO.getUsername(),
                accountBindingCardParamVO.getSoftId(),
                accountBindingCardParamVO.getPassword()
        );
        if (null == softAccountPO) {
            return Response.build(RspCode.PASSWORD_ERROR);
        } else if (softAccountPO.getBlacklist().equals(SoftAccountBlacklistConstant.YES.getType())) {
            return Response.build(RspCode.ACCOUNT_BLACKLIST_WARN);
        }

        CardPO cardPO = cardMapper.selectByNumber(accountBindingCardParamVO.getCardNumber());
        if (null == cardPO) {
            return Response.build(RspCode.CARD_NOT_EXIST_WARN);
        } else if (cardPO.getUseStatus().equals(CardUseStatusConstant.YES.getType())) {
            return Response.build(RspCode.CARD_USE_WARN);
        } else if (cardPO.getCloseStatus().equals(CardCloseStatusConstant.YES.getType())) {
            return Response.build(RspCode.CARD_CLOSURE_WARN);
        }

        CardTypePO cardTypePO = cardTypeMapper.selectById(cardPO.getCardTypeId());
        if (null == cardTypePO) {
            return Response.build(RspCode.CARD_NOT_EXIST_WARN);
        } else if (!accountBindingCardParamVO.getSoftId().equals(cardTypePO.getSoftId())) {
            return Response.build(RspCode.SOFT_INCONSISTENCY_WARN);
        }

        // 开始使用时间和结束使用期间
        Date startDatetime = DateUtil.date();
        DateTime endDateTime = null;
        switch (cardTypePO.getUnitType()) {
            case 1:
                endDateTime = DateUtil.offsetMinute(startDatetime, cardTypePO.getValue());
                break;
            case 2:
                endDateTime = DateUtil.offsetHour(startDatetime, cardTypePO.getValue());
                break;
            case 3:
                endDateTime = DateUtil.offsetDay(startDatetime, cardTypePO.getValue());
                break;
            case 4:
                endDateTime = DateUtil.offsetWeek(startDatetime, cardTypePO.getValue());
                break;
            case 5:
                endDateTime = DateUtil.offsetMonth(startDatetime, cardTypePO.getValue());
                break;
            case 6:
                endDateTime = DateUtil.offset(startDatetime, DateField.YEAR, cardTypePO.getValue());
                break;
        }

        CardPO cardUpdate = new CardPO();
        cardUpdate.setId(cardPO.getId());
        cardUpdate.setStartDate(startDatetime);
        cardUpdate.setEndDate(endDateTime);
        cardUpdate.setUseStatus(CardUseStatusConstant.NO.getType());
        cardUpdate.setSellStatus(CardSellStatusConstant.NO.getType());
        cardUpdate.setAccountId(softAccountPO.getId());
        int cardUpdateResult = cardMapper.updateById(cardUpdate);

        SoftAccountPO accountUpdate = new SoftAccountPO();
        accountUpdate.setId(softAccountPO.getId());
        accountUpdate.setCardId(cardPO.getId());
        accountUpdate.setMachineCode(code);
        int accountUpdateResult = super.baseMapper.updateById(accountUpdate);

        if (cardUpdateResult > 0 && accountUpdateResult > 0) {
            return Response.build(RspCode.SUCCESS);
        } else {
            return Response.build(RspCode.BINDING_CARD_ERROR);
        }
    }

    public Response bindingCode(AccountBindingCodeParamVO accountBindingCodeParamVO) {

        String privateKey = redisTemplateUtil.get(accountBindingCodeParamVO.getPublicKey(), String.class);
        if (StrUtil.isBlank(privateKey)) {
            return Response.build(RspCode.KEY_NOT_EXIST_WARN);
        }

        SoftPO softPO = softMapper.selectById(accountBindingCodeParamVO.getSoftId());
        if (null == softPO) {
            return Response.build(RspCode.SOFT_NOT_EXIST_WARN);
        } else if (softPO.getServiceStatus().equals(SoftServiceStatusConstant.CLOSE.getType())) {
            return Response.build(RspCode.SOFT_CLOSE_WARN, softPO.getAppCloseMsg());
        } else if (softPO.getChangeStrategyType().equals(SoftChangeTypeConstant.NO.getType())) {
            return Response.build(RspCode.SOFT_NO_CHANGE_WARN);
        }

        // 判断用户不存在直接返回
        int selectCountByUsernameAndSoftId = super.baseMapper.selectCountByUsernameAndSoftId(
                accountBindingCodeParamVO.getUsername(),
                accountBindingCodeParamVO.getSoftId()
        );
        if (0 == selectCountByUsernameAndSoftId) {
            return Response.build(RspCode.USER_NOT_EXIST_WARN);
        }

        // 进行解密 -> password 和 code -> 解密成真实文本
        String password = null;
        String code = null;
        try {
            password = RsaUtil.decodeRsa(accountBindingCodeParamVO.getPassword(), privateKey);
            code = RsaUtil.decodeRsa(accountBindingCodeParamVO.getCode(), privateKey);
        } catch (Exception e) {
            return Response.build(RspCode.KEY_ERROR);
        }
        if (StrUtil.hasEmpty(password, code)) {
            return Response.build(RspCode.KEY_ERROR);
        } else if (password.length() > 10 || password.length() < 5) {
            return Response.build(RspCode.PASSWORD_LENGTH_WARN);
        }

        SoftAccountPO softAccountPO = super.baseMapper.selectByUsernameAndSoftIdAndPassword(
                accountBindingCodeParamVO.getUsername(),
                accountBindingCodeParamVO.getSoftId(),
                accountBindingCodeParamVO.getPassword()
        );
        if (null == softAccountPO) {
            return Response.build(RspCode.PASSWORD_ERROR);
        } else if (softAccountPO.getBlacklist().equals(SoftAccountBlacklistConstant.YES.getType())) {
            return Response.build(RspCode.ACCOUNT_BLACKLIST_WARN);
        }

        SoftAccountPO accountUpdate = new SoftAccountPO();
        accountUpdate.setId(softAccountPO.getId());
        accountUpdate.setMachineCode(code);
        int updateById = super.baseMapper.updateById(accountUpdate);
        if (updateById > 0) {
            return Response.build(RspCode.SUCCESS);
        } else {
            return Response.build(RspCode.BINDING_CODE_ERROR);
        }
    }

    public Response updatePassword(AccountUpdatePasswordParamVO accountUpdatePasswordParamVO) {

        SoftPO softPO = softMapper.selectById(accountUpdatePasswordParamVO.getSoftId());
        if (null == softPO) {
            return Response.build(RspCode.SOFT_NOT_EXIST_WARN);
        } else if (softPO.getServiceStatus().equals(SoftServiceStatusConstant.CLOSE.getType())) {
            return Response.build(RspCode.SOFT_CLOSE_WARN, softPO.getAppCloseMsg());
        }

        int selectCountByUsernameAndSoftId = super.baseMapper.selectCountByUsernameAndSoftId(
                accountUpdatePasswordParamVO.getUsername(),
                accountUpdatePasswordParamVO.getSoftId()
        );
        if (0 == selectCountByUsernameAndSoftId) {
            return Response.build(RspCode.USER_NOT_EXIST_WARN);
        }

        SoftAccountPO softAccountPO = super.baseMapper.selectByUsernameAndSecurityCode(
                accountUpdatePasswordParamVO.getUsername(),
                accountUpdatePasswordParamVO.getSecurityCode()
        );
        if (null == softAccountPO) {
            return Response.build(RspCode.SECURITY_CODE_ERROR);
        } else if (softAccountPO.getBlacklist().equals(SoftAccountBlacklistConstant.YES.getType())) {
            return Response.build(RspCode.ACCOUNT_BLACKLIST_WARN);
        }

        SoftAccountPO softAccountPOUpdate = new SoftAccountPO();
        softAccountPOUpdate.setId(softAccountPO.getId());
        softAccountPOUpdate.setPassword(accountUpdatePasswordParamVO.getPassword());

        int updatePassword = super.baseMapper.updateById(softAccountPOUpdate);
        if (updatePassword > 0) {
            return Response.build(RspCode.SUCCESS);
        } else {
            return Response.build(RspCode.UPDATE_PASSWORD_ERROR);
        }
    }

}
