package com.report.app.downloadformat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ReportDownloadFormat {

	private String reportPath;
	public ReportDownloadFormat(String reportPath) {
		this.reportPath = reportPath;
	}
	
	public void generateCSVFile(Object[][] dbData) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(new File(reportPath+"/"+"trendReport.csv"));
		StringBuilder builder = new StringBuilder();
		
	for(int i=0; i< dbData.length; i++)
	{
		for(int j=0; j< dbData[i].length; j++)
		{
			builder.append(dbData[i][j]+",");
		}
		builder.append("\n");
	}
	writer.write(builder.toString());
	writer.flush();
	writer.close();
	}
}
