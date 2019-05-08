package org.java.hrm.domain;

import java.io.Serializable;

public class UserRole implements Serializable {
    private Integer userid;
    private Integer roleid;

    public UserRole() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}
