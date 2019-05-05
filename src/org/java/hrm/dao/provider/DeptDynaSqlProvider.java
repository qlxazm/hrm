package org.java.hrm.dao.provider;

import org.apache.ibatis.annotations.Insert;
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

    /**
     * 插入部门
     * @param dept
     * @return
     */
    public String insertDept(Dept dept){
        String sql = new SQL(){
            {
                INSERT_INTO(DEPTTABLE);
                if (dept.getName() != null && !dept.getName().equals("")) {
                    VALUES("name", "#{name}");
                }
                if (dept.getRemark() != null && !dept.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
        return sql;
    }

    /**
     * 更新dept
     * @param dept
     * @return
     */
    public String updateDept(Dept dept) {
        String sql = new SQL(){
            {
                UPDATE(DEPTTABLE);
                if (dept.getName() != null) {
                    SET(" name = #{name} ");
                }
                if (dept.getRemark() != null) {
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
        return sql;
    }
}
