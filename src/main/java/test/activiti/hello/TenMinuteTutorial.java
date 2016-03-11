package test.activiti.hello;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

public class TenMinuteTutorial {

	private ProcessEngine processEngine;
	
	private TaskService taskService;

	@Before
	public void initProcessEngine() {
		// Create Activiti process engine
		// ProcessEngine processEngine =
		// ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
		// .buildProcessEngine();
		String resource = "db.properties";
		ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		 cfg.setJdbcDriver("com.mysql.jdbc.Driver");
		 cfg.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8");
		 cfg.setJdbcUsername("activiti");
		 cfg.setJdbcPassword("activiti");
		 cfg.setDatabaseType("mysql");
//		cfg.setJdbcDriver("org.h2.Driver");
//		cfg.setJdbcUrl("jdbc.url=jdbc:h2:tcp://localhost/activiti");
//		cfg.setJdbcUsername("sa");
//		cfg.setJdbcPassword("");
		cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		processEngine = cfg.buildProcessEngine();
		
		taskService = processEngine.getTaskService();

	}

	@Test
	public void testDeploy() {
		// Get Activiti services
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// Deploy the process definition
		repositoryService.createDeployment().addClasspathResource("FinancialReportProcess.bpmn20.xml").deploy();
	}

	@Test
	public void testStart() {
		RuntimeService runtimeService = processEngine.getRuntimeService();
		// Start a process instance
		String procId = runtimeService.startProcessInstanceByKey("financialReport").getId();
		System.out.println(procId);
	}

	@Test
	public void testGet() {
		// Get the first task
		
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
		for (Task task : tasks) {
			System.out.println("Following task is available for accountancy group: " + task.getName());

			// claim it
			taskService.claim(task.getId(), "fozzie");
		}
	}

	@Test
	public void retrieveTask() {
		// Verify Fozzie can now retrieve the task
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
		for (Task task : tasks) {
			System.out.println("Task for fozzie: " + task.getName());

			// Complete the task
			taskService.complete(task.getId());
		}

		System.out
				.println("Number of tasks for fozzie: " + taskService.createTaskQuery().taskAssignee("fozzie").count());

	}

	@Test
	public void testST() {
		// Retrieve and claim the second task
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
			System.out.println("Following task is available for management group: " + task.getName());
			taskService.claim(task.getId(), "kermit");
			
		}

	}

	@Test
	public void completTask() {
		// Completing the second task ends the process
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("Management").list();
		for (Task task : tasks) {
			taskService.complete(task.getId());
			System.out.println("complete task:" + task.getId());
		}

	}

	public void history() {

		// verify that the process is actually finished
		HistoryService historyService = processEngine.getHistoryService();
		String procId = "22501";
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(procId ).singleResult();
		System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
	}
}
