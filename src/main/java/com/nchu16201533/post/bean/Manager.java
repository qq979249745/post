package com.nchu16201533.post.bean;

import javax.validation.constraints.Pattern;

public class Manager {
    private Integer id;

    private String name;
    @Pattern(regexp = ".{4,32}",
            message = "账号格式错误")
    private String account;
    @Pattern(regexp = ".{4,32}",
            message = "密码错误")
    private String password;

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
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}