package ohtu;

import java.util.Arrays;

public class Submission {
    private int[] exercises;

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int[] getExercises() {
        return exercises;
    }

    private int week;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    private int hours;

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public String toString() {
        return "viikko " + week
                + ": tehtyjä tehtäviä yhteensä: " + exercises.length
                + ", aikaa kului " + hours + " tuntia, "
                + "tehdyt tehtävät: " + Arrays.toString(exercises);
    }
    
}