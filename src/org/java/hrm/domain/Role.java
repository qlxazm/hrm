package org.java.hrm.domain;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private Integer id;

    @NotBlank(message = "角色名称不能为空！")
    private String name;

    private List<String> permissionIds;

    public Role() { super(); }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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

    public List<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<String> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
