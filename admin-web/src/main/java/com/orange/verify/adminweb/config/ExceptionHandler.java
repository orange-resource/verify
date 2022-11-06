package com.orange.verify.adminweb.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.orange.verify.api.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 异常捕获 加 请求日志记录
 * @author orange
 */
@Slf4j
@Aspect
@Component
public class ExceptionHandler {

	@Value("${app.interface-log:true}")
	private Boolean interfaceLog;

    @Pointcut("execution(* com.orange.verify.adminweb.controller.*.*.*(..))")
    private void allMethod() {
    }

    @Around("allMethod()")
    public Object doAround(ProceedingJoinPoint call) throws Throwable {

		try {
			Object result = call.proceed();

			if (interfaceLog) {
				StringBuilder basicBuilder = getBasicBuilder(call);
				basicBuilder.append("请求返回结果: ");
				basicBuilder.append(GsonUtil.buildGson().toJson(result));
				log.info(basicBuilder.toString());
			}

			return result;
        } catch (Throwable e) {
			StringBuilder basicBuilder = getBasicBuilder(call);
			basicBuilder.append("项目异常: ");
			basicBuilder.append("class: " + e.getClass().getName());
			basicBuilder.append("\n");
			basicBuilder.append(ExceptionUtil.stacktraceToString(e));
			log.error(basicBuilder.toString());
			throw e;
        }
	}

	private StringBuilder getBasicBuilder(ProceedingJoinPoint call) {
		MethodSignature signature = (MethodSignature) call.getSignature();
		Method method = signature.getMethod();
		String methodName = method.getName();
		String[] classNameArray = method.getDeclaringClass().getName().split("\\.");
		String className = classNameArray[classNameArray.length - 1];

		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append(">>>>>>>>接口日志<<<<<<<<<");
		builder.append("\n");
		builder.append("请求开始时间: ");
		builder.append(DateUtil.now());
		builder.append("\n");
		builder.append("请求函数: ");
		builder.append(className + "." + methodName);
		builder.append("\n");

		return builder;
    }
}
