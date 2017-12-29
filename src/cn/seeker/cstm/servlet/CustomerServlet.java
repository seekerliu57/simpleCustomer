package cn.seeker.cstm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;
import cn.seeker.cstm.domain.Customer;
import cn.seeker.cstm.service.CustomerService;

/**
 * web层
 *
 */
public class CustomerServlet extends BaseServlet {

	private CustomerService customerService = new CustomerService();

	/**
	 * 添加客户
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		
		System.out.println(c);
		
		c.setCid(CommonUtils.uuid());
		customerService.add(c);
		request.setAttribute("msg", "添加客户成功！");
		
		return "f:/msg.jsp";
	}
	
	/**
	 * 查询所有
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("cstmList", customerService.findAll());
		
		return "f:/list.jsp";
	}
}
