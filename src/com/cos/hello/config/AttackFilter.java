package com.cos.hello.config;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


// 2번째순서 (마지막순서)
public class AttackFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// joinProc 일때
		System.out.println("attackfilter");
		
		HttpServletRequest req = (HttpServletRequest) request;
		String method = req.getMethod();
		System.out.println("method:"+method);
		if(method.equals("POST")) {
			String username =request.getParameter("username");
		username=username.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			System.out.println("username:"+username);
		
			// username에 <> 꺽쇠 들어오는것을 방어
			// 만약에 꺽쇠가 들어오면 &lt; &gt;
			// 다시 필터 타게할 예정
		
		}
		chain.doFilter(request, response);
		
//		BufferedReader br= request.getReader();
//		String input;
//		while((input=br.readLine())!=null) {
//			System.out.println(input);
//		}
//		String gubun = request.getParameter("gubun");
//		if(gubun.equals("joinProc")) {
//			String username = request.getParameter("username");
//		}
//	
//		System.out.println("공격 방어 필터 실행");
		
	}
	
	
}
