package com.hector.userdb.DataModels;

import java.util.ArrayList;

public class APIResponse {
    ArrayList<MovieData> results;

    public  APIResponse() {}

    public APIResponse(ArrayList<MovieData> results) {
        this.results = results;
    }

    public ArrayList<MovieData> getResults() {
        return results;
    }
}
