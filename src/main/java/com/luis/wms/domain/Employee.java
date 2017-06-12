package com.luis.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Employee extends BaseDomain {

    private String name;
    private String password;
    private String email;
    private Integer age;
    private boolean admin;

    private Department dept;

    private List<Role> roles = new ArrayList<>();

    public String getRoleName() {
        if (admin) {
            return "[超级管理员]";
        } else if (roles.size() == 0) {
            return "[暂未分配角色]";
        } else {
            StringBuilder sb = new StringBuilder(40).append("[");
            for (Role role : roles) {
                sb.append(role.getName()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }
    }

    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", age=" + age
                + ", admin=" + admin + "]";
    }
}
