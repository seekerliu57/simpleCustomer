package cn.seeker.cstm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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
	/**
	 * 通过cid查询
	 * @param cid
	 */
	public  Customer findByCid(String cid) {
		
		try {
			String sql = "select * from t_customer where cid =?";
			return qr.query(sql, new BeanHandler<Customer>(Customer.class),cid);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void edit(Customer c) {
		try {
			String sql = "update t_customer set cname=?,gender=?," +
					"birthday=?,cellphone=?,email=?,description=?" +
					" where cid=?";
			Object [] params = {c.getCname(),c.getGender(),
					c.getBirthday(),c.getCellphone(),c.getEmail(),
					c.getDescription(),c.getCid()};
			qr.update(sql,params);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void delete(String cid) {
		try {
			String sql = "delete from t_customer where cid =?";
			qr.update(sql,cid);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Customer> query(Customer c) {

		try {
			StringBuilder sql = new StringBuilder("select * from t_customer where 1=1");
			
			ArrayList<String> params = new ArrayList<String>();
			
			if(c.getCname() != null && !c.getCname().trim().isEmpty()){
				sql.append(" and cname like ?");
				params.add("%"+c.getCname()+"%");
			}
			if(c.getGender() != null && !c.getGender().trim().isEmpty()){
				sql.append(" and gender like ?");
				params.add("%"+c.getGender()+"%");
			}
			if(c.getCellphone() != null && !c.getCellphone().trim().isEmpty()){
				sql.append(" and cellphone like ?");
				params.add("%"+c.getCellphone()+"%");
			}
			if(c.getEmail() != null && !c.getEmail().trim().isEmpty()){
				sql.append(" and email like ?");
				params.add("%"+c.getEmail()+"%");
			}
			
			return qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class),params.toArray());
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
