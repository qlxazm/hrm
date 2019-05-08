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

    public String selectByUid(){
        String sql = new SQL(){
            {
                SELECT(" o.* ");
                FROM(USERTABLE + " as u ");
                FROM(USERROLETABLE + " as ur ");
                FROM(ROLETABLE + " as r ");
                FROM(ROLEPERMISSIONTABLE + " as rp ");
                FROM(PERMISSIONTABLE + " as p ");
                FROM(PERMISSIONOPERATIONTABLE + " as po ");
                FROM(OPERATIONTABLE + " as o ");
                WHERE(" u.id = ur.userid ");
                AND();
                WHERE(" ur.roleid = r.id ");
                AND();
                WHERE(" r.id = rp.roleid ");
                AND();
                WHERE(" rp.permissionid = p.id ");
                AND();
                WHERE(" p.id = po.permissionid ");
                AND();
                WHERE(" po.operationid = o.id ");
                AND();
                WHERE(" u.id = #{id} ");
            }
        }.toString();
        return sql;
    };
}
