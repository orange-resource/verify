package com.orange.verify.adminweb.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.mapper.AdminUserMapper;
import com.orange.verify.api.common.constant.RedisKeyConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.po.AdminUserPO;
import com.orange.verify.api.util.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AdminUserService extends ServiceImpl<AdminUserMapper, AdminUserPO> {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    public Response login(AdminUserPO adminUserPO) {
        int count = super.baseMapper.selectCountByUsernameAndPassword(
                adminUserPO.getUsername(),
                adminUserPO.getPassword()
        );
        if (count > 0) {
            String token = IdUtil.fastSimpleUUID() + IdUtil.objectId();
            redisTemplateUtil.set(RedisKeyConstant.USER_TOKEN + token, token, 1, TimeUnit.DAYS);

            Map<String, Object> build = ResultBuilder
                    .create()
                    .setToken(token)
                    .build();
            return Response.build(RspCode.LOGIN_SUCCESS, build);
        } else {
            return Response.build(RspCode.LOGIN_PASSWORD_WARN);
        }
    }

    public Response logout(String token) {
        redisTemplateUtil.delete(RedisKeyConstant.USER_TOKEN + token);
        return Response.build(RspCode.LOGOUT_SUCCESS);
    }

}
