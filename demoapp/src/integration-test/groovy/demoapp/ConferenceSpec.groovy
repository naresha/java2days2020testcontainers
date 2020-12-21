package demoapp

import demo.Conference
import demo.ConferenceService
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.*
import spock.lang.Specification

@Integration
@Rollback
class ConferenceSpec extends Specification {

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
