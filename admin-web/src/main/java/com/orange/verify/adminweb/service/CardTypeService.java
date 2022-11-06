package com.orange.verify.adminweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.mapper.CardTypeMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.po.CardTypePO;
import com.orange.verify.api.entity.vo.admin.CardTypePageResultVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CardTypeService extends ServiceImpl<CardTypeMapper, CardTypePO> {

    public Response page(String softId, Integer offset, Integer limit) {
        Page<Object> page = new Page<>(offset, limit);
        List<CardTypePageResultVO> cardTypePageResultVOList =
                super.baseMapper.selectByPage(softId, page);

        Map<String, Object> build = ResultBuilder
                .create()
                .setList(cardTypePageResultVOList)
                .setTotal(page.getTotal())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response getDetail(String cardTypeId) {
        CardTypePO cardTypePO = super.baseMapper.selectById(cardTypeId);
        Map<String, Object> build = ResultBuilder.create().setDetail(cardTypePO).build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response create(CardTypePO cardTypePO) {
        int insert = super.baseMapper.insert(cardTypePO);
        return insert > 0 ? Response.success() : Response.error();
    }

    public Response update(CardTypePO cardTypePO) {
        int update = super.baseMapper.updateById(cardTypePO);
        return update > 0 ? Response.success() : Response.error();
    }

    public Response delete(String cardTypeId) {
        int delete = super.baseMapper.deleteById(cardTypeId);
        return delete > 0 ? Response.success() : Response.error();
    }

}
