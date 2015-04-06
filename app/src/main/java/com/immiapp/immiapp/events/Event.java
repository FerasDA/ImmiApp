package com.immiapp.immiapp.events;

public class Event {

    private int ID;
    private String Title;
    private String Description;
    private String Location;
    private String Time;
    private String Date;
    private String Category;
    private boolean Selected;

    public Event() {}

    public Event(String title, String description, String location, String time, String date, String category) {
        Title = title;
        Description = description;
        Location = location;
        Time = time;
        Date = date;
        Category = category;
    }

    public Event(int id, String title, String description, String location, String time, String date, String category) {
        ID = id;
        Title = title;
        Description = description;
        Location = location;
        Time = time;
        Date = date;
        Category = category;
    }

    /**
     * @return the iD
     */
    public int getID() {
        return ID;
    }

    /**
     * @param iD the iD to set
     */
    public void setID(int iD) {
        ID = iD;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return Title;
    }


    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        Title = title;
    }


    /**
     * @return the description
     */
    public String getDescription() {
        return Description;
    }


    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        Description = description;
    }


    /**
     * @return the location
     */
    public String getLocation() {
        return Location;
    }


    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        Location = location;
    }


    /**
     * @return the time
     */
    public String getTime() {
        return Time;
    }


    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        Time = time;
    }


    /**
     * @return the date
     */
    public String getDate() {
        return Date;
    }


    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        Date = date;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return Category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        Category = category;
    }

    public boolean isSelected(){
        return Selected;
    }

    public void setSelected(boolean selected){
        Selected = selected;
    }
}
