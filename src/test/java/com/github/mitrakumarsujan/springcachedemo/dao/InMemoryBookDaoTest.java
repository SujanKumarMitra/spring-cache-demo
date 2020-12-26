package com.github.mitrakumarsujan.springcachedemo.dao;

public class InMemoryBookDaoTest extends BookDaoTest {

    public InMemoryBookDaoTest() {
        super.daoUnderTest = new InMemoryBookDao();
    }

}
