package org.java.hrm.domain;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
public class Dept implements Serializable {
    private Integer id;
    @NotBlank(message = "部门名称是必填字段")
    private String name;
    private String remark;
    private int employeeNum; // 当前部门下员工的数量

    public Dept() {
        super();
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    @Override
    public String toString() {
        return this.id + "---" + this.name + "---" + this.remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
