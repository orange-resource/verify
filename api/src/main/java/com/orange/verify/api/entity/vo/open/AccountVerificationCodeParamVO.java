package com.orange.verify.api.entity.vo.open;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AccountVerificationCodeParamVO implements Serializable {

    private String publicKey;

    private String code;

}
