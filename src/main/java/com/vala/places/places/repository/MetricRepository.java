package com.vala.places.places.repository;

import com.vala.places.places.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetricRepository  extends JpaRepository<Metric, Long> {

}
