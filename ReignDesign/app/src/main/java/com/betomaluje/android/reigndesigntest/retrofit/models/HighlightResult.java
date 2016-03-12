package com.betomaluje.android.reigndesigntest.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by betomaluje on 3/11/16.
 */
public class HighlightResult {

    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("comment_text")
    @Expose
    private CommentText commentText;
    @SerializedName("story_title")
    @Expose
    private StoryTitle storyTitle;
    @SerializedName("story_url")
    @Expose
    private StoryUrl storyUrl;

    /**
     * @return The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return The commentText
     */
    public CommentText getCommentText() {
        return commentText;
    }

    /**
     * @param commentText The comment_text
     */
    public void setCommentText(CommentText commentText) {
        this.commentText = commentText;
    }

    /**
     * @return The storyTitle
     */
    public StoryTitle getStoryTitle() {
        return storyTitle;
    }

    /**
     * @param storyTitle The story_title
     */
    public void setStoryTitle(StoryTitle storyTitle) {
        this.storyTitle = storyTitle;
    }

    /**
     * @return The storyUrl
     */
    public StoryUrl getStoryUrl() {
        return storyUrl;
    }

    /**
     * @param storyUrl The story_url
     */
    public void setStoryUrl(StoryUrl storyUrl) {
        this.storyUrl = storyUrl;
    }

}
