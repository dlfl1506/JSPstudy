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
import com.cos.hello.service.UsersService;


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

		UsersService usersService = new UsersService();
		
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
			usersService.회원가입(req, resp);
		} else if (gubun.equals("loginProc")) {
			usersService.로그인(req, resp);
			
		}
	}
		}	
