package com.example.kirill.retrofitTry.Parsers;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tests {

    @SerializedName("tests")
    @Expose
    private List<Test> tests = null;

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

}