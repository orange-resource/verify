package com.orange.verify.adminweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.mapper.AccountRegisterLogMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.po.AccountRegisterLogPO;
import com.orange.verify.api.entity.vo.admin.AccountRegisterLogPageResultVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountRegisterLogService extends ServiceImpl<AccountRegisterLogMapper, AccountRegisterLogPO> {

    public Response getBeforeData(String softId) {
        List<String> beforeData = super.baseMapper.selectBeforeData(softId);
        return Response.build(RspCode.QUERY_SUCCESS, beforeData);
    }

    public Response page(String softId, Integer offset, Integer limit) {
        Page<Object> page = new Page<>(offset, limit);
        List<AccountRegisterLogPageResultVO> accountRegisterLogPageResultVOList = super.baseMapper.page(softId, page);
        Map<String, Object> build = ResultBuilder.create().setList(accountRegisterLogPageResultVOList).setTotal(page.getTotal()).build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

}
