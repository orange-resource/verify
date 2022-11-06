package com.orange.verify.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;

/**
 * 软件充值卡类型
 * t_card_type
 */
@TableName("t_card_type")
@KeySequence("SEQ_TEST")
public class CardTypePO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "create_at", fill = FieldFill.INSERT)
    private Long createAt;

    @TableField(value = "update_at", fill = FieldFill.UPDATE)
    private Long updateAt;

    /**
     * 卡类单位 1.分 2.时 3.天 4.周 5.月 6.年
     * unit_type
     */
    private Integer unitType;

    /**
     * 卡类值 比如对应的是分填1就是1分钟 以此类推
     * value
     */
    private Integer value;

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

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getSoftId() {
        return softId;
    }

    public void setSoftId(String softId) {
        this.softId = softId;
    }
}