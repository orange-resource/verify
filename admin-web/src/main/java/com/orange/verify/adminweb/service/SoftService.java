package com.orange.verify.adminweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.common.convert.ConvertObject;
import com.orange.verify.adminweb.mapper.SoftMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.constant.SoftServiceStatusConstant;
import com.orange.verify.api.entity.po.SoftPO;
import com.orange.verify.api.entity.vo.admin.SoftPageResultVO;
import com.orange.verify.api.entity.vo.open.SoftGetSoftDescResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SoftService extends ServiceImpl<SoftMapper, SoftPO> {

    @Autowired
    private ConvertObject convertObject;

    public Response page(String softName, Integer offset, Integer limit) {
        Page<Object> page = new Page<>(offset, limit);
        List<SoftPageResultVO> softPageResultVOList = super.baseMapper.selectListByPage(softName, page);

        Map<String, Object> build = ResultBuilder
                .create()
                .setList(softPageResultVOList)
                .setTotal(page.getTotal())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response getList() {
        List<SoftPO> softPOList = super.baseMapper.selectListBySort();
        Map<String, Object> build = ResultBuilder
                .create()
                .setList(softPOList)
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

    public Response getDetail(String id) {
        SoftPO softPO = super.baseMapper.selectById(id);
        Map<String, Object> build = ResultBuilder
                .create()
                .setDetail(softPO)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response create(SoftPO softPO) {
        int insert = super.baseMapper.insert(softPO);
        return insert > 0 ? Response.success() : Response.error();
    }

    public Response update(SoftPO softPO) {
        int update = super.baseMapper.updateById(softPO);
        return update > 0 ? Response.success() : Response.error();
    }

    public Response delete(String id) {
        int delete = super.baseMapper.deleteById(id);
        return delete > 0 ? Response.success() : Response.error();
    }

    public Response getDetailByOpenApi(String softId) {
        SoftPO softPO = super.baseMapper.selectById(softId);
        if (null == softPO) {
            return Response.build(RspCode.SOFT_NOT_EXIST_WARN);
        } else if (softPO.getServiceStatus().equals(SoftServiceStatusConstant.CLOSE.getType())) {
            return Response.build(RspCode.SOFT_CLOSE_WARN, softPO.getAppCloseMsg());
        }

        SoftGetSoftDescResultVO softGetSoftDescResultVO = convertObject.toVO(softPO);
        Map<String, Object> build = ResultBuilder
                .create()
                .setDetail(softGetSoftDescResultVO)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

}
