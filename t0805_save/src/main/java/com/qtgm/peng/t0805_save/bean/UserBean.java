package com.qtgm.peng.t0805_save.bean;

/**
 * @author peng_wang
 * @date 2019/8/5
 */
public class UserBean {

    private int id;
    private String username;
    private String password;
    private int age;
    private String sex;

    public UserBean(String username, String password, int age, String sex) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
