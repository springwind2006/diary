package cn.stwms.action;

public class DiaryAction extends Action{

	private static final long serialVersionUID = 1L;
	private String errcode="0";
	private String errmsg="ok";
	private Object data=null;
	
	
	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	

	public String list() {
		return SUCCESS;
	}
	
	public String save() {
		data=request.getParameter("id");
		System.out.println("public String save()");
		return SUCCESS;
	}

	public String sync() {
		
		return SUCCESS;
	}
	
}
