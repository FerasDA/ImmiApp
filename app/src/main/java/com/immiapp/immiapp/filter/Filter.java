package com.immiapp.immiapp.filter;

public class Filter {

    private int ID;
    private String Subject;
    private String Themes;
    private String Turn;
    private String ScheduleDate;
    private String RealDate;

    public Filter() {
        // TODO Auto-generated constructor stub
    }

    public Filter(String subject, String themes, String turn,
                 String scheduleDate, String realDate) {
        super();
        Subject = subject;
        Themes = themes;
        Turn = turn;
        ScheduleDate = scheduleDate;
        RealDate = realDate;
    }

    public Filter(int iD, String subject, String themes, String turn,
                 String scheduleDate, String realDate) {
        super();
        ID = iD;
        Subject = subject;
        Themes = themes;
        Turn = turn;
        ScheduleDate = scheduleDate;
        RealDate = realDate;
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
     * @return the subject
     */
    public String getSubject() {
        return Subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        Subject = subject;
    }

    /**
     * @return the themes
     */
    public String getThemes() {
        return Themes;
    }

    /**
     * @param themes the themes to set
     */
    public void setThemes(String themes) {
        Themes = themes;
    }

    /**
     * @return the turn
     */
    public String getTurn() {
        return Turn;
    }

    /**
     * @param turn the turn to set
     */
    public void setTurn(String turn) {
        Turn = turn;
    }

    /**
     * @return the scheduleDate
     */
    public String getScheduleDate() {
        return ScheduleDate;
    }

    /**
     * @param scheduleDate the scheduleDate to set
     */
    public void setScheduleDate(String scheduleDate) {
        ScheduleDate = scheduleDate;
    }

    /**
     * @return the realDate
     */
    public String getRealDate() {
        return RealDate;
    }

    /**
     * @param realDate the realDate to set
     */
    public void setRealDate(String realDate) {
        RealDate = realDate;
    }


}
