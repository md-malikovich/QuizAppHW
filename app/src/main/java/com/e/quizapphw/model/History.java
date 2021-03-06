package com.e.quizapphw.model;

import java.util.Date;

public class History {

    private String difficulty;
    private String category;
    private int correctAnswers;
    private Date date;
    private int id;
    private int amount;

    public History(String category, String difficulty, int correctAnswers, Date date, int id, int amount) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswers = correctAnswers;
        this.date = date;
        this.id = id;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

