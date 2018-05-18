package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import cn.edu.nyist.bookmanv1.biz.BookBiz;
import cn.edu.nyist.bookmanv1.biz.impl.BookBizImpl;
import cn.edu.nyist.bookmanv1.util.MyBeanUtils;
import cn.edu.nyist.bookmanv1.vo.BookVo;


@WebServlet("/bookAdd")
@MultipartConfig
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BookAddServlet() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       Part part= request.getPart("photo");
       String fileName=part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
       fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);

   	
		String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		String newFileName = UUID.randomUUID().toString() + "." + ext;
		part.write(request.getServletContext().getRealPath("upload/" + newFileName));
       
		/*String name=request.getParameter("name");
		String strPrice=request.getParameter("price");
		System.out.println(strPrice);
		double price =Double.parseDouble(strPrice);
		String descri=request.getParameter("descri");
		String photo=request.getParameter("newFileName");
		String author =request.getParameter("author");
		String strTid=request.getParameter("tid");
		int tid=Integer.parseInt(strTid);
		String strPubDate=request.getParameter("pubDate");
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date pubDate=null;
		try {
			pubDate=dateFormat.parse(strPubDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//调用业务层
		BookVo bookVo= new BookVo();
		/*try {
			BeanUtils.populate(bookVo,request.getParameterMap());
		
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}*/
		MyBeanUtils.populate(bookVo, request.getParameterMap(), "yyyy-MM-dd");
		bookVo.setPhoto(newFileName);
		BookBiz bookBiz=new BookBizImpl();
	
		int ret = bookBiz.saveBook(bookVo);
		
		response.setContentType("text/html;charset=utf-8");
		if (ret > 0) {
			response.getWriter().write("添加成功");
			}
		else {
			request.setAttribute("msg", "添加失败");
			request.getRequestDispatcher("bookAdd.jsp").forward(request, response);
		}
	}

}
