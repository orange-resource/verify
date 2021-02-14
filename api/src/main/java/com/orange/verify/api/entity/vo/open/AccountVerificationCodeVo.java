package com.orange.verify.api.entity.vo.open;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountVerificationCodeVo implements Serializable {

    private String publicKey;

    private String code;

}
