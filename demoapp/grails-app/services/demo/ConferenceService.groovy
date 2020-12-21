package demo

import grails.gorm.transactions.Transactional

@Transactional
class ConferenceService {

    def getConferencesInCities(List<String> cities) {
        Conference.where {
            city in cities
        }.list()
    }
}
