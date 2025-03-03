package com.tryingagain;

import java.util.ArrayList;

public class Chore {
    private String name;
    private int timeInSeconds;
    static ArrayList<Chore> chores;


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
    public boolean removeFromChores(Chore removeChore){

        String choreName = removeChore.getName();
        int choreTime = removeChore.getTime();

        for(int i=0;i<chores.size();i++){
            if(chores.get(i).getName().equals(choreName) && chores.get(i).getTime()==choreTime){
                chores.remove(chores.get(i));
                return true;
            }
        }
        return false;
    }



}
