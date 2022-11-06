package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.verify.api.entity.po.BaseTableName;
import com.orange.verify.api.entity.po.SoftLeaveMessagePO;
import com.orange.verify.api.entity.vo.admin.SoftLeaveMessagePageResultVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoftLeaveMessageMapper extends BaseMapper<SoftLeaveMessagePO>, BaseTableName {

    @Select("<script>" +
            "select slm.*,s.name as soft_name from" + t_soft_leave_message_space + "slm " +
            "left join" + t_soft_space + "s " +
            "on slm.soft_id = s.id " +
            "where 1=1 " +
            "<if test=\"softId != null and softId != ''\"> and slm.soft_id = #{softId} </if>" +
            "order by slm.create_at desc" +
            "</script>")
    List<SoftLeaveMessagePageResultVO> selectListByPage(@Param("softId") String softId, Page page);

}