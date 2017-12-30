package cn.seeker.cstm.service;

import java.util.List;

import cn.seeker.cstm.dao.CustomerDao;
import cn.seeker.cstm.domain.Customer;

public class CustomerService {

	private CustomerDao customerDao = new CustomerDao();
	
	/**
	 * 添加方法
	 */
	public void add(Customer c ){
		customerDao.add(c);
	}
	/**
	 * 查询所有
	 */
	public List<Customer> findAll(){
		return customerDao.findAll();
	}
	/**
	 * 通过cid查询
	 * @param cid
	 */
	public  Customer findByCid(String cid) {
		return customerDao.findByCid(cid);
	}
	public void edit(Customer c) {
		customerDao.edit(c);
	}
	public void delete(String cid) {
		customerDao.delete(cid);
	}
	public List<Customer> query(Customer c) {

		return customerDao.query(c);
	}
}
