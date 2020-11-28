package com.spa.jwt.models;

import java.util.List;

public class SPAPostsResponse {


    public List<SPAPosts> getPost() {
        return post;
    }

    public void setPost(List<SPAPosts> post) {
        this.post = post;
    }

    private List<SPAPosts> post;

    public int getTotalNoPages() {
        return totalNoPages;
    }

    public void setTotalNoPages(int totalNoPages) {
        this.totalNoPages = totalNoPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    private int totalNoPages;
    private int currentPage;
}
