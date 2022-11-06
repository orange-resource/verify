package com.orange.verify.adminweb.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.mapper.CardMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.constant.CardSellStatusConstant;
import com.orange.verify.api.entity.po.CardPO;
import com.orange.verify.api.entity.vo.admin.CardPageParamVO;
import com.orange.verify.api.entity.vo.admin.CardPageResultVO;
import com.orange.verify.api.entity.vo.open.CardTimeLimitResultVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CardService extends ServiceImpl<CardMapper, CardPO> {

    public Response page(CardPageParamVO cardPageParamVO) {
        Page<Object> page = new Page<>(cardPageParamVO.getOffset(), cardPageParamVO.getLimit());
        List<CardPageResultVO> cardPageResultVOList = super.baseMapper.page(cardPageParamVO, page);
        Map<String, Object> build = ResultBuilder
                .create()
                .setList(cardPageResultVOList)
                .setTotal(page.getTotal())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response getCount() {
        int count = super.baseMapper.selectCount();
        Map<String, Object> build = ResultBuilder
                .create()
                .setCount(count)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response batchCreate(String cardTypeId, Integer count) {
        if (count > 100) {
            return Response.build(RspCode.PRODUCTION_TOO_MUCH_WARN);
        }

        for (int i = 0; i < count; i++) {
            CardPO cardPO = new CardPO();
            cardPO.setCardTypeId(cardTypeId);
            cardPO.setNumber(IdUtil.simpleUUID());
            super.baseMapper.insert(cardPO);
        }
        return Response.success();
    }

    public Response close(String cardId, Integer closeStatus) {
        CardPO cardPO = new CardPO();
        cardPO.setId(cardId);
        cardPO.setCloseStatus(closeStatus);
        int update = super.baseMapper.updateById(cardPO);
        return update > 0 ? Response.success() : Response.error();
    }

    public Response sell(String cardId, Integer sellStatus) {
        CardPO cardPO = new CardPO();
        cardPO.setId(cardId);
        cardPO.setSellStatus(sellStatus);
        int update = super.baseMapper.updateById(cardPO);
        return update > 0 ? Response.success() : Response.error();
    }

    public Response batchSell(List<String> cardIdList) {
        for (String cardId : cardIdList) {
            CardPO cardPO = new CardPO();
            cardPO.setId(cardId);
            cardPO.setSellStatus(CardSellStatusConstant.YES.getType());
            super.baseMapper.updateById(cardPO);
        }
        return Response.success();
    }

    public Response batchDelete(List<String> cardIdList) {
        for (String cardId : cardIdList) {
            super.baseMapper.deleteById(cardId);
        }
        return Response.success();
    }

    public Response delete(String cardId) {
        int delete = super.baseMapper.deleteById(cardId);
        return delete > 0 ? Response.success() : Response.error();
    }

    public Response getCardTimeLimit(String username, String password, String softId) {
        CardTimeLimitResultVO cardTimeLimitResultVO =
                super.baseMapper.selectCardTimeLimit(username, password, softId);
        if (null == cardTimeLimitResultVO) {
            return Response.build(RspCode.CARD_NOT_EXIST_WARN);
        }
        return Response.build(RspCode.QUERY_SUCCESS, cardTimeLimitResultVO);
    }

}
