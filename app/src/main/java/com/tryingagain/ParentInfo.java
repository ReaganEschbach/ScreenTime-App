package com.tryingagain;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;

import java.util.Arrays;

public class ParentInfo {
    private static String parentName, childName, parentEmail;
    private static char[] password = {};

    //default constructor
    ParentInfo(){
        parentName = "";
        childName = "";
        parentEmail = "";
    }
    //getters
    public static String getParentPassword(){
        return String.valueOf(password);
    }
    public static boolean attemptPassword(String attempt){
        return Arrays.equals(password, attempt.toCharArray());
    }
    public static String getParentName(){
        return parentName;
    }
    public static String getChildName(){
        return childName;
    }
    public static String getParentEmail(){
        return parentEmail;
    }

    //setters
    public static void setParentName(String parent) {
        parentName = parent;
    }
    public static void setPassword(String pass){
        password = pass.toCharArray();
    }
    public static void setChildName(String child){
        childName = child;
    }
    public static void setParentEmail(String email){
        parentEmail = email;
    }



}
