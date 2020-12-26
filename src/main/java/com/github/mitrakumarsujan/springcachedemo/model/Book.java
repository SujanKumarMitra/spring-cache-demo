package com.github.mitrakumarsujan.springcachedemo.model;

import java.util.List;

public interface Book {

    String getIsbn();

    String getTitle();

    List<String> getAuthors();

    String getPublisher();

    String getCategory();

}
