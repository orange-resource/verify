package com.orange.verify.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;

/**
 * 钉钉通知token
 * t_dingding_token
 */
@TableName("t_dingding_token")
@KeySequence("SEQ_TEST")
public class DDTokenPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户名
     * token
     */
    private String token;

    /**
     * 用户密码
     * secret
     */
    private String secret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}