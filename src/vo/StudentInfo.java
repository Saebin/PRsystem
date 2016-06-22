package vo;

import java.util.Date;

public class StudentInfo {

	protected int 		std_id;
	protected String 	std_name;
	protected String 	std_email;
	protected String 	std_pwd;
	protected String	std_phone;
	protected String	std_auth;
	protected Date		std_cre_date;
	protected Date		std_mod_date;
	protected String	std_token;
	
	
	public String getStdToken() {
		return std_token;
	}
	public StudentInfo setStdToken(String std_token) {
		this.std_token = std_token;
		return this;
	}
	public int getStdId() {
		return std_id;
	}
	public StudentInfo setStdId(int std_id) {
		this.std_id = std_id;
		return this;
	}
	
	public String getStdName() {
		return std_name;
	}
	public StudentInfo setStdName(String std_name) {
		this.std_name = std_name;
		return this;
	}
	public String getStdEmail() {
		return std_email;
	}
	public StudentInfo setStdEmail(String std_email) {
		this.std_email = std_email;
		return this;
	}
	public String getStdPwd() {
		return std_pwd;
	}
	public StudentInfo setStdPwd(String std_pwd) {
		this.std_pwd = std_pwd;
		return this;
	}
	public String getStdPhone() {
		return std_phone;
	}
	public StudentInfo setStdPhone(String std_phone) {
		this.std_phone = std_phone;
		return this;
	}
	public String getStdAuth() {
		return std_auth;
	}
	public StudentInfo setStdAuth(String std_auth) {
		this.std_auth = std_auth;
		return this;
	}
	public Date getStdCreDate() {
		return std_cre_date;
	}
	public StudentInfo setStdCreDate(Date std_cre_date) {
		this.std_cre_date = std_cre_date;
		return this;
	}
	public Date getStdModDate() {
		return std_mod_date;
	}
	public StudentInfo setStdModDate(Date std_mod_date) {
		this.std_mod_date = std_mod_date;
		return this;
	}
}
