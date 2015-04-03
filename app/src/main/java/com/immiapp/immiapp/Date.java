package com.immiapp.immiapp;

/**
 * Created by Ben on 4/2/2015.
 */
public class Date {
    int day,
    month,
    year,
    hour,
    minute;

    public Date(int minute, int hour, int day, int month, int year){
        this.minute = minute % 60;
        this.hour = hour % 24;
        this.day = day % 31;
        this.month = month % 12;
        this.year = year;
    }

    public int GetMinute() {
        return minute;
    }

    public void SetMinute(int minute) {
        this.minute = minute % 60;
    }

    public int GetHour() {
        return hour;
    }

    public void SetHout(int hour) {
        this.hour = hour % 24;
    }

    public int GetDay(){
        return day;
    }

    public void SetDay(int day) {
        this.day = day % 31;
    }

    public int GetMonth() {
        return month;
    }

    public void SetMonth(int month) {
        this.month = month % 12;
    }

    public int GetYear() {
        return year;
    }

    public void SetYear(int year) {
        this.year = year;
    }
}
