package com.example.kirill.retrofittry.Parsers;

import java.util.List;

import com.example.kirill.retrofittry.Parsers.Theme;
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