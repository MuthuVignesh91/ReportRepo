package com.report.app.trendconfiguration;

import java.util.Date;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.app.reportconfig.trendreport.ParamArray;
import com.report.app.reportconfig.trendreport.ParameterColumns;
import com.report.app.reportconfig.trendreport.ReportId;
import com.report.app.reportconfig.trendreport.TimeArray;
import com.report.app.reportconfig.trendreport.TrendReportConfiguration;
import com.report.app.repository.TrendReportConfigRepository;
import com.report.app.util.ReportUtil;

import junitutil.UT;

@RunWith(SpringRunner.class)
@Category(UT.class)
@SpringBootTest
public class TrendConfigurationTest {

	@Autowired
	private TrendReportConfigRepository configRepo;
	
	@Test
	public void createConfig()
	{
		ReportId id = new ReportId("Saravana_Solars", "Custome_Report_v001");
		String localPath = System.getProperty("user.dir");
		TimeArray timeArr = new TimeArray("TIME", "dd-MM-yyyy HH:mm:ss", "TIME");
		ParameterColumns column1 = new ParameterColumns("device1.S001.0", "S001", "PARAM1");
		ParameterColumns column2 = new ParameterColumns("device1.S002.0", "S002", "PARAM2");
		
		ParamArray arr = new ParamArray();
		arr.addToList(timeArr);
		arr.addToList(column1);
		arr.addToList(column2);
		
		
		TrendReportConfiguration config = new TrendReportConfiguration(id, localPath, "custom", 
				new Date(), new Date(), true, arr.getParamArray());
		configRepo.save(config);
				
	}
}
