package com.orangehrm.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class ImplIinvokedListner implements IInvokedMethodListener {

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
		if(method.isTestMethod()){
		//System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Chrome78ver\\chromedriver.exe");
			//Browser version changed
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Chrome80ver\\chromedriver.exe");

		WebDriverFactory.setWebDriver(new ChromeDriver());
		
		//for extend reports
		ExtentReportTestFactory.createNewTest(method);
		
		// implicit wait setting
		WebDriverFactory.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebDriverFactory.getWebDriver().manage().window().maximize();

		WebDriverFactory.getWebDriver().get("http://127.0.0.1/orangehrm-3.3.1/symfony/web/index.php/auth/login");
		}
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub

		if(method.isTestMethod())
		{
			if(!testResult.isSuccess())
			{
				//screenshot for failed steps
				ExtentReportTestFactory.getTest().fail(testResult.getThrowable());
				SeleniumUtils.takeScreenshot(System.getProperty("user.dir")+"\\"+method.getTestMethod().getMethodName()+".png");
				try {
					ExtentReportTestFactory.getTest().addScreenCaptureFromPath(System.getProperty("user.dir")+"\\"+method.getTestMethod().getMethodName()+".png");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		// close dr after execution
		WebDriverFactory.getWebDriver().quit();
		}

	}

}
