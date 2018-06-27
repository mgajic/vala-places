package com.vala.places.places.dto;

import java.util.Map;

public class MetricDTO {

    private Long counter;

    private Map<String, Long> countryCounterMap;

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public Map<String, Long> getCountryCounterMap() {
        return countryCounterMap;
    }

    public void setCountryCounterMap(Map<String, Long> countryCounterMap) {
        this.countryCounterMap = countryCounterMap;
    }

}
