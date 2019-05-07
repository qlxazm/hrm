package org.java.hrm.domain;

import java.io.Serializable;

public class Operation implements Serializable {
    private Integer id;
    private String name;
    private String code;
    private String url;
    private Integer pid;

    public Operation(){super();}

    @Override
    public String toString() {
        return this.id + "---" + this.name + "---" + this.code + "---" + this.url + "---" + this.pid;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
