package org.java.hrm.dao;

import org.apache.ibatis.annotations.Select;
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
}
