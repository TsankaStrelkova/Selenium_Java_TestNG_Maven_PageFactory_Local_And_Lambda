package listeners;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import base.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class ExtentListener extends BaseTest implements ITestListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\target\\reports\\"+fileName);
	
	public static ExtentTest test;


	public void onTestStart(ITestResult result) {


	    // get parameters to list them in the report
		ArrayList <String> paramArray = new ArrayList<>();
		for (Object ob:result.getParameters()) {
			      paramArray.add(ob.toString());
		      }
		String allParameters="";
		if (paramArray.size()>0)
		allParameters = paramArray.toString();
		// end of get parameters

		test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName()+" "+allParameters);


	}

	public void onTestSuccess(ITestResult result) {

		
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName+ " PASSED"+"</b>";
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
		

	}

	public void onTestFailure(ITestResult result) {

		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.fail(exceptionMessage);


		ExtentManager.captureScreenshot();

		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName+ " FAILED"+"</b>";
	
		try {
			
			String screenshot = ExtentManager.screenshotFileName;
			test.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshot)
							.build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.log(Status.FAIL, m);
		
		

	}

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

}
