package com.nareshak.demo;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class SampleMySqlTest {

    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer(DockerImageName.parse("mysql:8"))
            .withDatabaseName("foo")
            .withUsername("foo")
            .withPassword("secret");

    @Test
    void test1() {
        // invoke SUT && assert
        assertTrue(mySQLContainer.isRunning());
    }

    @Test
    void test2() {
        // invoke SUT && assert
        assertTrue(mySQLContainer.isRunning());

    }
}
