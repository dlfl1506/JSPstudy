package com.cos.hello.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
// javax 로 시작하는 패키지는 톰캣이 들고 있는 라이브러리
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.config.DBConn;
import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;


// 디스패쳐의 역할 = 분기 = 필요한 view를 응답해주는것 
public class UserController extends HttpServlet {

	// req와 res는 톰캣이 만들어줍니다. (클라이언트의 요청이 있을때 마다)
	// req는 BufferedReader 할 수 있는 ByteStream
	// res는 BufferedWriter 할 수 있는 ByteStream

	// http://localhost:8000/hello/front

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doProcess(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("UserController 실행됨");

		String gubun = req.getParameter("gubun");
		System.out.println(gubun);
		route(gubun, req, resp);
	}

	private void route(String gubun, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		if (gubun.equals("insertOne")) {
			resp.sendRedirect("user/insertOne.jsp");
		} else if (gubun.equals("selectOne")) {
			// 인증이 필요한페이지
			String result;
			HttpSession session = req.getSession();
			if (session.getAttribute("sessionUser") != null) {
			Users user = (Users) session.getAttribute("sessionUser");
				//System.out.println("인증되었습니다");
				result ="인증되었습니다.";
				System.out.println(user);
			} else {
				//System.out.println("인증되지 않았습니다.");
				result="인증되지않았습니다.";
			}
			// resp.sendRedirect("user/selectOne.jsp");
			req.setAttribute("result",result);
			RequestDispatcher dis = req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req, resp);
		} else if (gubun.equals("updateOne")) {
			resp.sendRedirect("user/updateOne.jsp");
		} else if (gubun.equals("login")) {
			resp.sendRedirect("auth/login.jsp");
		} else if (gubun.equals("join")) {
			resp.sendRedirect("auth/join.jsp");
		} else if (gubun.equals("joinProc")) {
			// 데이터원형 username= ssar&password=1234&email=ssar@nate.com

			// 1번 form의 input 태그에 있는 3가지 값 username,password,email 받기
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			// getParameter 함수는 get방식의 데이터와 post 방식의 데이터를 다 받을 수 있음
			// post방식에서는 데이터 타입이 x-www-form-urlencoded 방식만 받을 수 있음.
		
			Users user = Users.builder()
					.username(username)
					.password(password)
					.email(email)
					.build();
			UsersDao usersDao = new UsersDao();
			int result = usersDao.insert(user);
				if (result ==1) {
				// 3번 INSERT가 정상적으로 되었다면 index.jsp를 응답!!
				resp.sendRedirect("auth/login.jsp");
			}else {
				resp.sendRedirect("auth/join.jsp");
			}
		
			
		} else if (gubun.equals("loginProc")) {

			// 1번 전달되는 값 받기

			String username = req.getParameter("username");
			String password = req.getParameter("password");
			System.out.println("==================loginProc start ================");
			System.out.println(username);
			System.out.println(password);
			System.out.println("==================loginProc End ================");
			// 2번 데이터베이스 값이 있는 Select 해서 확인


			System.out.println("로그인성공");
			Users user = Users.builder().id(1).username(username).build();
			// 3번
			HttpSession session = req.getSession(); // 세션 영역에 접근 힙메모리
			// session 에는 사용자 패스워드 절대 넣지않기
			session.setAttribute("sessionUser", user);
			// 모든 응답에는 jSessionid가 쿠키로 응답
			// resp.setHeader("Set-Cookie", "sessionKey=9998");
			// 4번 index.jsp 페이지로 이동
			resp.sendRedirect("index.jsp");
		}
	}
		}	
	
