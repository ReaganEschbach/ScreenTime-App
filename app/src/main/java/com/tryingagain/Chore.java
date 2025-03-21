package com.tryingagain;

import java.util.ArrayList;
import java.util.Locale;

public class Chore {
    private static Chore selectedChore = new Chore();
    private String name;
    private int timeInSeconds;
    static ArrayList<Chore> chores = new ArrayList<Chore>();


    //constructor
    Chore(String n, int time){
        name = n;
        timeInSeconds = time;
    }
    Chore(){
        name = "";
        timeInSeconds = 0;
    }

    //setters
    public void setname(String n){
        name = n;
    }
    public void setTime(int t){
        timeInSeconds = t;
    }
    public static void setSelectedChore(Chore select){ selectedChore = select; }

    //getters
    public String getName(){
        return this.name;
    }
    public int getTime(){
        return this.timeInSeconds;
    }
    public static Chore getSelectedChore(){ return selectedChore; }
    public static String getChoreFromString(String n){
        for(int i=0; i<chores.size();i++){
            if(n.equals(chores.get(i).getName())){
                return chores.get(i).getName();
            }
        }
        return "";
    }
    public static String getTimeStringLayout(Chore item){
        int tempDuration;
        int duration = item.getTime();

        int hours = duration / 3600;
        tempDuration = duration - (hours*3600);

        int minutes = tempDuration / 60;
        tempDuration = tempDuration - (minutes * 60);

        int seconds = tempDuration;

        String time = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds);

        return time;
    }

    //array stuff
    public static void addToChores(Chore newChore){
        chores.add(newChore);
    }
    public static void removeFromChores(String removedChoreName){

        if(!chores.isEmpty() && chores.size()>1){
        for(int i=0;i<chores.size();i++){
            if(chores.get(i).getName().equals(removedChoreName)){
                chores.remove(chores.get(i));
                return;
            }
        }
        }
    }

    public static void clearList(){
        chores.clear();
    }
    public static int getChoreListSize(){
        return chores.size();
    }
    public static Chore getChoreFromList(int index){
        if(index+1>getChoreListSize() || index<0){
            return null;
        }
        return chores.get(index);
    }
    public static String formatStringForView(Chore chore){
        return chore.getName() + " | " + Chore.getTimeStringLayout(chore);
    }






}
