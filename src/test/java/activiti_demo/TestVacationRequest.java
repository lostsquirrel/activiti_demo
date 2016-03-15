package activiti_demo;

import org.junit.Before;
import org.junit.Test;

import test.activiti.service.ActivitiService;

public class TestVacationRequest {

	ActivitiService activitiService;
	
	@Before
	public void testerInit() {
		activitiService = new ActivitiService();
	}
	
	@Test
	public void testDeploy() throws Exception {
		String res = "VacationRequest.bpmn20.xml";
		activitiService.deployProcess(res);
	}
}
