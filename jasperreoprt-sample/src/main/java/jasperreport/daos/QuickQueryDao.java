package jasperreport.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import jasperreport.domains.QuickQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * .
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-05 3:38 PM
 * @since JDK 1.5
 */
@Repository
public class QuickQueryDao {


	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public QuickQueryDao(DataSourceTransactionManager transactionManager) {
		super();
		final DataSource dataSource = transactionManager.getDataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
	}




	public List<QuickQuery> find_quickQuery(){
		String sql = "select UID,uname,upassword,role from Users where uid = ?";
		final List<QuickQuery> quickQueries = new ArrayList<QuickQuery>();
//		jdbcTemplate.query(sql, new RowCallbackHandler() {
//			public void processRow(ResultSet rs) throws SQLException {
//				QuickQuery quickQuery = new QuickQuery();
//				quickQuery.setRole(rs.getInt("role"));
//				quickQuery.setUname(rs.getString("uname"));
//				quickQuery.setUpassword(rs.getString("upassword"));
//				quickQuery.setUid(rs.getInt("uid"));
//				quickQueries.add(quickQuery);
//			}
//		});
		return quickQueries;
	}
}
