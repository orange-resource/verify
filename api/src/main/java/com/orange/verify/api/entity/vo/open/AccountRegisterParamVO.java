package com.orange.verify.api.entity.vo.open;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class AccountRegisterParamVO implements Serializable {

    @NotBlank(message = "参数不合法")
    @Size(min = 5,max = 10,message = "参数不合法")
    private String username;

    @NotBlank(message = "参数不合法")
    @Size(min = 1,max = 10,message = "参数不合法")
    private String qq;

    @NotBlank(message = "参数不合法")
    private String password;

    @NotBlank(message = "参数不合法")
    private String softId;

    @NotBlank(message = "参数不合法")
    @Size(min = 5,max = 10,message = "参数不合法")
    private String securityCode;

    /**
     * 用户电脑的机器码
     * code
     */
    @NotBlank(message = "参数不合法")
    private String code;
    
    /**
     * 验证码
     */
    @NotBlank(message = "参数不合法")
    @Size(min = 6,max = 6,message = "参数不合法")
    private String vc;

    /**
     * 用户的真实姓名
     * name
     */
    @NotBlank(message = "参数不合法")
    @Size(min = 1,max = 10,message = "参数不合法")
    private String name;

    private String ip;

    @NotBlank(message = "参数不合法")
    String publicKey;

}
