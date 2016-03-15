package test.activiti.cfg;

import org.activiti.engine.ProcessEngineConfiguration;

public class ConfigurationCreator {

	public static ProcessEngineConfiguration createByXml() {
		return ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
	}
	
	public static ProcessEngineConfiguration createStandalone() {
		ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		cfg.setJdbcDriver("com.mysql.jdbc.Driver");  
		cfg.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_demo?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8");  
		cfg.setJdbcUsername("activiti");  
		cfg.setJdbcPassword("activiti");
		cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE); 
		return cfg;
	}
}
