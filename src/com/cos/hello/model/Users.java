package com.cos.hello.model;

import javax.servlet.annotation.WebInitParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Users {
	private int id;
	private String username;
	private String password;
	private String email;

}
