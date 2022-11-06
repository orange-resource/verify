package com.orange.verify.adminweb.controller.open;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import com.orange.verify.adminweb.common.util.IpUtil;
import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.SoftAccountService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.entity.vo.open.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Validated
@Controller(value = "SoftAccountControllerOpen")
@RequestMapping(value = "/open/account")
public class SoftAccountController {

    @Autowired
    private SoftAccountService softAccountService;

    @ApiAuth(type = ApiAuthConstant.OPEN_PLATFORM)
    @RequestMapping(value = "/getPublicKey", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response getPublicKey() {
        return softAccountService.getPublicKey();
    }

    @RequestMapping(value = "/getVerificationCode", method = RequestMethod.GET)
    public void getVerificationCode(String publicKey, HttpServletResponse response) {
        ICaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 6, 20);
        try {
            publicKey = publicKey.replaceAll(" ","+");
            softAccountService.saveVerificationCode(publicKey, captcha.getCode());
            captcha.write(response.getOutputStream());
        } catch (IOException e) {
        }
    }

    @ApiAuth(type = ApiAuthConstant.OPEN_PLATFORM)
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response register(@Valid AccountRegisterParamVO accountRegisterParamVO,
                             BindingResult result,
                             HttpServletRequest request) {

        accountRegisterParamVO.setPublicKey(accountRegisterParamVO.getPublicKey().replaceAll(" ","+"));
        accountRegisterParamVO.setPassword(accountRegisterParamVO.getPassword().replaceAll(" ","+"));
        accountRegisterParamVO.setCode(accountRegisterParamVO.getCode().replaceAll(" ","+"));

        accountRegisterParamVO.setIp(IpUtil.getIpAddress(request));

        return softAccountService.register(accountRegisterParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.OPEN_PLATFORM)
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response login(@Valid AccountLoginParamVO accountLoginParamVO,
                          BindingResult result,
                          HttpServletRequest request) {

        accountLoginParamVO.setPublicKey(accountLoginParamVO.getPublicKey().replaceAll(" ","+"));
        accountLoginParamVO.setPassword(accountLoginParamVO.getPassword().replaceAll(" ","+"));
        accountLoginParamVO.setCode(accountLoginParamVO.getCode().replaceAll(" ","+"));

        accountLoginParamVO.setIp(IpUtil.getIpAddress(request));

        return softAccountService.login(accountLoginParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.OPEN_PLATFORM)
    @RequestMapping(value = "/bindingCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response bindingCard(@Valid AccountBindingCardParamVO accountBindingCardParamVO,
                                BindingResult result) {

        accountBindingCardParamVO.setPublicKey(accountBindingCardParamVO.getPublicKey().replaceAll(" ","+"));
        accountBindingCardParamVO.setPassword(accountBindingCardParamVO.getPassword().replaceAll(" ","+"));
        accountBindingCardParamVO.setCode(accountBindingCardParamVO.getCode().replaceAll(" ","+"));

        return softAccountService.bindingCard(accountBindingCardParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.OPEN_PLATFORM)
    @RequestMapping(value = "/bindingCode", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response bindingCode(@Valid AccountBindingCodeParamVO accountBindingCodeParamVO,
                                BindingResult result) {

        accountBindingCodeParamVO.setPublicKey(accountBindingCodeParamVO.getPublicKey().replaceAll(" ","+"));
        accountBindingCodeParamVO.setPassword(accountBindingCodeParamVO.getPassword().replaceAll(" ","+"));
        accountBindingCodeParamVO.setCode(accountBindingCodeParamVO.getCode().replaceAll(" ","+"));

        return softAccountService.bindingCode(accountBindingCodeParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.OPEN_PLATFORM)
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Response updatePassword(@Valid AccountUpdatePasswordParamVO accountUpdatePasswordParamVO,
                                   BindingResult result) {

        return softAccountService.updatePassword(accountUpdatePasswordParamVO);
    }

}
