package activiti_demo;

import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.activiti.cfg.ConfigurationCreator;

public class TestConfigurationCreator {

	private final Logger log = LoggerFactory.getLogger(TestConfigurationCreator.class);
	
	@Test
	public void testCreatByXml() {
		ProcessEngineConfiguration cfg = ConfigurationCreator.createByXml();
		log.debug(cfg.toString());
		log.debug(cfg.getJdbcDriver());
	}
	
	@Test
	public void testCreateStandalone() {
		ProcessEngineConfiguration cfg = ConfigurationCreator.createStandalone();
		log.debug(cfg.toString());
		log.debug(cfg.getJdbcDriver());
	}
}
