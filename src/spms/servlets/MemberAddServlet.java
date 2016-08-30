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
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberForm.jsp");
		rd.forward(request, response);
		
		/* convert to comment at page 289
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Register Member</title></head>");
		out.println("<body><h1>Register Member</h1>");
		out.println("<form action='add' method='post'>");
		out.println("Name: <input type='text' name='name'><br>");
		out.println("Email: <input type='text' name='email'><br>");
		out.println("Password: <input type='password' name='password'><br>");
		out.println("<input type='submit' value='add'>");
		out.println("<input type='reset' value='cancel'>");
		out.println("</form>");
		out.println("</body></html>");
		*/
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {		
		//Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();

			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			dao.insert(new Member().setName(request.getParameter("name"))
					.setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("password")));
			
			/* after page 384, commented (converted to DAO)
			stmt = conn.prepareStatement(
				"insert into MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)"
				+ " values(?, ?, ?, NOW(), NOW())"
			);
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("name"));
			stmt.executeUpdate();
			*/
			
			response.sendRedirect("list");
			
			/* Test for redirect
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Result of adding member</title>");
			out.println("<meta http-equiv='Refresh' content='1' url='list'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>Succeed!!</p>");
			out.println("</body></html>");
			*/
			
			//response.addHeader("Refresh", "1;url=list");
		} catch (Exception e) {
			//throw new ServletException(e);
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
	}
}
