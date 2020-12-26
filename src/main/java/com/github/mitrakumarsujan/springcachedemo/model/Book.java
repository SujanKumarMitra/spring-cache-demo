package com.github.mitrakumarsujan.springcachedemo.model;

import java.io.Serializable;
import java.util.List;

public interface Book extends Serializable {

    String getIsbn();

    String getTitle();

    List<String> getAuthors();

    String getPublisher();

    String getCategory();

}
