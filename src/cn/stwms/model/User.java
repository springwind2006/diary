package cn.stwms.model;

public class User{
	private int id;
	private String username;
	private String password;
	private String email;
	private int addtime;
	private int lastlogin;
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
	}
	public void setAddtime(int addtime){
		this.addtime=addtime;
	}
	public int getAddtime(){
		return addtime;
	}
	public void setLastlogin(int lastlogin){
		this.lastlogin=lastlogin;
	}
	public int getLastlogin(){
		return lastlogin;
	}
}
