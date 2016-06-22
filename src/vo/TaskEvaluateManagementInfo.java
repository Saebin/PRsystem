package vo;

import java.util.Date;

public class TaskEvaluateManagementInfo {
	protected int 		task_number;
	protected int		std_id;
	protected int 	 	std_id_evaluate;
	protected int 	submit_score;
	protected String 	submit_comment;
	protected Date		cre_date;
	protected Date		mod_date;
	protected int		del;
	
	public int getTask_number() {
		return task_number;
	}
	public TaskEvaluateManagementInfo setTask_number(int task_number) {
		this.task_number = task_number;
		return this;
	}
	public int getStd_id() {
		return std_id;
	}
	public TaskEvaluateManagementInfo setStd_id(int std_id) {
		this.std_id = std_id;
		return this;
	}
	public int getStd_id_evaluate() {
		return std_id_evaluate;
	}
	public TaskEvaluateManagementInfo setStd_id_evaluate(int std_id_evaluate) {
		this.std_id_evaluate = std_id_evaluate;
		return this;
	}
	public int getSubmit_score() {
		return submit_score;
	}
	public TaskEvaluateManagementInfo setSubmit_score(int submit_score) {
		this.submit_score = submit_score;
		return this;
	}
	public String getSubmit_comment() {
		return submit_comment;
	}
	public TaskEvaluateManagementInfo setSubmit_comment(String submit_comment) {
		this.submit_comment = submit_comment;
		return this;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public TaskEvaluateManagementInfo setCre_date(Date cre_date) {
		this.cre_date = cre_date;
		return this;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public TaskEvaluateManagementInfo setMod_date(Date mod_date) {
		this.mod_date = mod_date;
		return this;
	}
	public int getDel() {
		return del;
	}
	public TaskEvaluateManagementInfo setDel(int del) {
		this.del = del;
		return this;
	}
	
}
