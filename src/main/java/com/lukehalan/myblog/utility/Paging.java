package com.lukehalan.myblog.utility;


import com.lukehalan.myblog.entities.PostEntity;
import org.springframework.data.domain.Page;


public class Paging {

    private final Page<PostEntity> posts;

    public int getPageIndex() {
        return posts.getNumber() + 1;
    }

    public Paging(Page<PostEntity> posts) {
        this.posts = posts;
    }

    public int getPageSize() {
        return posts.getSize();
    }

    public boolean hasNext() {
        return posts.hasNext();
    }

    public boolean hasPrevious() {
        return posts.hasPrevious();
    }

    public int getTotalPages() {
        return posts.getTotalPages();
    }

    public long getTotalElements() {
        return posts.getTotalElements();
    }

    public Page<PostEntity> getPosts() {
        return posts;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }

}
