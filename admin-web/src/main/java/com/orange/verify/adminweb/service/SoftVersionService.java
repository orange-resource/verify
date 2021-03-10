package com.orange.verify.adminweb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.mapper.SoftVersionMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.po.SoftVersionPO;
import com.orange.verify.api.entity.vo.open.SoftVersionResultVO;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class SoftVersionService extends ServiceImpl<SoftVersionMapper, SoftVersionPO> {

    public Response getDetail(String softId) {
        SoftVersionPO softVersionPO = super.baseMapper.selectBySoftId(softId);
        Map<String, Object> build = ResultBuilder
                .create()
                .setDetail(softVersionPO)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response create(SoftVersionPO softVersionPO) {
        int insert = super.baseMapper.insert(softVersionPO);
        return insert > 0 ? Response.success() : Response.error();
    }

    public Response update(SoftVersionPO softVersionPO) {
        int update = super.baseMapper.updateById(softVersionPO);
        return update > 0 ? Response.success() : Response.error();
    }

    public Response getVersion(String softId) {
        SoftVersionResultVO softVersionResultVO = super.baseMapper.selectVOBySoftId(softId);
        if (null == softVersionResultVO) {
            return Response.build(RspCode.VERSION_NOT_EXIST_WARN);
        }

        Map<String, Object> build = ResultBuilder
                .create()
                .setDetail(softVersionResultVO)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

}
