package com.cricket;

public class Tournament {
    private String tournamentName;
    private String  registrationStartDate ;
    private String  registrationEndDate;
    private  String format;
    private int overs;
    private String trailsStartDate;
    private String trailsEndDate;

    public Tournament(){

    }

    public Tournament(String tournamentName, String registrationStartDate, String registrationEndDate, String format, int overs, String trailsStartDate, String trailsEndDate) {
        this.tournamentName = tournamentName;
        this.registrationStartDate = registrationStartDate;
        this.registrationEndDate = registrationEndDate;
        this.format = format;
        this.overs = overs;
        this.trailsStartDate = trailsStartDate;
        this.trailsEndDate = trailsEndDate;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getRegistrationStartDate() {
        return registrationStartDate;
    }

    public void setRegistrationStartDate(String registrationStartDate) {
        this.registrationStartDate = registrationStartDate;
    }

    public String getRegistrationEndDate() {
        return registrationEndDate;
    }

    public void setRegistrationEndDate(String registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public String getTrailsStartDate() {
        return trailsStartDate;
    }

    public void setTrailsStartDate(String trailsStartDate) {
        this.trailsStartDate = trailsStartDate;
    }

    public String getTrailsEndDate() {
        return trailsEndDate;
    }

    public void setTrailsEndDate(String trailsEndDate) {
        this.trailsEndDate = trailsEndDate;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tournamentName='" + tournamentName + '\'' +
                ", registrationStartDate='" + registrationStartDate + '\'' +
                ", registrationEndDate='" + registrationEndDate + '\'' +
                ", format='" + format + '\'' +
                ", overs=" + overs +
                ", trailsStartDate='" + trailsStartDate + '\'' +
                ", trailsEndDate='" + trailsEndDate + '\'' +
                '}';
    }
}
