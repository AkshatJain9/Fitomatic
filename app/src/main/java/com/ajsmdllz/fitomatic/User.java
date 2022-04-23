package com.ajsmdllz.fitomatic;

public class User {
    private String name;
    private int age;
    private String gender;

    public User(String name, int age, String gender){
        this.name = name;
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
