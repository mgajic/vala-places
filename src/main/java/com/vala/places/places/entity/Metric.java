package com.vala.places.places.entity;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Metric {

    @Id
    private Long counter;
    private Map<String,Long> countryCounterMap;

    private Metric() {
    }

    @PersistenceConstructor
    public Metric(Long counter, Map<String,Long> countryCounterMap) {
        super();
        this.counter = counter;
        this.countryCounterMap = countryCounterMap;
    }

    @Override
    public String toString() {
        return "Metric [counter=" + counter + ", countryCounterMap=" + countryCounterMap + "]";
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public Map<String,Long> getCountryCounterMap() {
        return countryCounterMap;
    }

    public void setCountryCounterMap(Map<String,Long> countryCounterMap) {
        this.countryCounterMap = countryCounterMap;
    }

}
