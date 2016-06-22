package vo;

import java.util.Date;

public class SubjectInfo {
	protected String subj_code_div;
	protected int curr_num;
	protected String subj_name;
	protected String subj_image;
	protected String subj_outline;
	protected int limit_num;
	protected String year_term;
	protected int prof_id;
	protected String regist_code;
	protected int subj_progress_b;
	
	// 아직 디비에 생성 안된거
	protected Date subj_cre_date;
	protected Date subj_mod_date;

	public int getSubj_progress_b() {
		return subj_progress_b;
	}
	public SubjectInfo setSubj_progress_b(int subj_progress_b) {
		this.subj_progress_b = subj_progress_b;
		return this;
	}
	public String getSubj_code_div() {
		return subj_code_div;
	}
	public SubjectInfo setSubj_code_div(String subj_code_div) {
		this.subj_code_div = subj_code_div;
		return this;
	}
	public int getCurr_num() {
		return curr_num;
	}
	public SubjectInfo setCurr_num(int curr_num) {
		this.curr_num = curr_num;
		return this;
	}
	public String getSubj_name() {
		return subj_name;
	}
	public SubjectInfo setSubj_name(String subj_name) {
		this.subj_name = subj_name;
		return this;
	}
	public String getSubj_image() {
		return subj_image;
	}
	public SubjectInfo setSubj_image(String subj_image) {
		this.subj_image = subj_image;
		return this;
	}
	public String getSubj_outline() {
		return subj_outline;
	}
	public SubjectInfo setSubj_outline(String subj_outline) {
		this.subj_outline = subj_outline;
		return this;
	}
	public int getLimit_num() {
		return limit_num;
	}
	public SubjectInfo setLimit_num(int limit_num) {
		this.limit_num = limit_num;
		return this;
	}
	public String getYear_term() {
		return year_term;
	}
	public SubjectInfo setYear_term(String year_term) {
		this.year_term = year_term;	
		return this;
	}
	public int getProf_id() {
		return prof_id;
	}
	public SubjectInfo setProf_id(int prof_id) {
		this.prof_id = prof_id;	
		return this;
	}
	public String getRegist_code() {
		return regist_code;
	}
	public SubjectInfo setRegist_code(String regist_code) {
		this.regist_code = regist_code;
		return this;
	}
	public Date getSubj_cre_date() {
		return subj_cre_date;
	}
	public SubjectInfo setSubj_cre_date(Date subj_cre_date) {
		this.subj_cre_date = subj_cre_date;
		return this;
	}
	public Date getSubj_mod_date() {
		return subj_mod_date;
	}
	public SubjectInfo setSubj_mod_date(Date subj_mod_date) {
		this.subj_mod_date = subj_mod_date;
		return this;
	}

}
