package com.orange.verify.adminweb.controller;

import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.RspCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class NotFoundController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response notFound() {
        return Response.build(RspCode.NOT_FOUND);
    }

}
