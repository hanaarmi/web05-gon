package spms.servlets;

import java.io.IOException;
//import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;


@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		try {
			ServletContext sc = this.getServletContext();
			
			// Removed at page 393 due to change from AppInitServlet to 
			// ServletContextListener
			//Connection conn = (Connection)sc.getAttribute("conn");
			//MemberDao memberDao = new MemberDao();
			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			request.setAttribute("members", memberDao.selectList());

			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher(
				"/member/MemberList.jsp");
			rd.include(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}
