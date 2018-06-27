package com.vala.places.places.service;

import com.vala.places.places.entity.Metric;
import com.vala.places.places.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class MetricService {

    @Autowired
    private MetricRepository metricRepository;


   public List<Metric> getPlacesMetric() {
        return metricRepository.findAll();
    }

    public void increaseRequestAndCountryCounter(String country){
        List<Metric> metrics = metricRepository.findAll();

        Metric metric = null;
        if(metrics.size()>0){
            metric = metrics.get(0);
            incCounter(metric);
            updateCountryMap(country, metric);
        }else{
            metric = createNewMetric(country);
        }

        metricRepository.save(metric);
    }

   public void increaseRequestCounter(){
        List<Metric> metrics = metricRepository.findAll();
        Metric metric = null;
        if(metrics.size()>0){
            metric = metrics.get(0);
            incCounter(metric);
        }else{
            metric = createNewMetric(null);
        }

        metricRepository.save(metric);
    };

    private Metric createNewMetric(String country) {
        Metric metric;Map<String, Long> countryCounterMap=new HashMap<>();
        if(country!=null){
        countryCounterMap.put(country,1l);
        }
        metric=new Metric(new Random().nextLong(),1l,countryCounterMap);
        return metric;
    }

    private void updateCountryMap(String country, Metric metric) {
        Map<String, Long> countryCounterMap = metric.getCountryCounterMap();
        if (countryCounterMap.containsKey(country)) {
            Long countryCounter = countryCounterMap.get(country);
            countryCounter++;
            countryCounterMap.replace(country, countryCounter);
        } else {
            countryCounterMap.put(country, 1l);
        }
    }

    private void incCounter(Metric metric) {
        Long counter = metric.getCounter();
        counter++;
        metric.setCounter(counter);
    }



}
