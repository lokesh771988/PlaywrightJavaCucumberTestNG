package reportsGenerator;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CucumberReportGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File reportOutputDirectory = new File("target/cucumber-report-html");
        List<String> jsonFiles = Arrays.asList("target/cucumber.json");

        Configuration config = new Configuration(reportOutputDirectory, "Playwright Java Cucumber");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();
	}

}
