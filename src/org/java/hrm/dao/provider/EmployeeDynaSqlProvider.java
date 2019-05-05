package org.java.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.java.hrm.domain.Employee;

import static org.java.hrm.util.common.HrmConstants.EMPLOYEETABLE;
import java.util.Map;

public class EmployeeDynaSqlProvider {

    /**
     * 根据参数查询员工数据
     * @param params
     * @return
     */
    public String selectWithParam(Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(EMPLOYEETABLE);
                if (params.get("employee") != null){
                    Employee employee = (Employee) params.get("employee");
                    if (employee.getSex() != null) {
                        WHERE(" sex LIKE CONCAT('%', #{employee.sex}, '%')");
                    }
                    if (employee.getJob_id() != null) {
                        WHERE(" job_id = #{employee.job_id} ");
                    }
                    if (employee.getName() != null && !employee.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%', #{employee.name}, '%') ");
                    }
                    if (employee.getCard_id() != null) {
                        WHERE(" card_id = #{employee.card_id} ");
                    }
                    if (employee.getPhone() != null && !employee.getPhone().equals("")) {
                        WHERE(" phone LIKE CONCAT('%', #{employee.phone} ,'%') ");
                    }
                    if (employee.getDept_id() != null) {
                        WHERE(" dept_id = #{employee.dept_id} ");
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
     * 根据条件查询员工总数
     * @param params
     * @return
     */
    public String count(Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("count(*)");
                FROM(EMPLOYEETABLE);
                if (params.get("employee") != null){
                    Employee employee = (Employee) params.get("employee");
                    if (employee.getSex() != null) {
                        WHERE(" sex LIKE CONCAT('%', #{employee.sex}, '%')");
                    }
                    if (employee.getJob_id() != null) {
                        WHERE(" job_id = #{employee.job_id} ");
                    }
                    if (employee.getName() != null && !employee.getName().equals("")) {
                        WHERE(" name LIKE CONCAT('%', #{employee.name}, '%') ");
                    }
                    if (employee.getCard_id() != null) {
                        WHERE(" card_id = #{employee.card_id} ");
                    }
                    if (employee.getPhone() != null && !employee.getPhone().equals("")) {
                        WHERE(" phone LIKE CONCAT('%', #{employee.phone} ,'%') ");
                    }
                    if (employee.getDept_id() != null) {
                        WHERE(" dept_id = #{employee.dept_id} ");
                    }
                }
            }
        }.toString();
        return sql;
    }
}
