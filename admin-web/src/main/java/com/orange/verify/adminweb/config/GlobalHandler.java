package com.orange.verify.adminweb.config;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.RspCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public Response httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        return Response.build(RspCode.SUBMISSION_AGREEMENT_ERROR);
    }

    @ExceptionHandler(value = MultipartException.class)
    public Response multipartExceptionHandler(MultipartException e) {
        return Response.build(RspCode.CONTENT_IS_TOO_LARGE);
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public Response maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException e) {
        return Response.build(RspCode.CONTENT_IS_TOO_LARGE);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Response httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return Response.build(RspCode.PARAMETER_ERROR);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Response httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return Response.build(RspCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED);
    }

    @ExceptionHandler(value = ServletRequestBindingException.class)
    public Response servletRequestBindingExceptionHandler(ServletRequestBindingException e) {
        return Response.build(RspCode.PARAMETER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return Response.build(RspCode.PARAMETER_ERROR, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(value = InvalidPropertyException.class)
    public Response methodArgumentNotValidExceptionHandler(InvalidPropertyException e) {
        return Response.build(RspCode.PARAMETER_ERROR);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response constraintViolationExceptionHandler(ConstraintViolationException e) {
        return Response.build(RspCode.PARAMETER_ERROR);
    }

    @ExceptionHandler(value = Throwable.class)
    public Response throwableHandler(Throwable e) {
        log.error("\n项目异常：class name: [{}] - stacktrace [{}]", e.getClass().getName(), ExceptionUtil.stacktraceToString(e));
        return Response.build(RspCode.UNKNOWN);
    }

}
