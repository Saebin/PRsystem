package vo;

import java.util.Date;

public class Notice {
	protected int 		noti_number;
	protected String 	noti_comment;
	protected String 	subj_code_div;
	protected int 		prof_id;
	protected Date		noti_cre_date;
	protected Date		noti_mod_date;
	protected String	noti_name;
	protected int		noti_del;
	
	public int getNoti_del() {
		return noti_del;
	}
	public Notice setNoti_del(int noti_del) {
		this.noti_del = noti_del;
		return this;
	}
	public String getNoti_name() {
		return noti_name;
	}
	public Notice setNoti_name(String noti_name) {
		this.noti_name = noti_name;
		return this;
	}
	public int getNoti_number() {
		return noti_number;
	}
	public Notice setNoti_number(int noti_number) {
		this.noti_number = noti_number;
		return this;
	}
	public String getNoti_comment() {
		return noti_comment;
	}
	public Notice setNoti_comment(String noti_comment) {
		this.noti_comment = noti_comment;
		return this;
	}
	public String getSubj_code_div() {
		return subj_code_div;
	}
	public Notice setSubj_code_div(String subj_code_div) {
		this.subj_code_div = subj_code_div;
		return this;
	}
	public int getProf_id() {
		return prof_id;
	}
	public Notice setProf_id(int prof_id) {
		this.prof_id = prof_id;
		return this;
	}
	public Date getNoti_cre_date() {
		return noti_cre_date;
	}
	public Notice setNoti_cre_date(Date noti_cre_date) {
		this.noti_cre_date = noti_cre_date;
		return this;
	}
	public Date getNoti_mod_date() {
		return noti_mod_date;
	}
	public Notice setNoti_mod_date(Date noti_mod_date) {
		this.noti_mod_date = noti_mod_date;
		return this;
	}
}
