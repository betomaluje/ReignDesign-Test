package com.betomaluje.android.reigndesigntest.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by betomaluje on 3/11/16.
 */
public class ArticlesRequest {

    @SerializedName("hits")
    @Expose
    private List<Hit> hits = new ArrayList<Hit>();
    @SerializedName("nbHits")
    @Expose
    private int nbHits;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("nbPages")
    @Expose
    private int nbPages;
    @SerializedName("hitsPerPage")
    @Expose
    private int hitsPerPage;
    @SerializedName("processingTimeMS")
    @Expose
    private int processingTimeMS;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("params")
    @Expose
    private String params;

    /**
     * @return The hits
     */
    public List<Hit> getHits() {
        return hits;
    }

    /**
     * @param hits The hits
     */
    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    /**
     * @return The nbHits
     */
    public int getNbHits() {
        return nbHits;
    }

    /**
     * @param nbHits The nbHits
     */
    public void setNbHits(int nbHits) {
        this.nbHits = nbHits;
    }

    /**
     * @return The page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return The nbPages
     */
    public int getNbPages() {
        return nbPages;
    }

    /**
     * @param nbPages The nbPages
     */
    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    /**
     * @return The hitsPerPage
     */
    public int getHitsPerPage() {
        return hitsPerPage;
    }

    /**
     * @param hitsPerPage The hitsPerPage
     */
    public void setHitsPerPage(int hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }

    /**
     * @return The processingTimeMS
     */
    public int getProcessingTimeMS() {
        return processingTimeMS;
    }

    /**
     * @param processingTimeMS The processingTimeMS
     */
    public void setProcessingTimeMS(int processingTimeMS) {
        this.processingTimeMS = processingTimeMS;
    }

    /**
     * @return The query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query The query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return The params
     */
    public String getParams() {
        return params;
    }

    /**
     * @param params The params
     */
    public void setParams(String params) {
        this.params = params;
    }

}
