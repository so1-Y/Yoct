package model;

import java.io.Serializable;

/**
 * 新規ユーザー登録用のUsersテーブルに対応した石原さん作成のJavaBeans。
 * 本来のUsersBeanと異なりuserNumberがString型となっているので
 * 他のソースでは使用禁止。
 */
public class UsersBeanX implements Serializable  {
	private String userNumber;
	private String userName;
	private String password;
	private String area;


	public UsersBeanX(){}
    public UsersBeanX(String userNumber, String userName, String password,String area) {
    	this.userNumber = userNumber;
    	this.userName = userName;
    	this.password = password;
    	this.area = area;
	}

	public String getUserNumber() {return userNumber;}
    public String getUserName() {return userName;}
    public String getPassword() {return password;}
    public String getArea() {return area;}
}
