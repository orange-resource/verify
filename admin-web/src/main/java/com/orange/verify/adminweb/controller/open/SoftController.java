package com.orange.verify.adminweb.controller.open;

import com.orange.verify.adminweb.service.SoftService;
import com.orange.verify.api.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@Controller(value = "SoftControllerOpen")
@RequestMapping(value = "/open/soft", produces = "application/json")
public class SoftController {

    @Autowired
    private SoftService softService;

    @PostMapping(value = "/getDetail")
    @ResponseBody
    public Response getDetail(@Valid @NotBlank(message = "参数不合法") String softId) {
        return softService.getDetailByOpenApi(softId);
    }


}
