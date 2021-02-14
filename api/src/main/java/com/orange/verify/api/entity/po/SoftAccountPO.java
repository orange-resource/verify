package com.orange.verify.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 软件用户表
 * t_soft_account
 */
@TableName("t_soft_account")
@KeySequence("SEQ_TEST")
public class SoftAccountPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "create_at", fill = FieldFill.INSERT)
    private Date createAt;

    @TableField(value = "update_at", fill = FieldFill.UPDATE)
    private Date updateAt;

    /**
     * 用户名
     * username
     */
    private String username;

    /**
     * 用户密码
     * password
     */
    private String password;

    /**
     * 安全码，找回密码用
     * security_code
     */
    private String securityCode;

    /**
     * 用户注册的时候ip地址
     * create_ip
     */
    private String createIp;

    /**
     * 创建时候的ip信息
     * create_ip_info
     */
    private String createIpInfo;

    /**
     * 是否加入了黑名单 1.不是 2.是
     * blacklist
     */
    private Integer blacklist;

    /**
     * 用户电脑的机器码
     * machine_code
     */
    private String machineCode;

    /**
     * 用户的真实姓名
     * name
     */
    private String name;

    /**
     * 用户的联系手机号
     * phone
     */
    private String phone;

    /**
     * 用户的联系QQ
     * qq
     */
    private String qq;

    /**
     * 卡密绑定id
     * card_id
     */
    private String cardId;

    /**
     * 软件绑定id
     * soft_id
     */
    private String softId;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public String getCreateIpInfo() {
        return createIpInfo;
    }

    public void setCreateIpInfo(String createIpInfo) {
        this.createIpInfo = createIpInfo;
    }

    public Integer getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Integer blacklist) {
        this.blacklist = blacklist;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getSoftId() {
        return softId;
    }

    public void setSoftId(String softId) {
        this.softId = softId;
    }
}