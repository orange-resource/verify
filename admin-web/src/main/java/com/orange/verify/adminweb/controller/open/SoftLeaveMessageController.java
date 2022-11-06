package com.orange.verify.adminweb.controller.open;

import com.orange.verify.adminweb.common.util.IpUtil;
import com.orange.verify.adminweb.service.SoftLeaveMessageService;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.entity.vo.open.SoftLeaveMessageSubmitParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Validated
@Controller(value = "SoftLeaveMessageControllerOpen")
@RequestMapping(value = "/open/softLeaveMessage")
public class SoftLeaveMessageController {

    @Autowired
    private SoftLeaveMessageService softLeaveMessageService;

    @PostMapping(value = "/submit")
    @ResponseBody
    public Response submit(@Valid SoftLeaveMessageSubmitParamVO softLeaveMessageSubmitParamVO, BindingResult result, HttpServletRequest request) {
        String ipAddress = IpUtil.getIpAddress(request);
        softLeaveMessageSubmitParamVO.setIp(ipAddress);
        return softLeaveMessageService.submit(softLeaveMessageSubmitParamVO);
    }

}
