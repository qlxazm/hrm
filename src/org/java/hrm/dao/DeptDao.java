package org.java.hrm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.java.hrm.dao.provider.DeptDynaSqlProvider;
import org.java.hrm.domain.Dept;

import java.util.List;
import java.util.Map;
import static org.java.hrm.util.common.HrmConstants.DEPTTABLE;

public interface DeptDao {

    /**
     * 分页查询部门信息
     * @param params
     * @return
     */
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "selectWithParam")
    List<Dept> selectByPage(Map<String, Object> params);

    /**
     * 计算部门总数
     * @param params
     * @return
     */
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "count")
    int count(Map<String, Object> params);

    @Delete("DELETE FROM " + DEPTTABLE + " WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);
}
