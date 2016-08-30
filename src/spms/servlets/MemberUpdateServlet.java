package spms.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
@WebServlet(urlPatterns={"/member/update"})
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			
			/* Remove due to page 395 exercise
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao dao = new MemberDao();
			dao.setConnection(conn);
			*/
			
			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			Member member = dao.selectOne(Integer.parseInt(request.getParameter("no")));
			
			/* remove at page 384
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
				"select mno, email, mname, cre_date from members" +
				" where mno = " + request.getParameter("no"));
			rs.next();
			
			Member member = new Member();
			member.setNo(rs.getInt("mno"))
				.setEmail(rs.getString("email"))
				.setName(rs.getString("mname"))
				.setCreatedDate(rs.getDate("cre_date"));	
			*/
			
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.forward(request, response);
			
			/* Commented at page 337
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Information of member</title></head>");
			out.println("<body><h1>Information of member</h1>");
			out.println("<form action='update' method='post'>");
			out.println("Number: <input type='text' name='no' value='" + 
				request.getParameter("no") + "' readonly><br>");
			out.println("Name: <input type='text' name='name' value='" +
				rs.getString("mname") + "'><br>");
			out.println("EMail: <input type='text' name='email' value='" +
				rs.getString("email") + "'><br>");
			out.println("Register Date: " + rs.getDate("cre_date") + "<br>");
			out.println("<input type='submit' value='Save'>");
			out.println("<input type='button' value='Delete'" +
				" onclick='location.href=\"delete?no=" + request.getParameter("no") + "\"'>");
			out.println("<input type='button' value='Cancel'" +
				" onclick='location.href=\"list\"'>");
			out.println("</form>");
			out.println("</body></html>");
			*/
		} catch (Exception e) {
			//throw new ServletException(e);
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

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
			
			Member member = new Member().setName(request.getParameter("name"))
				.setEmail(request.getParameter("email"))
				.setNo(Integer.parseInt(request.getParameter("no")));

			dao.update(member);
			
			/* Remove the code at page 384 excercise
			stmt = conn.prepareStatement(
				"update members set email=?, mname=?, mod_date=now() " +
				"where mno=?");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("name"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
			stmt.executeUpdate();
			*/
			
			response.sendRedirect("list");
		} catch (Exception e) {
			//throw new ServletException(e);
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (stmt != null) { stmt.close(); }} catch(Exception e) {}
		}
		
	}
}

