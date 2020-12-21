package demoapp

import demo.Conference
import demo.ConferenceService
import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification

@Integration
@Rollback
@Testcontainers
class ConferenceTestContainerSpec extends Specification {

    @Shared
    MySQLContainer mySQLContainer = new MySQLContainer(DockerImageName.parse("mysql:8"))
            .withDatabaseName("testapp")
            .withUsername("user")
            .withPassword("secret")


    ConferenceService conferenceService

    def setup() {
    }

    def cleanup() {
    }

    void "should return empty list"() {
        given:
        List<String> cities = []

        when:
        List<Conference> conferences = conferenceService.getConferencesInCities(cities)

        then:
        conferences.size() == 0
    }
}
