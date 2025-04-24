package reportsGenerator;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class CucumberReportGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File reportOutputDirectory = new File("target/cucumber-reports");
        List<String> jsonFiles = Collections.singletonList("target/cucumber.json");

        Configuration config = new Configuration(reportOutputDirectory, "PlaywrightJavaCucumberTestNG");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();
	}

}
