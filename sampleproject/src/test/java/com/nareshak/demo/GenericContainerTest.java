package com.nareshak.demo;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

    @Testcontainers
    public class GenericContainerTest {

        @Container
        private static GenericContainer<?> redis = new GenericContainer<>(
                DockerImageName.parse("redis:6"))
                .withExposedPorts(6379);

        @Test
        void testRedisOps() {
            //get host port
            System.out.println(redis.getMappedPort(6379));
            assertTrue(redis.isRunning());
        }
    }
