package com.orange.verify.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 软件版本控制
 * t_soft_version
 */
@TableName("t_soft_version")
@KeySequence("SEQ_TEST")
public class SoftVersionPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "create_at", fill = FieldFill.INSERT)
    private Date createAt;

    @TableField(value = "update_at", fill = FieldFill.UPDATE)
    private Date updateAt;

    /**
     * 是否强制更新 1.不强制 2.强制
     * forced_status
     */
    private Integer forcedStatus;

    /**
     * 版本号
     * number
     */
    private String number;

    /**
     * 更新url
     * update_url
     */
    private String updateUrl;

    /**
     * 软件绑定id
     * soft_id
     */
    private String softId;

    /**
     * 更新公告
     * notice
     */
    private String notice;

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

    public Integer getForcedStatus() {
        return forcedStatus;
    }

    public void setForcedStatus(Integer forcedStatus) {
        this.forcedStatus = forcedStatus;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getSoftId() {
        return softId;
    }

    public void setSoftId(String softId) {
        this.softId = softId;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}