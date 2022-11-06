package com.orange.verify.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;

/**
 * 百度地理api配置
 * t_baidu_map_token
 */
@TableName("t_baidu_map_token")
@KeySequence("SEQ_TEST")
public class BaiduMapTokenPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * appkey
     * appkey
     */
    private String appkey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
}