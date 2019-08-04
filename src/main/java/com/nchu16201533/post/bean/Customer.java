package com.nchu16201533.post.bean;

import javax.validation.constraints.Pattern;

public class Customer {
    private Integer id;
    @Pattern(regexp = ".{4,32}",message = "账号长度为4到32个字符")
    private String account;
    @Pattern(regexp = ".{4,32}",message = "密码长度为4到32个字符")
    private String password;
    @Pattern(regexp = ".{2,16}",message = "姓名长度为2到16个字符")
    private String name;
    @Pattern(regexp = "[男|女]",message = "性别只能是男或女")
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
}