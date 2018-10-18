package com.report.app.reportconfig.trendreport;

public class ReportId {

	private String customerToken;
	private String reportName;
	
	public ReportId(String customerToken, String reportName) {
		this.customerToken = customerToken;
		this.reportName = reportName;
	}
	
	public String getCustomerToken() {
		return customerToken;
	}
	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
}
