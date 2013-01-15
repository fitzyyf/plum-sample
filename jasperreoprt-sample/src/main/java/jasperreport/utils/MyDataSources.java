package jasperreport.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * <p>
 * JasperReport 自定义数据源.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-05 4:13 PM
 * @since JDK 1.5
 */
public class MyDataSources implements JRDataSource {
	/** 传来的sql语句，填充数据集时用 */
	private final String sql;
	private final Connection con;
	/** 存放从数据库里查询出来的数据集,里面存放HashMap */
	private List<?> dataSet = Lists.newArrayList();
	private int index = -1;
	/** 记录数据集的列数 */
	private int cellCount = 0;

	public MyDataSources(Connection con, String sql) throws SQLException {
		this.con = con;
		this.sql = sql;
		dataSet = getClassCount();
	}

	public Connection getConn() {
		return con;
	}

	public List<?> getClassCount() throws SQLException {
		Map<String, Object> hm = null;
		List<Map<String, Object>> list = Lists.newArrayList(); //查询出来的数据集，返回给全局变量dataSet
		int a = 0; //  记录记录集的行数

		int smallAge[] = new int[6];
		int bigAge[] = new int[6];
		smallAge[0] = 0;
		smallAge[1] = 26;
		smallAge[2] = 36;
		smallAge[3] = 46;
		smallAge[4] = 56;
		smallAge[5] = 66;
		bigAge[0] = 25;
		bigAge[1] = 35;
		bigAge[2] = 45;
		bigAge[3] = 55;
		bigAge[4] = 65;
		bigAge[5] = 100;
		PreparedStatement ps = con.prepareStatement(sql);
		for (int count = 0; count < smallAge.length; count++) {
			ps.setInt(1, smallAge[count]);
			ps.setInt(2, bigAge[count]);
			ResultSet rs = ps.executeQuery();
//        Statement ps = con.createStatement();
//        ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				hm = Maps.newHashMap();
				ResultSetMetaData rsmd = rs.getMetaData();
				cellCount = rsmd.getColumnCount();
				for (int i = 1; i <= cellCount; i++) {
					String cellName = rsmd.getColumnName(i);
					if (i <= 2) {
						String dataStr = rs.getString(i);
						hm.put(cellName, dataStr);
					} else {
						Integer dataInt = rs.getInt(i);
						hm.put(cellName, dataInt);
					}
				}
				list.add(a, hm);
				a++;
			}
			System.out.println(count + ":==========" + smallAge[count] + "," +
					bigAge[count]);
		}

		return list;
	}

	@Override
	public boolean next() throws JRException {
		index++;
		return (index < dataSet.size());
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		//获得字段信息，是每个单元格的信息
		Object value = null;
		String fieldName = field.getName();
		HashMap rowData; //用来存放一条记录
		rowData = (HashMap) dataSet.get(index); //获得第index条记录
		for (int i = 0; i < cellCount; i++) {
			if (rowData.containsKey(fieldName)) {
				value = rowData.get(fieldName);
			}
			if (fieldName.equals("SUBREPORT_DIR"))
				value = "C:/Program Files/JasperSoft/iReport-1.3.0/";
		}
		return value;
	}
}
