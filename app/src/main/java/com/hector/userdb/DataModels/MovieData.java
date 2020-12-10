package com.hector.userdb.DataModels;

public class MovieData {
    String title, overview, release_date, vote_average, poster_path;

    public  MovieData(){}

    public MovieData(String title, String overview, String release_date, String vote_average, String poster_path) {
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
