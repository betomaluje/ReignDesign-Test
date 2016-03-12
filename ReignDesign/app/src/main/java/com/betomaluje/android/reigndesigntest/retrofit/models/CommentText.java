package com.betomaluje.android.reigndesigntest.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by betomaluje on 3/11/16.
 */
public class CommentText {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("matchLevel")
    @Expose
    private String matchLevel;
    @SerializedName("matchedWords")
    @Expose
    private List<String> matchedWords = new ArrayList<String>();

    /**
     * @return The value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return The matchLevel
     */
    public String getMatchLevel() {
        return matchLevel;
    }

    /**
     * @param matchLevel The matchLevel
     */
    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    /**
     * @return The matchedWords
     */
    public List<String> getMatchedWords() {
        return matchedWords;
    }

    /**
     * @param matchedWords The matchedWords
     */
    public void setMatchedWords(List<String> matchedWords) {
        this.matchedWords = matchedWords;
    }

}
