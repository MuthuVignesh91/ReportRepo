package com.example.demo.reportconfig.trendreport;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TrendReportConfiguration {

	@Id
	private ReportId id;
	private String localPath;
	private String reportType;
	private Date startDate;
	private Date endDate;
	private boolean append;
	private Parameters[] params;
	
	
	public TrendReportConfiguration(ReportId id, String localPath, String reportType, Date startDate, Date endDate, boolean append,
			Parameters[] params) {
		this.id = id;
		this.localPath = localPath;
		this.reportType = reportType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.append = append;
		this.params = params;
	}

	public ReportId getId() {
		return id;
	}
	
	public void setId(ReportId id) {
		this.id = id;
	}
	
	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getReportType() {
		return reportType;
	}
	
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public boolean isAppend() {
		return append;
	}
	
	public void setAppend(boolean append) {
		this.append = append;
	}
	
	public Parameters[] getParams() {
		return params;
	}
	
	public void setParams(Parameters[] params) {
		this.params = params;
	}
		
}
