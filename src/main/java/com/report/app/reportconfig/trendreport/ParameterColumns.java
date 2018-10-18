package com.report.app.reportconfig.trendreport;

public class ParameterColumns extends Parameters{

	private String dbParam;
	private String colName;
	private String label;
	
	public ParameterColumns(String dbParam, String colName, String label) {
		this.dbParam = dbParam;
		this.colName = colName;
		this.label = label;
	}
	
	public String getDbParam() {
		return dbParam;
	}
	public void setDbParam(String dbParam) {
		this.dbParam = dbParam;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
