package org.java.hrm.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.java.hrm.dao.provider.OperationDynaSqlProvider;
import org.java.hrm.domain.Operation;

import java.util.List;

public interface OperationDao {

    /**
     * 根据用户id查询出用户可以进行的操作
     * @param id
     * @return
     */
    @SelectProvider(type = OperationDynaSqlProvider.class, method = "selectByUid")
    List<Operation> selectByUid(@Param("id") Integer id);
}
