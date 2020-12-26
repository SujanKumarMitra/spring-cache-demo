package com.github.mitrakumarsujan.springcachedemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InMemoryBookDaoIntegrationTest extends BookDaoIntegrationTest {

    @Autowired
    public InMemoryBookDaoIntegrationTest(InMemoryBookDao bookDao) {
        super.daoUnderTest = bookDao;
    }

}
