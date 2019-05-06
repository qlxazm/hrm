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


    /**
     * 添加用户
     * @param employee
     * @return
     */
    public String addEmployee(Employee employee){
        String sql = new SQL(){
            {
                INSERT_INTO(EMPLOYEETABLE);
                if(employee.getParty() != null && !employee.getParty().equals("")){
                    VALUES("party", " #{party} ");
                }
                if(employee.getRace() != null && !employee.getRace().equals("")){
                    VALUES("race", " #{race} ");
                }
                if(employee.getEducation() != null && !employee.getEducation().equals("")){
                    VALUES("education", " #{education} ");
                }
                if(employee.getSpeciality() != null && !employee.getSpeciality().equals("")){
                    VALUES("speciality", " #{speciality} ");
                }
                if(employee.getHobby() != null && !employee.getHobby().equals("")){
                    VALUES("hobby", " #{hobby} ");
                }
                if(employee.getRemark() != null && !employee.getRemark().equals("")){
                    VALUES("remark", " #{remark} ");
                }
                /** 生日暂时先不管 */
                /*if(employee.getBirthday() != null){
                    VALUES("birthday", " birthday=#{birthday} ");
                }*/
                if(employee.getPhone() != null && !employee.getPhone().equals("")){
                    VALUES("phone", " #{phone} ");
                }
                if(employee.getQQ_num() != null && !employee.getQQ_num().equals("")){
                    VALUES("QQ_num", " #{QQ_num} ");
                }
                if(employee.getEmail() != null && !employee.getEmail().equals("")){
                    VALUES("email", " #{email} ");
                }
                if(employee.getSex() != null){
                    VALUES("sex", " #{sex} ");
                }
                if (employee.getTel() != null && !employee.getTel().equals("")) {
                    VALUES("tel", " #{tel} ");
                }
                if (employee.getPost_code() != null && !employee.getPost_code().equals("")){
                    VALUES("post_code", "  #{post_code} ");
                }
                if (employee.getAddress() != null && !employee.getAddress().equals("")){
                    VALUES("address", "  #{address} ");
                }
                if (employee.getCard_id() != null && !employee.getCard_id().equals("")){
                    VALUES("card_id", " #{card_id} ");
                }
                if (employee.getName() != null && !employee.getName().equals("")) {
                    VALUES("name", " #{name} ");
                }
                if (employee.getDept_id() != null){
                    VALUES("dept_id", " #{dept_id} ");
                }
                if (employee.getJob_id() != null) {
                    VALUES("job_id", " #{job_id} ");
                }
            }
        }.toString();
        return sql;
    }


    /**
     * 更新员工信息
     * @param employee
     * @return
     */
    public String updateEmployee(Employee employee) {
        String sql = new SQL(){
            {
                UPDATE(EMPLOYEETABLE);
                if(employee.getParty() != null && !employee.getParty().equals("")){
                    SET(" party=#{party} ");
                }
                if(employee.getRace() != null && !employee.getRace().equals("")){
                    SET(" race=#{race} ");
                }
                if(employee.getEducation() != null && !employee.getEducation().equals("")){
                    SET(" education=#{education} ");
                }
                if(employee.getSpeciality() != null && !employee.getSpeciality().equals("")){
                    SET(" speciality= #{speciality} ");
                }
                if(employee.getHobby() != null && !employee.getHobby().equals("")){
                    SET(" hobby= #{hobby} ");
                }
                if(employee.getRemark() != null && !employee.getRemark().equals("")){
                    SET(" remark= #{remark} ");
                }
                /** 生日暂时先不管 */
                /*if(employee.getBirthday() != null){
                    SET("birthday= birthday=#{birthday} ");
                }*/
                if(employee.getPhone() != null && !employee.getPhone().equals("")){
                    SET(" phone= #{phone} ");
                }
                if(employee.getQQ_num() != null && !employee.getQQ_num().equals("")){
                    SET(" QQ_num= #{QQ_num} ");
                }
                if(employee.getEmail() != null && !employee.getEmail().equals("")){
                    SET(" email= #{email} ");
                }
                if(employee.getSex() != null){
                    SET("sex= #{sex} ");
                }
                if (employee.getTel() != null && !employee.getTel().equals("")) {
                    SET(" tel= #{tel} ");
                }
                if (employee.getPost_code() != null && !employee.getPost_code().equals("")){
                    SET(" post_code=  #{post_code} ");
                }
                if (employee.getAddress() != null && !employee.getAddress().equals("")){
                    SET(" address=  #{address} ");
                }
                if (employee.getCard_id() != null && !employee.getCard_id().equals("")){
                    SET(" card_id= #{card_id} ");
                }
                if (employee.getName() != null && !employee.getName().equals("")) {
                    SET(" name= #{name} ");
                }
                if (employee.getDept_id() != null){
                    SET(" dept_id= #{dept_id} ");
                }
                if (employee.getJob_id() != null) {
                    SET(" job_id= #{job_id} ");
                }
                WHERE(" id=#{id} ");
            }
        }.toString();
        return sql;
    }
}
