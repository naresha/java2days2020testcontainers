package com.nareshak.demo;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class DockerComposeTest {

    @Container
    private DockerComposeContainer<?> container = new
            DockerComposeContainer<>(
            new File("src/test/resources/docker-compose.yml"))
            .withExposedService("web_1", 8080)
            .withExposedService("redis_1", 6379);

    @Test
    void testIntegration() throws Exception {
        String web_1 = container.getServiceHost("web_1", 8080);
    }
}
