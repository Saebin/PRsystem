package vo;

import java.util.Date;

public class StudentSubject {

	protected int 		std_id;
	protected String 	subj_code_div;
	protected Date 		regist_date;
	protected int 		del;
	protected int		task_score;
	protected String	task_comment;
	protected Date		cre_date;
	protected Date		mod_date;
	public int getStd_id() {
		return std_id;
	}
	public StudentSubject setStd_id(int std_id) {
		this.std_id = std_id;
		return this;
	}
	public String getSubj_code_div() {
		return subj_code_div;
	}
	public StudentSubject setSubj_code_div(String subj_code_div) {
		this.subj_code_div = subj_code_div;
		return this;
	}
	public Date getRegist_date() {
		return regist_date;
	}
	public StudentSubject setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
		return this;
	}
	public int getDel() {
		return del;
	}
	public StudentSubject setDel(int del) {
		this.del = del;
		return this;
	}
	public int getTask_score() {
		return task_score;
	}
	public StudentSubject setTask_score(int task_score) {
		this.task_score = task_score;
		return this;
	}
	public String getTask_comment() {
		return task_comment;
	}
	public StudentSubject setTask_comment(String task_comment) {
		this.task_comment = task_comment;
		return this;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public StudentSubject setCre_date(Date cre_date) {
		this.cre_date = cre_date;
		return this;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public StudentSubject setMod_date(Date mod_date) {
		this.mod_date = mod_date;
		return this;
	}
	
	

}
