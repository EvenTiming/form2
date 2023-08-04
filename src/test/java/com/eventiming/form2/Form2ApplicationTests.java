package com.eventiming.form2;

import com.eventiming.form2.DAO.userdao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Form2ApplicationTests {
	@Autowired
	private userdao ud;

	@Test
	void testUseradd(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		long = new long("12313");
//
//		ud.insertUser(long,"eventime", "123@qq.com",
//				"123@email.com", timestamp);
	}
	@Test
	public void testSelectUserPasswordByUsername() {
		String username = "eventime";
		String expectedPassword = "123@qq.com";
		String actualPassword = ud.selectUserPasswordByUsername(username);
		assertEquals(expectedPassword, actualPassword);
	}

	@Test
	public void testSelectUserPasswordByEmail() {
		String email = "123@email.com";
		String expectpassword = "123@qq.com";
		String actualpassword = ud.selectUserPasswordByEmail(email);
		assertEquals(expectpassword, actualpassword);
	}

	@Test
	public void testSelectUserById() {

	}

	@Test
	public void testSelectUserByUsername() {

	}

	@Test
	public void testSelectUserByEmail() {

	}

	@Test
	public void testSelectUsersByRegisterTimeById() {

	}

	@Test
	public void testUpdateUserNameById() {

	}

	@Test
	public void testUpdateUserPasswordById() {

	}

	@Test
	public void testUpdateUserEmailById() {

	}

	@Test
	public void testDeleteUserById() {

	}
}


