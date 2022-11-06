package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.verify.api.entity.po.BaseTableName;
import com.orange.verify.api.entity.po.SoftPO;
import com.orange.verify.api.entity.vo.admin.SoftPageResultVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoftMapper extends BaseMapper<SoftPO>, BaseTableName {

    @Select("<script>" +
            "SELECT " +
            "s.*, " +
            "(SELECT count(*) FROM" + t_soft_account_space + "a where a.soft_id = s.id) as account_total, " +
            "(SELECT sv.number FROM" + t_soft_version_space + "sv WHERE sv.soft_id = s.id) as version_num, " +
            "(SELECT count(*) FROM" + t_soft_leave_message_space + "slm WHERE slm.soft_id = s.id) as leave_message_num " +
            "FROM" + t_soft_space + "s " +
            "where 1=1 " +
            "<if test=\"softName != null and softName != ''\"> and s.name like concat('%',#{softName},'%') </if>" +
            "order by s.create_at desc" +
            "</script>")
    List<SoftPageResultVO> selectListByPage(@Param("softName") String softName, Page page);

    @Select("select * from" + t_soft_space + "order by create_at desc")
    List<SoftPO> selectListBySort();

}