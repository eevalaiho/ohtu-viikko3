package ohtu;

import java.util.Arrays;

public class Submission {

    private int[] exercises;
    private int week;
    private int hours;

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public String toString(int maximum) {

        return "viikko " + week
                + ": tehtyjä tehtäviä yhteensä: " + exercises.length
                + " (maksimi " + maximum + ")"
                + ", aikaa kului " + hours + " tuntia, "
                + "tehdyt tehtävät: " + Arrays.toString(exercises);
    }
    
}