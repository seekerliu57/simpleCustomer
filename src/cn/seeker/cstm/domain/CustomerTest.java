package cn.seeker.cstm.domain;

import cn.itcast.utils.CommonUtils;
import cn.seeker.cstm.dao.CustomerDao;

public class CustomerTest {

	public static void main(String[] args) {
		CustomerDao customerDao = new CustomerDao();
		for(int i=0;i<200;i++){
			Customer c = new Customer();
			
			c.setCid(CommonUtils.uuid());
			c.setBirthday("2015-05-07");
			c.setCellphone("18629039"+i);
			c.setCname("cstm" +i);
			c.setDescription("我是客户");
			c.setEmail("cstm" +i+"@163.com");
			c.setGender(i%2==0?"男":"女");
			
			customerDao.add(c);
		}
	}
}
