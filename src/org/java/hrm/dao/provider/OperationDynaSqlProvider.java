package org.java.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import static org.java.hrm.util.common.HrmConstants.USERTABLE;
import static org.java.hrm.util.common.HrmConstants.USERROLETABLE;
import static org.java.hrm.util.common.HrmConstants.ROLETABLE;
import static org.java.hrm.util.common.HrmConstants.ROLEPERMISSIONTABLE;
import static org.java.hrm.util.common.HrmConstants.PERMISSIONTABLE;
import static org.java.hrm.util.common.HrmConstants.PERMISSIONOPERATIONTABLE;
import static org.java.hrm.util.common.HrmConstants.OPERATIONTABLE;

public class OperationDynaSqlProvider {

    public String selectByUid(Integer id){
        String sql = new SQL(){
            {
                SELECT(" u.loginname,u.username,o.name, o.code, o.url ");
                FROM(USERTABLE + " as u ");
                FROM(USERROLETABLE + " as ur ");
                FROM(ROLETABLE + " as r ");
                FROM(ROLEPERMISSIONTABLE + " as rp ");
                FROM(PERMISSIONTABLE + " as p ");
                FROM(PERMISSIONOPERATIONTABLE + " as po ");
                FROM(OPERATIONTABLE + " as o ");
                WHERE(" u.id = ur.userid ");
                WHERE(" ur.roleid = r.id ");
                WHERE(" r.id = rp.roleid ");
                WHERE(" rp.permissionid = p.id ");
                WHERE(" p.id = po.permissionid ");
                WHERE(" po.operationid = o.id ");
                WHERE(" u.id = #{id} ");
            }
        }.toString();
        return sql;
    };
}
