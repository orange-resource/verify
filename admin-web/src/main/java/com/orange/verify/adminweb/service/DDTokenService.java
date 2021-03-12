package com.orange.verify.adminweb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.mapper.DDTokenMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.po.DDTokenPO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DDTokenService extends ServiceImpl<DDTokenMapper, DDTokenPO> {

    public Response getDetail() {
        DDTokenPO ddTokenPO = super.baseMapper.selectOne();
        Map<String, Object> build = ResultBuilder.create().setDetail(ddTokenPO).build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response update(DDTokenPO ddTokenPO) {
        boolean saveOrUpdate = super.saveOrUpdate(ddTokenPO);
        return saveOrUpdate ? Response.success() : Response.error();
    }

}
