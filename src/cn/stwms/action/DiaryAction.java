package cn.stwms.action;

public class DiaryAction extends Action{

	private static final long serialVersionUID = 1L;
	

	public String list() {
		return SUCCESS;
	}
	
	public String save() {
		System.out.println("public String save()");
		return SUCCESS;
	}

	public String sync() {
		return SUCCESS;
	}
	
}
