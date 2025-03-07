package com.tryingagain;

import java.util.ArrayList;

public class Chore {
    private String name;
    private int timeInSeconds;
    static ArrayList<Chore> chores = new ArrayList<Chore>();


    //constructor
    Chore(String n, int time){
        name = n;
        timeInSeconds = time;
    }
    //setters
    public void setname(String n){
        name = n;
    }
    public void setTime(int t){
        timeInSeconds = t;
    }

    //getters
    public String getName(){
        return this.name;
    }
    public int getTime(){
        return this.timeInSeconds;
    }

    public void addToChores(Chore newChore){
        chores.add(newChore);
    }
    public static boolean removeFromChores(String removedChoreName){

        if(!chores.isEmpty()){
        for(int i=0;i<chores.size();i++){
            if(chores.get(i).getName().equals(removedChoreName)){
                chores.remove(chores.get(i));
                return true;
            }
        }
        }
        return false;
    }



}
