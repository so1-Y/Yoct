package model;

import java.io.Serializable;

/**
 * Usersテーブルの1レコードを表すJavaBeans
 */
public class OldUsersBean implements Serializable {

	private int userNumber;
	private String  userName ;
	private String  password ;
	private String area;

	public OldUsersBean(int userNumber, String userName, String password, String area) {
		super();
		this.userNumber = userNumber;
		this.userName = userName;
		this.password = password;
		this.area = area;
	}


	public OldUsersBean(int userNumber, String userName) {
		this.userNumber = userNumber;
		this.userName = userName;
	}


	public OldUsersBean() {}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	};

}