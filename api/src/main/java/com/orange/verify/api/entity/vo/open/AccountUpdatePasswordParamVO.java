package com.orange.verify.api.entity.vo.open;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class AccountUpdatePasswordParamVO implements Serializable {

    @NotBlank(message = "参数不合法")
    @Size(min = 5,max = 10,message = "参数不合法")
    private String username;

    @NotBlank(message = "参数不合法")
    @Size(min = 5,max = 10,message = "参数不合法")
    private String password;

    @NotBlank(message = "参数不合法")
    @Size(min = 5,max = 10,message = "参数不合法")
    private String securityCode;

    @NotBlank(message = "参数不合法")
    private String softId;

}
