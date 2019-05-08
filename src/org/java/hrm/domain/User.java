package org.java.hrm.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "登录名不能为空")
    private String loginname;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "[\\w_]{6,16}", message = "密码可以包含字母、下划线，长度在6和16之间")
    private String password;
    @NotNull(message = "用户状态是必填字段")
    private Integer userstatus;

    @NotBlank(message = "角色是必填字段")
    private String role_ids;

    private List<Role> roles;


    private Date createdate;

    public User() {
        super();
    }

    @Override
    public String toString() {
        return this.id + "---" + this.username + "---" + this.loginname + "---" + this.userstatus + "---" + this.createdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getRole_ids() {
        return role_ids;
    }

    public void setRole_ids(String role_ids) {
        this.role_ids = role_ids;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
