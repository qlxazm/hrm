package org.java.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.java.hrm.domain.Role;

import java.util.Map;

import static org.java.hrm.util.common.HrmConstants.ROLETABLE;
import static org.java.hrm.util.common.HrmConstants.USERROLETABLE;

public class RoleDynaSqlProvider {

    /**
     * 根据查询参数分页角色
     * @param params
     * @return
     */
    public String selectWithParam(Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(ROLETABLE);
                if (params.get("role") != null) {
                    Role role = (Role) params.get("role");
                    if (role.getName() != null && !role.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%', #{role.name}, '%') ");
                    }
                }
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " LIMIT #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 根据用户id查询角色信息
     * @return
     */
    public String selectWithUserId(){
        String sql = new SQL(){
            {
                SELECT("r.*");
                FROM(USERROLETABLE + " as ur ");
                FROM(ROLETABLE + " as r ");
                WHERE(" ur.userid=#{id} ");
                AND();
                WHERE(" ur.roleid = r.id ");
            }
        }.toString();
        return sql;
    }


    /**
     * 查询符合条件的角色数
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL(){
            {
                SELECT("count(*)");
                FROM(ROLETABLE);
                if (params.get("role") != null) {
                    Role role = (Role) params.get("role");
                    if (role.getName() != null && !role.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%', #{role.name}, '%') ");
                    }
                }
            }
        }.toString();
        return sql;
    }

}
