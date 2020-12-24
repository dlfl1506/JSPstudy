package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.hello.config.DBConn;
import com.cos.hello.model.Users;




public class UsersDao {

	public Users login(Users user) {
	
				StringBuffer sb = new StringBuffer();  
				// 긴문장을 적을때는 스트링버퍼를 사용한다
				// 스트링전용 컬렉션 
				sb.append("SELECT id,username,email FROM users WHERE username = ? AND password = ?");
				String sql = sb.toString();
				Connection conn = DBConn.getInstance();
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, user.getUsername());
					pstmt.setString(2, user.getPassword());
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						Users userEntity = Users.builder()
								.id(rs.getInt("id"))
								.username(rs.getString("username"))
								
								.email(rs.getString("email"))
								.build();
						return userEntity;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
			}

	public int insert(Users user) {
		
		// 2번 DB에 연결해서 3가지 값을 INSERT 하기
		StringBuffer sb = new StringBuffer();  
		// 긴문장을 적을때는 스트링버퍼를 사용한다
		// 스트링전용 컬렉션 
		sb.append("INSERT INTO users(username,password,email) ");
		sb.append("VALUES(?,?,?)");
		String sql = sb.toString();
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			int result = pstmt.executeUpdate(); // 변경된 행의 개수를 리턴
			
			return result;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return -1;
	}
}
