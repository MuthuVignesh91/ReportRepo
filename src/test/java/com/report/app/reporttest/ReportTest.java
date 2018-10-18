package com.report.app.reporttest;


import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.TreeMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.app.document.trend.Trend;
import com.report.app.document.trend.TrendId;
import com.report.app.document.trend.TrendValues;
import com.report.app.downloadformat.ReportDownloadFormat;
import com.report.app.reportconfig.trendreport.ParamArray;
import com.report.app.reportconfig.trendreport.ParameterColumns;
import com.report.app.reportconfig.trendreport.Parameters;
import com.report.app.reportconfig.trendreport.ReportId;
import com.report.app.reportconfig.trendreport.TimeArray;
import com.report.app.reportconfig.trendreport.TrendReportConfiguration;
import com.report.app.repository.TrendReportConfigRepository;
import com.report.app.repository.TrendRepository;
import com.report.app.util.ReportUtil;

import junitutil.UT;

@RunWith(SpringRunner.class)
@Category(UT.class)
@SpringBootTest
public class ReportTest {

	@Autowired
	private TrendReportConfigRepository reportConfig;
	@Autowired
	private TrendRepository trendRepo;
	
//	@Test
	public void addTrendReportConfig()
	{
		ReportId id = new ReportId("Saravana_Solars", "Custome_Report_v001");
		String localPath = "Some path";
		String reportType = "History";
		Date startDate = new Date();
		Date endDate = new Date();
		boolean append = true;
		
		Parameters timestamp = new TimeArray("TIME", "dd/MM/yyyy HH:mm:ss", "trends");
		Parameters param1 = new ParameterColumns("device1.S001.0", "trends", "PARAM1(kW)");
		Parameters param2 = new ParameterColumns("device1.s002.0", "trends", "PARAM2(kWh)");
		
		ParamArray paramObject = new ParamArray();
		paramObject.addToList(timestamp);
		paramObject.addToList(param1);
		paramObject.addToList(param2);
		
		TrendReportConfiguration config = new TrendReportConfiguration(id, localPath, reportType, startDate, endDate, append, paramObject.getParamArray());
		
		this.reportConfig.save(config);
	}
	
	
	/*
	 * Keeping for internal reference only
	 */
//	@Test
	@Deprecated
	public void generateReport() throws ParseException, FileNotFoundException
	{
		ReportId id = new ReportId("Saravana_Solars", "Custome_Report_v001");
		Optional<TrendReportConfiguration> configuration = this.reportConfig.findById(id);
		if(configuration.isPresent())
		{
			Date startDate = configuration.get().getStartDate();
			Parameters[] params = configuration.get().getParams();
			HashMap<String, TrendValues> reportContent = new HashMap<>();
			String[][] dbData = null;
			String timeLabel = null;
			SimpleDateFormat format = null;
			Date trendStartDate = null;
			int maxParamLength = 0;
			for(Parameters paramInstance : params)
			{
				if(paramInstance instanceof TimeArray)
				{
					TimeArray time = (TimeArray) paramInstance;
					System.out.println("Format ======================================   "+time.getFormat());
					timeLabel = time.getLabel();
					format = new SimpleDateFormat(time.getFormat());
				}
				else if(paramInstance instanceof ParameterColumns)
				{
					ParameterColumns pColumn = (ParameterColumns) paramInstance;
					String[] dbParams = pColumn.getDbParam().split("\\.");
					TrendId id1 = new TrendId(startDate, dbParams[0], dbParams[1]);
					
					Optional<Trend> trendResult = this.trendRepo.findById(id1);
					trendStartDate = trendResult.get().getId().getTimestamp();
						
					if(trendResult.isPresent())
					{
						int tempSize = trendResult.get().getValues().size();
						reportContent.put(dbParams[0]+"."+pColumn.getLabel()+"*"+dbParams[2], trendResult.get().getValues());
						if(maxParamLength < tempSize)
						{
							maxParamLength = tempSize+1;
						}
					}
					
				}
			}
			dbData = new String[maxParamLength][params.length];
			System.out.println("Reprinting report content ============================================= "+ reportContent.toString());
			/*Set<Date> dateIndex = new TreeSet<>();
			for(Map.Entry<String, TrendValues> reportContentInstance : reportContent.entrySet())
			{
				for(Map.Entry<String, Object> maxParamDataInstance : reportContentInstance.getValue().entrySet())
				{
					Integer timeInMillis = Integer.parseInt(maxParamDataInstance.getKey())*1000;
					cal.setTimeInMillis(trendStartDate.getTime()+timeInMillis);
					dateIndex.add(format.parse(format.format(cal.getTime())));
				}
			}
			Iterator<Date> dateIterator = dateIndex.iterator();
			TreeMap<Date, HashMap<String, Object>> finalMap = new TreeMap<>();
			while(dateIterator.hasNext())
			{
				Date currentDateInstance = dateIterator.next();
				for(Map.Entry<String, TrendValues> reportContentInstance : reportContent.entrySet())
				{
					String[] reportContentHeader = reportContentInstance.getKey().split("\\*");
					TrendValues value = reportContentInstance.getValue();
					for(Map.Entry<String, Object> valueIns : value.entrySet())
					{
					Integer timeInMillis = Integer.parseInt(valueIns.getKey())*1000;
					cal.setTimeInMillis(trendStartDate.getTime()+timeInMillis);
					Date currentTime = format.parse(format.format(cal.getTime()));
					HashMap<String, Object> tempMap = null;
					if(currentDateInstance.equals(currentTime))
					{
						if(finalMap.containsKey(currentTime))
						 tempMap= finalMap.get(currentTime);
						else
						 tempMap = new HashMap<>();
						
						if(!valueIns.getValue().toString().equalsIgnoreCase(reportContentHeader[1].toString()))
							tempMap.put(reportContentHeader[0], valueIns.getValue());
						else
							tempMap.put(reportContentHeader[0], reportContentHeader[1]);
						
						finalMap.put(currentDateInstance, tempMap);
					}
						
					}
				}
			}*/
			ReportUtil util = new ReportUtil();
				TreeMap<Date, HashMap<String, Object>> finalMap = util.getCustomReportContent(reportContent, trendStartDate, format);
				System.out.println("===== Final Map ===== "+ finalMap);
				dbData = util.convertMapToArray(finalMap, dbData);
					dbData[0][0] = timeLabel;
/*					int finalMapIndex =1;
				for(Map.Entry<Date, HashMap<String, Object>> finalMapInstance: finalMap.entrySet())
				{
					dbData[finalMapIndex][0] = finalMapInstance.getKey().toGMTString();
					int columnIndex =1;
					for(Map.Entry<String, Object> paramIns : finalMapInstance.getValue().entrySet())
					{
						for(int i=1; i< params.length; i++)
						{
							try
							{
								if(dbData[0][i].equalsIgnoreCase(paramIns.getKey()))
								{
									columnIndex=i;
									break;
								}
								else
								{
									columnIndex++;
								}
							}
							catch (Exception e) {
								dbData[0][columnIndex] = paramIns.getKey();
								break;
							}
						}
						dbData[finalMapIndex][columnIndex] = paramIns.getValue().toString();
					}
					finalMapIndex++;
				}*/

		/*		PrintWriter writer = new PrintWriter(new File("trendReport.csv"));
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
			writer.close();*/
		ReportDownloadFormat reportFormat = new ReportDownloadFormat(System.getProperty("user.dir"));
		reportFormat.generateCSVFile(dbData);
		
		}
		
	}

