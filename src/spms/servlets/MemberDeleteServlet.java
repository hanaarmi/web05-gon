package spms.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
			
			/* Remove due to page 395 exercise
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao dao = new MemberDao();
			dao.setConnection(conn);
			*/
			
			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			
			dao.delete(Integer.parseInt(request.getParameter("no")));
			
			/* Remove the code at page 384 exercise
			stmt = conn.prepareStatement("delete from members where mno=?");
			stmt.setInt(1, Integer.parseInt(request.getParameter("no")));
			stmt.executeUpdate();
			*/
			
			response.sendRedirect("list");
		} catch (Exception e) {
			//throw new ServletException(e);
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
		}
		
	}
}
