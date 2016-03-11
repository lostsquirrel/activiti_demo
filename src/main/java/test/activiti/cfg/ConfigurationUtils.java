package test.activiti.cfg;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

public class ConfigurationUtils {

	public static ProcessEngine createStandaloneInMemProcessEngine() {
		ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
				  .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
				  .setJdbcUrl("jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000")
				  .setAsyncExecutorEnabled(true)
				  .setAsyncExecutorActivate(false)
				  .buildProcessEngine();
		
		return processEngine;
	}
}
