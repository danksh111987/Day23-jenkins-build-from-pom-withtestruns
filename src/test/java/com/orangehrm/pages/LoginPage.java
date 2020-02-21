package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.common.WebDriverFactory;

public class LoginPage {

	// WebElements
	@FindBy(id = "txtUsername")
	WebElement vUserName;
	@FindBy(id = "txtPassword")
	WebElement vPaswd;
	@FindBy(id = "btnLogin")
	WebElement vBtnLogin;

	// Explicit wait
	WebDriverWait objwait = new WebDriverWait(WebDriverFactory.getWebDriver(), 10);

	// Constructor to initialize webelement
	public LoginPage() {
		PageFactory.initElements(WebDriverFactory.getWebDriver(), this);
	}

	// page loaded method
	public LoginPage isLoginPageLoaded() {
		objwait.until(ExpectedConditions.elementToBeClickable(vBtnLogin));
		objwait.until(ExpectedConditions.elementToBeClickable(vPaswd));
		objwait.until(ExpectedConditions.elementToBeClickable(vUserName));
		return this;
	}

	// Generic methods
	public HomePageAfterLogin checkValidLogin(String user, String paswd) {
		vUserName.sendKeys(user);
		vPaswd.sendKeys(paswd);
		vBtnLogin.click();

		// on successful login it should go to homepage.
		return (new HomePageAfterLogin().isHomePageLoaded());
	}

}
