package jasperreport.utils;

import java.sql.Connection;
import java.util.Map;
import javax.sql.DataSource;

import com.google.common.collect.Maps;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * .
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-05 4:21 PM
 * @since JDK 1.5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/app/applicationContext.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class MyDataSourcesTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private DataSourceTransactionManager transactionManager;

	@Test
	public void test_report() throws Exception {
		final DataSource dataSource = transactionManager.getDataSource();

		Connection con = dataSource.getConnection();
		JasperPrint jp;
		Map<String, Object> params = Maps.newHashMap();
		JasperReport reportFile = JasperCompileManager.compileReport("/iflytek/erms/xc归档文件目录_二维表格.jrxml");
		String sql = reportFile.getQuery().getText();
		jp = JasperFillManager.fillReport(reportFile, params,
				new MyDataSources(con, sql));
		JasperExportManager.exportReportToPdfFile(jp,
				"/iflytek/erms/xc归档文件目录_二维表格.pdf");
		JasperViewer.viewReport(jp);
	}

	@Test
	public void test_report_fill_connection() throws Exception {

		final DataSource dataSource = transactionManager.getDataSource();

		Connection con = dataSource.getConnection();
		JasperPrint jp;
		Map<String, Object> params = Maps.newHashMap();
		params.put("C_YEAR", 2012);
		params.put("C_RETENTIONPERIOD", "10年");
		params.put("PAGE_MIN_LINE", 13);
		params.put("CurOrgCode", "013XQ4LPLZ3HQI7V1HIFOS45MHBFWF");
		JasperReport reportFile = JasperCompileManager.compileReport("/iflytek/erms/xc归档文件目录_二维表格.jrxml");
		jp = JasperFillManager.fillReport(reportFile, params, con);
		JasperExportManager.exportReportToHtmlFile(jp,
				"/iflytek/erms/xc归档文件目录_二维表格.html");
		JasperExportManager.exportReportToPdfFile(jp,
				"/iflytek/erms/xc归档文件目录_二维表格.pdf");

		JasperViewer.viewReport(jp);
	}
}
