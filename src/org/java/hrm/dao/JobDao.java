package org.java.hrm.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.java.hrm.domain.Job;

import java.util.List;

import static org.java.hrm.util.common.HrmConstants.JOBTABLE;

public interface JobDao {

    /**
     * 查询出所有岗位
     * @return
     */
    @Select("SELECT * FROM " + JOBTABLE)
    public List<Job> selectAll();

    /**
     *  根据id查询岗位信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM " + JOBTABLE + " WHERE id=#{id}")
    Job selectById(@Param("id") Integer id);
}
