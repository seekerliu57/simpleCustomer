package cn.seeker.cstm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.seeker.cstm.domain.Customer;

/**
 * 持久层
 * @author 刘金祥
 *
 */
public class CustomerDao {

	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 添加用户
	 */
	public void add(Customer c ){
		try {
		String sql = "insert into t_customer (cid,cname,gender,birthday," +
				"cellphone,email,description)" +
				"values(?,?,?,?,?,?,?)";
		
		Object [] params = { c.getCid(),c.getCname(),c.getGender(),
				c.getBirthday(),c.getCellphone(),c.getEmail(),
				c.getDescription()
		};
		
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询所有
	 */
	public List<Customer> findAll(){
		
		try {
			String sql = "select * from t_customer";
			return qr.query(sql, new BeanListHandler<Customer>(Customer.class));
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
