package jasperreport.utils;

import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRStringUtil;

/**
 * <p>
 * .
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-05 4:52 PM
 * @since JDK 1.5
 */
public class Test {

	@org.junit.Test
	public void testName() throws Exception {
		JasperReport reportFile = JasperCompileManager.compileReport("/iflytek/erms/xc归档文件目录_二维表格.jrxml");
		JRQuery jrQuery = reportFile.getQuery();
		String language = jrQuery.getLanguage();
		System.out.println(language);
		System.out.println("");
		String sql = jrQuery.getText();
		System.out.println(sql);
		System.out.println("");
		String s1 = JRStringUtil.escapeJavaStringLiteral(sql);
		System.out.println(s1);

	}
}
