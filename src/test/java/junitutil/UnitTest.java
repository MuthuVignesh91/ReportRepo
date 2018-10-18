package junitutil;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.report.app.reporttest.ReportTest;
import com.report.app.trendconfiguration.TrendConfigurationTest;
import com.report.app.trends.TrendTest;

@RunWith(Categories.class)
@IncludeCategory(UT.class)
@SuiteClasses({TrendConfigurationTest.class,TrendTest.class, ReportTest.class})
public class UnitTest{
}
