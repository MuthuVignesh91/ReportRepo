package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.reportconfig.trendreport.ReportId;
import com.example.demo.reportconfig.trendreport.TrendReportConfiguration;

public interface TrendReportConfigRepository extends MongoRepository<TrendReportConfiguration, ReportId>{

}
