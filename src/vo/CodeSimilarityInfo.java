package vo;


public class CodeSimilarityInfo {
	protected int 		num;
	protected int 		task_number;
	protected String 	stu_name1;
	protected String 	stu_name2;
	protected String 	title;
	protected double 	content;

	
	public int getNum() {
		return num;
	}
	public CodeSimilarityInfo setNum(int num) {
		this.num = num;
		return this;
	}
	public int getTask_number() {
		return task_number;
	}
	public CodeSimilarityInfo setTask_number(int task_number) {
		this.task_number = task_number;
		return this;
	}
	public String getStu_name1() {
		return stu_name1;
	}
	public CodeSimilarityInfo setStu_name1(String stu_name1) {
		this.stu_name1 = stu_name1;
		return this;
	}
	public String getStu_name2() {
		return stu_name2;
	}
	public CodeSimilarityInfo setStu_name2(String stu_name2) {
		this.stu_name2 = stu_name2;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public CodeSimilarityInfo setTitle(String title) {
		this.title = title;
		return this;
	}
	public double getContent() {
		return content;
	}
	public CodeSimilarityInfo setContent(double content) {
		this.content = content;
		return this;
	}
}
