package stepdefinitions;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.en.*;
import pages.LoginPage;

public class LoginSteps {
	
	private static Playwright playwright;
	private static Browser browser;
	private static BrowserContext context;
	private static Page page;
	LoginPage login;
	
	@Given("user lanches the application")
	public void lanuchApp() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
		context = browser.newContext();
        page = context.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	@When("user logs with valid username and password")
	public void loginPage() throws InterruptedException {
		login = new LoginPage(page);
		login.loginPage("Admin", "admin123");
		Thread.sleep(5000);
	}
	
	@Then("user should see the dashboard")
	public void verifyDashboard() {
		assertTrue(page.locator(".oxd-main-menu-item").first().isVisible());
		context.close();
		page.close();
	}

	@When("user logs with invalid username {string} and {string}")
	public void user_logs_with_invalid_username_and(String name, String password) throws InterruptedException {
		login = new LoginPage(page);
		login.loginPage(name, password);
		Thread.sleep(5000);
	}
	
	@Then("user should see an error message {string}")
	public void verifyLoginErrorMessage(String message) {
		login = new LoginPage(page);
		String lbl_message = login.getLoginErrorMessage();
		Assert.assertEquals(message, lbl_message);
		//assertTrue(page.locator("//*[text()='Invalid credentials']").isVisible());
		context.close();
		page.close();
	}
	
	@When("user logs in with {string} and {string}")
	public void user_logs_in_with(String username, String password) throws InterruptedException {
		login = new LoginPage(page);
		login.loginPage(username, password);
		Thread.sleep(5000);
	}
	
	@Then("user should verify Message {string}")
	public void user_should_verify_message(String message) {
		if(message.equalsIgnoreCase("Dashboard")) {
			assertTrue(page.locator(".oxd-main-menu-item").first().isVisible());
			context.close();
			page.close();
		}else {
			assertTrue(page.locator("//*[text()='Invalid credentials']").isVisible());
			context.close();
			page.close();
		}		
	}
	
	@When("user logs with credentials")
	public void user_logs_with_credentials(io.cucumber.datatable.DataTable datatable) throws InterruptedException {
		List<Map<String, String>> credentials = datatable.asMaps(String.class, String.class);
		String name = credentials.get(0).get("username");
		String password = credentials.get(0).get("password");
		login = new LoginPage(page);
		login.loginPage(name, password);
		Thread.sleep(5000);
	}
}
