package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.document.trend.Trend;
import com.example.demo.document.trend.TrendId;

public interface TrendRepository extends MongoRepository<Trend, TrendId>{

	public List<Trend> findByIdDeviceId(String deviceId);
	
}
