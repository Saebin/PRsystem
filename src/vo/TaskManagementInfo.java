package vo;

import java.util.Date;

public class TaskManagementInfo {
	protected int 		task_number;
	protected int 	    std_id;
	protected String 	task_score;
	protected String 	task_comment;
	protected String 	task_answer;
	protected int 		task_group_number;
	protected String 	task_submit_b;
	protected Date		cre_date;
	protected Date		mod_date;
	protected int		del;
	protected int		group_div;
	protected String 	task_title;
	protected String 	task_content;
	
	public int getTask_number() {
		return task_number;
	}
	public TaskManagementInfo setTask_number(int task_number) {
		this.task_number = task_number;
		return this;
	}
	public int getStd_id() {
		return std_id;
	}
	public TaskManagementInfo setStd_id(int std_id) {
		this.std_id = std_id;
		return this;
	}
	public String getTask_score() {
		return task_score;
	}
	public TaskManagementInfo setTask_score(String task_score) {
		this.task_score = task_score;
		return this;
	}
	public String getTask_comment() {
		return task_comment;
	}
	public TaskManagementInfo setTask_comment(String task_comment) {
		this.task_comment = task_comment;
		return this;
	}
	public String getTask_answer() {
		return task_answer;
	}
	public TaskManagementInfo setTask_answer(String task_answer) {
		this.task_answer = task_answer;
		return this;
	}
	public int getTask_group_number() {
		return task_group_number;
	}
	public TaskManagementInfo setTask_group_number(int task_group_number) {
		this.task_group_number = task_group_number;
		return this;
	}
	public String getTask_submit_b() {
		return task_submit_b;
	}
	public TaskManagementInfo setTask_submit_b(String task_submit_b) {
		this.task_submit_b = task_submit_b;
		return this;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public TaskManagementInfo setCre_date(Date cre_date) {
		this.cre_date = cre_date;
		return this;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public TaskManagementInfo setMod_date(Date mod_date) {
		this.mod_date = mod_date;
		return this;
	}
	public int getDel() {
		return del;
	}
	public TaskManagementInfo setDel(int del) {
		this.del = del;
		return this;
	}
	public int getGroup_div() {
		return group_div;
	}
	public TaskManagementInfo setGroup_div(int group_div) {
		this.group_div = group_div;
		return this;
	}
	public String getTask_title() {
		return task_title;
	}
	public TaskManagementInfo setTask_title(String task_title) {
		this.task_title = task_title;
		return this;
	}
	public String getTask_content() {
		return task_content;
	}
	public TaskManagementInfo setTask_content(String task_content) {
		this.task_content = task_content;
		return this;
	}
	
}