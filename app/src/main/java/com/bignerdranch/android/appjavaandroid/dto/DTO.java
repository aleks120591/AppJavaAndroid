package com.bignerdranch.android.appjavaandroid.dto;

import java.util.Date;

public class DTO {

    private long id;
    private String title;
    private Date remindDate;

    public DTO(String title) {
        this.title = title;
    }

    public DTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return remindDate;
    }

    public void setDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
