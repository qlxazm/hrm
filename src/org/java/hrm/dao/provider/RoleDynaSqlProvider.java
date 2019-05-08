package org.java.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import static org.java.hrm.util.common.HrmConstants.ROLETABLE;
import static org.java.hrm.util.common.HrmConstants.USERROLETABLE;

public class RoleDynaSqlProvider {

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
}
