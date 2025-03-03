package com.tryingagain;

public class ParentInfo {
    String parentName, password;

    //default constructor
    public ParentInfo(){
        parentName = "";
        password = "";
    }
    public ParentInfo (String name, String password){
        this.parentName = name;
        this.password = password;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
