package org.java.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.java.hrm.domain.Dept;

import java.util.Map;

import static org.java.hrm.util.common.HrmConstants.DEPTTABLE;

public class DeptDynaSqlProvider {

    /**
     * 部门的分页查询
     * @param params
     * @return
     */
    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(DEPTTABLE);
                if (params.get("dept") != null){
                    Dept dept = (Dept)params.get("dept");
                    if (dept.getName() != null && !dept.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " LIMIT #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 查询符合条件的部门数
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL(){
            {
                SELECT("count(*)");
                FROM(DEPTTABLE);
                if (params.get("dept") != null){
                    Dept dept = (Dept)params.get("dept");
                    if (dept.getName() != null && !dept.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
        return sql;
    }
}
