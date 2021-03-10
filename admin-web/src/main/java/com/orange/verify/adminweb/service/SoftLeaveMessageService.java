package com.orange.verify.adminweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.mapper.SoftLeaveMessageMapper;
import com.orange.verify.adminweb.mapper.SoftMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.constant.SoftServiceStatusConstant;
import com.orange.verify.api.entity.po.SoftLeaveMessagePO;
import com.orange.verify.api.entity.po.SoftPO;
import com.orange.verify.api.entity.vo.admin.SoftLeaveMessagePageResultVO;
import com.orange.verify.api.entity.vo.open.SoftLeaveMessageSubmitParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SoftLeaveMessageService extends ServiceImpl<SoftLeaveMessageMapper, SoftLeaveMessagePO> {

    @Autowired
    private SoftMapper softMapper;

    @Autowired
    private BaiduMapService baiduMapService;

    public Response page(String softId, Integer offset, Integer limit) {
        Page<Object> page = new Page<>(offset, limit);
        List<SoftLeaveMessagePageResultVO> softLeaveMessagePageResultVOList = super.baseMapper.selectListByPage(softId, page);

        Map<String, Object> build = ResultBuilder
                .create()
                .setList(softLeaveMessagePageResultVOList)
                .setTotal(page.getTotal())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response delete(String id) {
        int delete = super.baseMapper.deleteById(id);
        return delete > 0 ? Response.success() : Response.error();
    }

    public Response submit(SoftLeaveMessageSubmitParamVO softLeaveMessageSubmitParamVO) {
        SoftPO softPO = softMapper.selectById(softLeaveMessageSubmitParamVO.getSoftId());
        if (null == softPO) {
            return Response.build(RspCode.SOFT_NOT_EXIST_WARN);
        } else if (softPO.getServiceStatus().equals(SoftServiceStatusConstant.CLOSE.getType())) {
            return Response.build(RspCode.SOFT_CLOSE_WARN, softPO.getAppCloseMsg());
        }

        //查询ip信息
        String addressByIp = "";
        try {
            addressByIp = baiduMapService.getIpInfo(softLeaveMessageSubmitParamVO.getIp());
        } catch (Exception e) {
            return Response.build(RspCode.BAIDU_API_ERROR);
        }

        SoftLeaveMessagePO softLeaveMessagePO = new SoftLeaveMessagePO();
        softLeaveMessagePO.setSoftId(softLeaveMessageSubmitParamVO.getSoftId());
        softLeaveMessagePO.setQq(softLeaveMessageSubmitParamVO.getQq());
        softLeaveMessagePO.setContent(softLeaveMessageSubmitParamVO.getContent());
        softLeaveMessagePO.setIp(softLeaveMessageSubmitParamVO.getIp());
        softLeaveMessagePO.setIpInfo(addressByIp);
        super.baseMapper.insert(softLeaveMessagePO);

        // todo 通知到钉钉...

        return Response.success();
    }
}
