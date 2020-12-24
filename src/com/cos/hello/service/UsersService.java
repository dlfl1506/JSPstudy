package com.cos.hello.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;

public class UsersService {
	
	public void 로그인(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 데이터원형 username= ssar&password=1234&email=ssar@nate.com
		// 1번 form의 input 태그에 있는 3가지 값 username,password,email 받기
		String username = req.getParameter("username");
		String password = req.getParameter("password");
	
		System.out.println("==================loginProc start ================");
		System.out.println(username);
		System.out.println(password);
		System.out.println("==================loginProc End ================");
		
		// getParameter 함수는 get방식의 데이터와 post 방식의 데이터를 다 받을 수 있음
		// post방식에서는 데이터 타입이 x-www-form-urlencoded 방식만 받을 수 있음.
		Users user = Users.builder().username(username).password(password).build();
		UsersDao usersDao = new UsersDao();
		Users userEntity = usersDao.login(user);
		
		if(userEntity !=null) {
			HttpSession session = req.getSession(); // 세션 영역에 접근 힙메모리
			session.setAttribute("sessionUser", userEntity);
			resp.sendRedirect("index.jsp");
		}else {
			resp.sendRedirect("auth/login.jsp");
		}
		
	}
	
	
	public void 회원가입(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 데이터원형 username= ssar&password=1234&email=ssar@nate.com

		// 1번 form의 input 태그에 있는 3가지 값 username,password,email 받기
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		// getParameter 함수는 get방식의 데이터와 post 방식의 데이터를 다 받을 수 있음
		// post방식에서는 데이터 타입이 x-www-form-urlencoded 방식만 받을 수 있음.

		Users user = Users.builder().username(username).password(password).email(email).build();
		UsersDao usersDao = new UsersDao();
		int result = usersDao.insert(user);
		if (result ==1) {
			// 3번 INSERT가 정상적으로 되었다면 index.jsp를 응답!!
			resp.sendRedirect("auth/login.jsp");
		}else {
			resp.sendRedirect("auth/join.jsp");
		}
	
	}
}
