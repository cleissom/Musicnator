package com.musicnator;

public class Item {
    private String mText;
    private boolean mCompleted;

    public Item(String text, boolean completed) {
        mText = text;
        mCompleted = completed;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }
}