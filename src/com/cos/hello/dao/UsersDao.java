package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.hello.config.DBConn;
import com.cos.hello.dto.JoinDto;
import com.cos.hello.dto.LoginDto;
import com.cos.hello.model.Users;




public class UsersDao {

	public Users selectById(int id) {
		
		StringBuffer sb = new StringBuffer();  
		// 긴문장을 적을때는 스트링버퍼를 사용한다
		// 스트링전용 컬렉션 
		sb.append("SELECT id,password,username,email FROM users WHERE id = ?");
		String sql = sb.toString();
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.password(rs.getString("password"))
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

	
	
	public Users login(LoginDto loginDto) {
	
				StringBuffer sb = new StringBuffer();  
				// 긴문장을 적을때는 스트링버퍼를 사용한다
				// 스트링전용 컬렉션 
				sb.append("SELECT id,username,email FROM users WHERE username = ? AND password = ?");
				String sql = sb.toString();
				Connection conn = DBConn.getInstance();
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, loginDto.getUsername());
					pstmt.setString(2, loginDto.getPassword());
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

	public int insert(JoinDto joindto) {
		
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
			pstmt.setString(1, joindto.getUsername());
			pstmt.setString(2, joindto.getPassword());
			pstmt.setString(3, joindto.getEmail());
			int result = pstmt.executeUpdate(); // 변경된 행의 개수를 리턴
			
			return result;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return -1;
	}
	
public int update(Users user) {
		
		// 2번 DB에 연결해서 3가지 값을 INSERT 하기
		StringBuffer sb = new StringBuffer();  
		// 긴문장을 적을때는 스트링버퍼를 사용한다
		// 스트링전용 컬렉션 
		sb.append("update users set password=? ,email=? where id=?");
		
		String sql = sb.toString();
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setInt(3, user.getId());
			int result = pstmt.executeUpdate(); // 변경된 행의 개수를 리턴
			
			return result;
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return -1;
	}

public int delete(int id) {
	
	// 2번 DB에 연결해서 3가지 값을 INSERT 하기
	StringBuffer sb = new StringBuffer();  
	// 긴문장을 적을때는 스트링버퍼를 사용한다
	// 스트링전용 컬렉션 
	sb.append("DELETE FROM users WHERE id=?");
	
	String sql = sb.toString();
	Connection conn = DBConn.getInstance();
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
	
		pstmt.setInt(1,id);
		int result = pstmt.executeUpdate(); // 변경된 행의 개수를 리턴
		return result;

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
return -1;
}
}
