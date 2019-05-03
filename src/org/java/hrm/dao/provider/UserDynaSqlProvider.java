package org.java.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.java.hrm.domain.User;

import static org.java.hrm.util.common.HrmConstants.USERTABLE;

import java.util.Map;

public class UserDynaSqlProvider {


    public String selectWithParam(Map<String, Object> params) {
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(USERTABLE);
                if (params.get("user") != null){
                    User user = (User)params.get("user");
                    if (user.getUsername() != null && !user.getUsername().equals("")) {
                        WHERE(" username LIKE CONCAT('%', #{user.username}, '%') ");
                    }
                    if (user.getUserstatus() != null && !user.getUserstatus().equals("")) {
                        WHERE(" userstatus LIKE CONCAT('%', #{user.userstatus}, '%') ");
                    }
                }
            }
        }.toString();

        if (params.get("pageModel") != null) {
            sql += " LIMIT #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        System.out.println("动态sql语句之用户分页查询：" + sql);
        return sql;
    }

    public String count(Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("count(*)");
                FROM(USERTABLE);
                if (params.get("user") != null){
                    User user = (User)params.get("user");
                    if (user.getUsername() != null && !user.getUsername().equals("")) {
                        WHERE(" username LIKE CONCAT('%', #{user.username}, '%') ");
                    }
                    if (user.getUserstatus() != null && !user.getUserstatus().equals("")) {
                        WHERE(" userstatus LIKE CONCAT('%',#{user.userstatus},'%') ");
                    }
                }
            }
        }.toString();
        System.out.println("动态sql语句之查询符合条件的用户总数：" + sql);
        return sql;
    }
}
