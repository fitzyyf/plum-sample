package services.impl;

import java.util.List;

import org.junit.Test;

/**
 * <p>
 * .
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-16 7:27 AM
 * @since JDK 1.5
 */
public class LogReadServiceImplTest {
	@Test
	public void testRead_log() throws Exception {

		LogReadServiceImpl logReadService = new LogReadServiceImpl();
		logReadService.setLog_folder("/iflytek/temp");
		List<String> contents = logReadService.read_log("jenkins-test", 199000);
		System.out.println(contents.size());
	}
}
