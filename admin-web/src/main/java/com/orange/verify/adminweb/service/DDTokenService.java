package com.orange.verify.adminweb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.mapper.DDTokenMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.po.DDTokenPO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DDTokenService extends ServiceImpl<DDTokenMapper, DDTokenPO> {

    public Response getList() {
        List<DDTokenPO> ddTokenPOList = super.baseMapper.selectListBySort();
        Map<String, Object> build = ResultBuilder.create().setList(ddTokenPOList).build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response getDetail(String id) {
        DDTokenPO ddTokenPO = super.baseMapper.selectById(id);
        Map<String, Object> build = ResultBuilder.create().setDetail(ddTokenPO).build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response create(DDTokenPO ddTokenPO) {
        int insert = super.baseMapper.insert(ddTokenPO);
        return insert > 0 ? Response.success() : Response.error();
    }

    public Response update(DDTokenPO ddTokenPO) {
        int update = super.baseMapper.updateById(ddTokenPO);
        return update > 0 ? Response.success() : Response.error();
    }

    public Response delete(String id) {
        int delete = super.baseMapper.deleteById(id);
        return delete > 0 ? Response.success() : Response.error();
    }

}
