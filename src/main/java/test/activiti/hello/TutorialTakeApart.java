package test.activiti.hello;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.activiti.cfg.ConfigurationCreator;

public class TutorialTakeApart {

	private final Logger log = LoggerFactory.getLogger(TutorialTakeApart.class);

	RepositoryService repositoryService;

	RuntimeService runtimeService;

	TaskService taskService;

	HistoryService historyService;
	
	@Before
	public void initTesting() {
		ProcessEngineConfiguration cfg = ConfigurationCreator.createByXml();
		ProcessEngine processEngine = cfg.buildProcessEngine();
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
		historyService = processEngine.getHistoryService();
	}

	@Test
	public void testDeploy() {
		// Deploy the process definition
		String resource = "FinancialReportProcess.bpmn20.xml";
		deployProcess(resource);
	}

	private void deployProcess(String resource) {
		repositoryService.createDeployment().addClasspathResource(resource).deploy();
	}

	@Test
	public void testStartProcess() throws Exception {
		ProcessInstance p = runtimeService.startProcessInstanceByKey("financialReport");
		log.debug("start process id: " + p.getId());
	}

	@Test
	public void testGetTasks() throws Exception {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
		for (Task task : tasks) {
			log.debug("Following task is available for accountancy group: " + task.getName());
		}
	}

	@Test
	public void testClaimTasks() throws Exception {
		String userId = "fozzie";
		String candidateGroup = "accountancy";
		userId = "kemit";
		candidateGroup = "management";
		claimFromGroup(userId, candidateGroup);
	}

	private void claimFromGroup(String userId, String candidateGroup) {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).list();
		for (Task task : tasks) {
			log.debug("claim task for " + candidateGroup + " group: " + task.getName());
			taskService.claim(task.getId(), userId);
		}
	}

	@Test
	public void testGetAssigedTasks() throws Exception {
		// Verify Fozzie can now retrieve the task
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
		for (Task task : tasks) {
			log.debug("Task for fozzie: " + task.getName());
		}
	}

	@Test
	public void testCompleteAssigedTasks() throws Exception {
		// Verify Fozzie can now retrieve the task
		String assignee = "fozzie";
		assignee = "kemit";
		completeAssignedTasks(assignee);
	}

	private void completeAssignedTasks(String assignee) {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
		for (Task task : tasks) {
			log.debug("complete task for " + assignee + ": " + task.getName());
			taskService.complete(task.getId());
		}
	}

	@Test
	public void testCountAssigedTasks() throws Exception {
		log.debug("Number of tasks for fozzie: " + taskService.createTaskQuery().taskAssignee("fozzie").count());
		log.debug("Number of tasks for gonzo: " + taskService.createTaskQuery().taskAssignee("gonzo").count());
		log.debug("Number of tasks for kemit: " + taskService.createTaskQuery().taskAssignee("kemit").count());
	}
	
	@Test
	public void testFinished() throws Exception {
		String procId = "7501";
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(procId ).singleResult();
		log.debug("Process instance end time: " + historicProcessInstance.getEndTime());
	}
}
