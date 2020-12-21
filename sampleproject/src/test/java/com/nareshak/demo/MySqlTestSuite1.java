package com.nareshak.demo;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class MySqlTestSuite1 extends MySqlTest {

    @Test
    void test1() {
        assertTrue(mySQLContainer.isRunning());
    }

    @Test
    void test2() {
        assertTrue(mySQLContainer.isRunning());
    }
}
