package com.Atschool.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Atschool.Class.DocConverter;
import com.Atschool.JavaBean.UserPrintInfoAll;

/**
 * Servlet implementation class ConvertToSwf
 */
@WebServlet("/ConvertToSwf")
public class PreviewDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PreviewDocServlet() {
        super();
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户信息session范围
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		UserPrintInfoAll userPrintInfos=(UserPrintInfoAll)session.getAttribute("userPrintInfos");
		
		//获取预览按钮的id值，来判断预览那个文档
		int i=Integer.parseInt(request.getParameter("id"));
		String filePath = userPrintInfos.getInfo(i).getFilePath();
		//System.out.println("按钮的id="+i);
		
		//借助永中在线预览接口实现预览
		String userName=(String)session.getAttribute("userName");
		String fileName=userPrintInfos.getInfo(i).getFileReamName();
		String sendFilePath="http://139.196.234.4:8080/dcsUserCenter/checkUrl.do?"
				+ "k=30750321"
				+ "&url=http://115.159.30.21/IOTPrintFolder/"
				+ userName+"/"
				+ fileName;
		//response.sendRedirect(sendFilePath);
		request.getRequestDispatcher(sendFilePath).forward(request, response);
		
		/*
		try {
			filePath=filePath.replace("\\\\", "/");
			//执行文件转换
			DocConverter d = new DocConverter(filePath);
			d.conver();
			//生成swf相对路径，以便传递给flexpaper播放器  
			//绝对路径为        D:/userPrintFolder/nickname/document/test.swf
			//相对路径应该为    /userPrintFolder/nickname/document/test.swf
			//String swfFilePath = "/userPrintFolder/Tigerkin"+d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));
			String swfFilePath = d.getswfPath().substring(2);
			
			request.setAttribute("swfFilePath", swfFilePath);
			request.getRequestDispatcher("./WEB-INF/jsp/OnlinePreview.jsp").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("./WEB-INF/error.jsp").forward(request, response);
			e.printStackTrace();
		}*/
	}

}
