package com.ajsmdllz.fitomatic;

public class User {
    private String name;
    private String password;
    private int age;
    private String gender;

    public User(String name, String password, int age, String gender){
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    // Need more setters and getters here

    public String getUserName() {
        return name;
    }

    public void setUserName(String newName) {
        this.name = newName;
    }

    public int getUserAge() {
        return age;
    }

}
