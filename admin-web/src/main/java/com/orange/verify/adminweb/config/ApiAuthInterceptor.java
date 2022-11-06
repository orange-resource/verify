package com.orange.verify.adminweb.config;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.orange.verify.adminweb.common.util.IpUtil;
import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.constant.RedisKeyConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.util.GsonUtil;
import com.orange.verify.api.util.RedisTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录拦截器
 * @author orange
 */
@Slf4j
@Component
public class ApiAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            ApiAuth apiAuth =  method.getMethodAnnotation(ApiAuth.class);

            // 没有注解，直接放行
            if (null == apiAuth) return true;

            // 接口关闭了
            if (apiAuth.close()) {
                toJson(response, Response.build(RspCode.INTERFACE_CLOSED));
                return false;
            }

            // 先验证ip
            String[] ip = apiAuth.ip();
            if (ip.length > 0) {
                boolean isPass = false;
                String address = IpUtil.getIpAddress(request);
                for (String ipAddress : ip) {
                    if (ipAddress.equals(address)) {
                        isPass = true;
                        break;
                    }
                }
                if (!isPass) {
                    toJson(response, Response.build(RspCode.VERIFICATION_FAILED));
                    return false;
                }
            }

            // 验证所属平台
            // 管理平台端接口验证拦截
            if (apiAuth.type().equals(ApiAuthConstant.ADMIN_PLATFORM)) {
                return adminPlatformIntercept(request, response, apiAuth);
            }
        }

        return true;
    }

    private boolean adminPlatformIntercept(HttpServletRequest request, HttpServletResponse response, ApiAuth apiAuth) {
        String authorization = request.getHeader(ApiAuthConstant.HEADER_AUTHORIZATION);
        if (StrUtil.isBlank(authorization)) {
            toJson(response, Response.build(RspCode.VERIFICATION_FAILED));
            return false;
        }

        // 验证
        Boolean hasKey = redisTemplateUtil.hasKey(RedisKeyConstant.USER_TOKEN + authorization);
        if (null == hasKey || !hasKey) {
            toJson(response, Response.build(RspCode.LOGIN_EXPIRED));
            return false;
        }

        return true;
    }

    /**
     * 输出json
     */
    private void toJson(HttpServletResponse response, Response res) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.append(GsonUtil.buildGson().toJson(res));
        } catch (IOException e) {
            log.error("登录拦截输出json错误: {}", ExceptionUtil.stacktraceToString(e));
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }

}

