package cn.seeker.cstm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
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
	/**
	 * 编辑之前的加载工作
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cid = request.getParameter("cid");
		Customer cstm = customerService.findByCid(cid);
		request.setAttribute("cstm", cstm);
		return "f:/edit.jsp";
	}
	/**
	 * 编辑方法
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Customer cstm = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customerService.edit(cstm);
		request.setAttribute("msg", "编辑客户成功！");
		return "f:/msg.jsp";
	}
	/**
	 * 删除方法
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cid = request.getParameter("cid");
		customerService.delete(cid);
		request.setAttribute("msg", "删除客户成功！");
		return "f:/msg.jsp";
	}
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		List<Customer> list = customerService.query(c);
		request.setAttribute("cstmList", list);
		return "f:/list.jsp";
	}
}
