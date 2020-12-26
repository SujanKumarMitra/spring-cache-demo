package com.github.mitrakumarsujan.springcachedemo.dao;

public class InMemoryBookDaoIntegrationTest extends BookDaoIntegrationTest {

    public InMemoryBookDaoIntegrationTest() {
        super.daoUnderTest = new InMemoryBookDao();
    }

}
