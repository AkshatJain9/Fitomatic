package com.ajsmdllz.fitomatic;

public class User {
    private String name;
//    private String userName;
    private String email;
    private int age;
    private String gender;

    public User(String name, String email, int age, String gender){
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public User(String email) {
        this.email = email;
        this.name = "";
        this.age = 0;
        this.gender = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
