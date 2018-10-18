package com.report.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.report.app.reportconfig.trendreport.ReportId;
import com.report.app.reportconfig.trendreport.TrendReportConfiguration;

public interface TrendReportConfigRepository extends MongoRepository<TrendReportConfiguration, ReportId>{

}
