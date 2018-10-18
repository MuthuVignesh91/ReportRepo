package com.report.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.report.app.document.trend.Trend;
import com.report.app.document.trend.TrendId;

public interface TrendRepository extends MongoRepository<Trend, TrendId>{

	public List<Trend> findByIdDeviceId(String deviceId);
	
}
