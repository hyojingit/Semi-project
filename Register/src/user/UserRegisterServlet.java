package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/UserRegisetServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html;charset=UTF-8");
		String userID = request.getParameter("userID");
		String userPassword1 = request.getParameter("userPassword1");
		String userPassword2 = request.getParameter("userPassword2");
		String userName = request.getParameter("userName");
		String userAge = request.getParameter("userAge");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");
		
//		System.out.println(request.getParameter("userID"));
//		System.out.println(request.getParameter("userPassword1"));
//		System.out.println(request.getParameter("userPassword2"));
//		System.out.println(request.getParameter("userName"));
//		System.out.println(request.getParameter("userAge"));
//		System.out.println(request.getParameter("userGender"));
//		System.out.println(request.getParameter("userEmail"));
		if(userID == null || userID.equals("") || userPassword1 == null || userPassword1.equals("") || 
				userPassword2 == null || userPassword2.equals("") || userName == null || userName.equals("") || 
				userAge == null || userAge.equals("") || userGender == null || userGender.equals("") || 
				userEmail == null || userEmail.equals("")) {
			request.getSession().setAttribute("messageType", "오류메세지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			response.sendRedirect("index.jsp");
			return;
		}
		if(!userPassword1.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "오류메세지");
			request.getSession().setAttribute("messageContent", "비밀번호가 서로 일치하지 않습니다.");
			response.sendRedirect("index.jsp");
			return;
		}
		UserDAO dao = new UserDAO();
		int result  = dao.register(userID, userPassword1, userName, userAge, userGender, userEmail);
		if(result == 1) {
			request.getSession().setAttribute("messageType", "성공메세지");
			request.getSession().setAttribute("messageContent", "회원가입에 성공하였습니다.");
			response.sendRedirect("index.jsp");
			return;
		} else {
			request.getSession().setAttribute("messageType", "오류메세지");
			request.getSession().setAttribute("messageContent", "이미 존재하는 회원입니다.");
			response.sendRedirect("index.jsp");
			return;
		}
	}

}