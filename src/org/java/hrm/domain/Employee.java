package org.java.hrm.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {

    @NotBlank(message="用户名不能为空")
    private String name;

    private Integer id;
    private Dept dept;          // 员工关联的部门对象
    private Job job;            // 员工关联的工作对象

    @NotNull(message = "必须选择岗位")
    private Integer job_id;
    @NotNull(message = "必须选择部门")
    private Integer dept_id;

    @NotBlank(message = "身份证号是必填字段")
    @Pattern(regexp = "[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)",message = "无效的身份证号")
    private String card_id;

    @NotBlank(message = "地址是必填字段")
    private String address;

    @Pattern(regexp = "[0-9]{6}", message = "无效的邮编")
    private String post_code;
    private String tel;

    @NotBlank(message = "手机号是必填字段")
    @Pattern(regexp = "1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}", message = "无效的手机号")
    private String phone;

    @Pattern(regexp = "[1-9][0-9]{4,14}", message = "无效的QQ号")
    private String QQ_num;

    @NotBlank(message = "邮箱是必填字段")
    @Email(message = "必须是合法的邮箱地址")
    private String email;

    @NotNull(message = "性别是必填字段")
    private Integer sex;

    private String party;       // 政治面貌

    /*
    * 日期转换
    * */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "生日必须是一个过去的日期")
    //@Pattern(regexp = "(19|20)\\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])", message = "生日的格式必须是 yyyy-mm--dd")
    private Date birthday;

    private String race;        // 民族
    private String education;   // 学历
    private String speciality;  // 特长
    private String hobby;       // 爱好
    private String remark;      // 备注
    private Date create_date;   // 建档日期




    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return this.name + "---" + this.address + "---部门：" + this.dept_id + "---职位：" + this.job_id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQQ_num() {
        return QQ_num;
    }

    public void setQQ_num(String QQ_num) {
        this.QQ_num = QQ_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
