package com.vala.places.places.service;

import com.vala.places.places.entity.Metric;
import com.vala.places.places.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricService {

    @Autowired
    private MetricRepository metricRepository;

    List<Metric> getPlacesMetric() {
        return metricRepository.findAll();
    }

    void increaseRequestAndCountryCounter(String country){

    }

    void increaseRequestCounter(){

    };

}
