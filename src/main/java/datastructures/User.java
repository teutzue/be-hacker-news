package datastructures;

public class User {
	private String user_name;
	private String user_pwd;

	public User() {
		
	}
	
	
	
	/**
	 * @param user_name
	 * @param user_pwd
	 */
	public User(String user_name, String user_pwd) {
		this.user_name = user_name;
		this.user_pwd = user_pwd;
	}



	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

}
