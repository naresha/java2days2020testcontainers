package com.nareshak.demo;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class MySqlTest {

    @Container
    protected static final MySQLContainer mySQLContainer;

    static {
        mySQLContainer = new MySQLContainer(DockerImageName.parse("mysql:8"))
                .withDatabaseName("foo")
                .withUsername("foo")
                .withPassword("secret");
        mySQLContainer.start();
    }
}
