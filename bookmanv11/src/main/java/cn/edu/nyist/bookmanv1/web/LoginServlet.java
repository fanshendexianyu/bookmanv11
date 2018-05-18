package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.nyist.bookmanv1.biz.adminBIz;
import cn.edu.nyist.bookmanv1.biz.impl.adminBizimpl;
import cn.edu.nyist.bookmanv1.util.DsUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1��ȡ�û�����
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String vcode=request.getParameter("vcode");
		//ִ����֤��
		HttpSession session=request.getSession();
		String serverSession=(String) session.getAttribute("validateCode");
		//����ҵ���
		adminBIz adminBiz=new adminBizimpl();
		boolean ret = adminBiz.findAdminbyNameandPwd(name,pwd);
		//���û�һ����Ӧ
		if(ret) {
			response.sendRedirect("main.jsp");
		}else {
			request.setAttribute("msg", "�û������������");
			request.setAttribute("name", name);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
