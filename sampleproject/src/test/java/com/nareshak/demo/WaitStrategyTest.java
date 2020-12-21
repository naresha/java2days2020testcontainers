package com.nareshak.demo;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

@Testcontainers
public class WaitStrategyTest {

    @Container
    private static GenericContainer<?> tomcat = new
            GenericContainer<>(
            DockerImageName.parse("tomcat:8.5.8-jre8"))
            .withExposedPorts(8080)
            .waitingFor(Wait.forHttp("/")
            .forStatusCode(200));

    //.withStartupTimeout(Duration.ofMinutes(2));
    //.waitingFor(Wait.forHttp("/"));

    @Test
    void test() throws Exception {
        //System.out.println("Ready");
        //Thread.sleep(30000);
        System.out.println(tomcat.getMappedPort(8080));
    }

}
