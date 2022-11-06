package com.orange.verify.api.entity.vo.open;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class SoftLeaveMessageSubmitParamVO implements Serializable {

    @NotBlank(message = "参数不合法")
    @Size(min = 1,max = 255,message = "参数不合法")
    private String content;

    @NotBlank(message = "参数不合法")
    @Size(min = 1,max = 10,message = "参数不合法")
    private String qq;

    @NotBlank(message = "参数不合法")
    private String softId;

    private String ip;

}
