package com.betomaluje.android.reigndesigntest.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by betomaluje on 3/11/16.
 */
public class Hit {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("points")
    @Expose
    private int points;
    @SerializedName("story_text")
    @Expose
    private String storyText;
    @SerializedName("comment_text")
    @Expose
    private String commentText;
    @SerializedName("num_comments")
    @Expose
    private int numComments;
    @SerializedName("story_id")
    @Expose
    private int storyId;
    @SerializedName("story_title")
    @Expose
    private String storyTitle;
    @SerializedName("story_url")
    @Expose
    private String storyUrl;
    @SerializedName("parent_id")
    @Expose
    private int parentId;
    @SerializedName("created_at_i")
    @Expose
    private int createdAtI;
    @SerializedName("_tags")
    @Expose
    private List<String> Tags = new ArrayList<String>();
    @SerializedName("objectID")
    @Expose
    private String objectID;
    @SerializedName("_highlightResult")
    @Expose
    private HighlightResult HighlightResult;

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return The points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points The points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return The storyText
     */
    public String getStoryText() {
        return storyText;
    }

    /**
     * @param storyText The story_text
     */
    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    /**
     * @return The commentText
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * @param commentText The comment_text
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /**
     * @return The numComments
     */
    public int getNumComments() {
        return numComments;
    }

    /**
     * @param numComments The num_comments
     */
    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    /**
     * @return The storyId
     */
    public int getStoryId() {
        return storyId;
    }

    /**
     * @param storyId The story_id
     */
    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    /**
     * @return The storyTitle
     */
    public String getStoryTitle() {
        return storyTitle;
    }

    /**
     * @param storyTitle The story_title
     */
    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    /**
     * @return The storyUrl
     */
    public String getStoryUrl() {
        return storyUrl;
    }

    /**
     * @param storyUrl The story_url
     */
    public void setStoryUrl(String storyUrl) {
        this.storyUrl = storyUrl;
    }

    /**
     * @return The parentId
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * @param parentId The parent_id
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * @return The createdAtI
     */
    public int getCreatedAtI() {
        return createdAtI;
    }

    /**
     * @param createdAtI The created_at_i
     */
    public void setCreatedAtI(int createdAtI) {
        this.createdAtI = createdAtI;
    }

    /**
     * @return The Tags
     */
    public List<String> getTags() {
        return Tags;
    }

    /**
     * @param Tags The _tags
     */
    public void setTags(List<String> Tags) {
        this.Tags = Tags;
    }

    /**
     * @return The objectID
     */
    public String getObjectID() {
        return objectID;
    }

    /**
     * @param objectID The objectID
     */
    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    /**
     * @return The HighlightResult
     */
    public HighlightResult getHighlightResult() {
        return HighlightResult;
    }

    /**
     * @param HighlightResult The _highlightResult
     */
    public void setHighlightResult(HighlightResult HighlightResult) {
        this.HighlightResult = HighlightResult;
    }

    public String getTrueTitle() {
        if (title != null && !title.isEmpty()) {
            return title;
        } else if (storyTitle != null && !storyTitle.isEmpty()) {
            return storyTitle;
        } else if (getHighlightResult() != null && getHighlightResult().getStoryTitle() != null &&
                getHighlightResult().getStoryTitle().getValue() != null && !getHighlightResult().getStoryTitle().getValue().isEmpty()) {
            return getHighlightResult().getStoryTitle().getValue();
        } else {
            return "";
        }
    }

}
