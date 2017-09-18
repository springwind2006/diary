package cn.stwms.model;

public class Diary{
	private int id;
	private String title;
	private String subtitle;
	private String content;
	private String weather;
	private short emote;
	private int addtime;
	private int edittime;
	private int sync_id;
	private int userid;
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setSubtitle(String subtitle){
		this.subtitle=subtitle;
	}
	public String getSubtitle(){
		return subtitle;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setWeather(String weather){
		this.weather=weather;
	}
	public String getWeather(){
		return weather;
	}
	public void setEmote(short emote){
		this.emote=emote;
	}
	public short getEmote(){
		return emote;
	}
	public void setAddtime(int addtime){
		this.addtime=addtime;
	}
	public int getAddtime(){
		return addtime;
	}
	public void setEdittime(int edittime){
		this.edittime=edittime;
	}
	public int getEdittime(){
		return edittime;
	}
	public void setSync_id(int sync_id){
		this.sync_id=sync_id;
	}
	public int getSync_id(){
		return sync_id;
	}
	public void setUserid(int userid){
		this.userid=userid;
	}
	public int getUserid(){
		return userid;
	}
}
