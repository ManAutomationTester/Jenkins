package jenkins;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestJenkins {
	
	@Test
	public void demoJenkinsMethod1() {
		System.out.println("demo Jenkins Method 1");
		
	}
	
	@AfterMethod
	public void demoJenkinsMethod2() {
		System.out.println("final report");
		Desktop desk=   Desktop.getDesktop();
      try {
		desk.open(new File("D:\\OstrumTech\\ostrumTech_WorkSpace\\jenkins\\test-output\\emailable-report.html"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
	}
	


}
