package com.cos.hello.config;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConn {

   public static Connection getInstance() {
      try {
         Context initContext = new InitialContext();
         Context envContext  = (Context)initContext.lookup("java:/comp/env");
         DataSource ds = (DataSource)envContext.lookup("mysql/ssar");
         Connection conn = ds.getConnection();
         System.out.println("연결성공");
         //etc.
         return conn;
      } catch (Exception e) {
         System.out.println("DB연결 실패" + e.getMessage());
      }
      return null;
   }
   
   public static Connection getInstance2() {
	      try {
	    	  Context initContext = new InitialContext();
	    	  Context envContext  = (Context)initContext.lookup("java:/comp/env");
	    	  DataSource ds = (DataSource)envContext.lookup("jdbc/insta");
	    	  Connection conn = ds.getConnection();
	    	  //etc.
	         System.out.println("연결성공");
	         //etc.
	         return conn;
	      } catch (Exception e) {
	         System.out.println("DB연결 실패" + e.getMessage());
	      }
	      return null;
	   }
   
  
}