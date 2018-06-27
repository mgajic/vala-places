package com.vala.places.places.entity;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Metric {


    @Id
    private long id;

    private Long counter;

    @ElementCollection(targetClass=Long.class,fetch = FetchType.EAGER)
    @MapKeyColumn(name="Country")
    private Map<String,Long> countryCounterMap;

    private Metric() {
    }

    @PersistenceConstructor
    public Metric(Long id,Long counter, Map<String,Long> countryCounterMap) {
        super();
        this.id=id;
        this.counter = counter;
        this.countryCounterMap = countryCounterMap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
