package test.activiti.service;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.activiti.cfg.ConfigurationCreator;

public class ActivitiService {

	private final Logger log = LoggerFactory.getLogger(ActivitiService.class);

	RepositoryService repositoryService;

	RuntimeService runtimeService;

	TaskService taskService;

	HistoryService historyService;
	
	public ActivitiService() {
		ProcessEngineConfiguration cfg = ConfigurationCreator.createByXml();
		ProcessEngine processEngine = cfg.buildProcessEngine();
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
		historyService = processEngine.getHistoryService();
	}
	
	public void deployProcess(String resource) {
		repositoryService.createDeployment().addClasspathResource(resource).deploy();
		log.debug("deploy: " + resource);
	}
}
