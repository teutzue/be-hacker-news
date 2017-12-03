package datastructures;

public class EditUserDTO {

	private String user_name;
	private String oldUser_pwd;
	private String newUser_pwd;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String oldUser_name) {
		this.user_name = oldUser_name;
	}
	public String getOldUser_pwd() {
		return oldUser_pwd;
	}
	public void setOldUser_pwd(String oldUser_pwd) {
		this.oldUser_pwd = oldUser_pwd;
	}
	public String getNewUser_pwd() {
		return newUser_pwd;
	}
	public void setNewUser_pwd(String newUser_pwd) {
		this.newUser_pwd = newUser_pwd;
	}
	
	
	
	
}
