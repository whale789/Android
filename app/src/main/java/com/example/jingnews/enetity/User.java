package com.example.jingnews.enetity;

public class User {

    private String id;
    private String userAccount;
    private String userPassword;
    private String userName;
    private String avatar;
    private String email;


    public User() {
    }

    public User(String id, String userAccount, String userPassword, String userName,String avatar,String email) {
        this.id = id;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userName = userName;
        this.avatar=avatar;
        this.email=email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

