package org.java.hrm.domain;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer id;
    private String type;
    private String remark;

    public Permission() {
        super();
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