	@Test
	public void generateCSVReportTest() throws ParseException, FileNotFoundException
	{
		ReportId id = new ReportId("Saravana_Solars", "Custome_Report_v001");
		Optional<TrendReportConfiguration> configuration = this.reportConfig.findById(id);
		if(configuration.isPresent())
		{
			Date startDate = configuration.get().getStartDate();
			Parameters[] params = configuration.get().getParams();
			HashMap<String, TrendValues> reportContent = new HashMap<>();
			String[][] dbData = null;
			String timeLabel = null;
			SimpleDateFormat format = null;
			Date trendStartDate = null;
			int maxParamLength = 0;
			Calendar.getInstance();
			for(Parameters paramInstance : params)
			{
				if(paramInstance instanceof TimeArray)
				{
					TimeArray time = (TimeArray) paramInstance;
					System.out.println("Format ======================================   "+time.getFormat());
					timeLabel = time.getLabel();
					format = new SimpleDateFormat(time.getFormat());
				}
				else if(paramInstance instanceof ParameterColumns)
				{
					ParameterColumns pColumn = (ParameterColumns) paramInstance;
					String[] dbParams = pColumn.getDbParam().split("\\.");
					TrendId id1 = new TrendId(startDate, dbParams[0], dbParams[1]);
					
					Optional<Trend> trendResult = this.trendRepo.findById(id1);
					trendStartDate = trendResult.get().getId().getTimestamp();
						
					if(trendResult.isPresent())
					{
						int tempSize = trendResult.get().getValues().size();
						reportContent.put(dbParams[0]+"."+pColumn.getLabel()+"*"+dbParams[2], trendResult.get().getValues());
						if(maxParamLength < tempSize)
						{
							maxParamLength = tempSize+1;
						}
					}
					
				}
			}
			dbData = new String[maxParamLength][params.length];
			System.out.println("Reprinting report content ============================================= "+ reportContent.toString());
			
			ReportUtil util = new ReportUtil();
			TreeMap<Date, HashMap<String, Object>> finalMap = util.getCustomReportContent(reportContent, trendStartDate, format);
			dbData = util.convertMapToArray(finalMap, dbData);
			dbData[0][0] = timeLabel;
			
			ReportDownloadFormat reportFormat = new ReportDownloadFormat(System.getProperty("user.dir"));
			reportFormat.generateCSVFile(dbData);
	}
}
}
