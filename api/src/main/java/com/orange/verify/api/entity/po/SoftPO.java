package com.orange.verify.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 软件
 * t_soft
 */
@TableName("t_soft")
@KeySequence("SEQ_TEST")
public class SoftPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "create_at", fill = FieldFill.INSERT)
    private Date createAt;

    @TableField(value = "update_at", fill = FieldFill.UPDATE)
    private Date updateAt;

    /**
     * 换绑策略 1.支持换绑定 2.不支持换绑定
     * change_strategy_type
     */
    private Integer changeStrategyType;

    /**
     * 多开策略 1.只支持单机 2.无限制
     * dosing_strategy_type
     */
    private Integer dosingStrategyType;

    /**
     * 软件名称
     * name
     */
    private String name;

    /**
     * 注册状态 1.开放注册 2.关闭注册
     * register_status
     */
    private Integer registerStatus;

    /**
     * 服务状态 1.收费 2.免费开放 3.关闭开放使用
     * service_status
     */
    private Integer serviceStatus;

    /**
     * 公告
     * notice
     */
    private String notice;

    /**
     * 关闭注册后的返回信息
     * register_close_msg
     */
    private String registerCloseMsg;

    /**
     * 应用关闭状态下的返回信息
     * app_close_msg
     */
    private String appCloseMsg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getChangeStrategyType() {
        return changeStrategyType;
    }

    public void setChangeStrategyType(Integer changeStrategyType) {
        this.changeStrategyType = changeStrategyType;
    }

    public Integer getDosingStrategyType() {
        return dosingStrategyType;
    }

    public void setDosingStrategyType(Integer dosingStrategyType) {
        this.dosingStrategyType = dosingStrategyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getRegisterCloseMsg() {
        return registerCloseMsg;
    }

    public void setRegisterCloseMsg(String registerCloseMsg) {
        this.registerCloseMsg = registerCloseMsg;
    }

    public String getAppCloseMsg() {
        return appCloseMsg;
    }

    public void setAppCloseMsg(String appCloseMsg) {
        this.appCloseMsg = appCloseMsg;
    }
}