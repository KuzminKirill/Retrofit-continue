package com.example.kirill.retrofitTry.Parsers;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Themes {

    @SerializedName("themes")
    @Expose
    private List<Theme> themes = null;

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

}