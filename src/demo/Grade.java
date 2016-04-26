package demo;

public class Grade {
	
	String gid;
	String date;
	String cid;
	String cname;
	String score;
	String credit;
	String studyhour;
	String exammethod;
	String examattr;
	public Grade() {
		super();
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getStudyhour() {
		return studyhour;
	}
	public void setStudyhour(String studyhour) {
		this.studyhour = studyhour;
	}
	public String getExammethod() {
		return exammethod;
	}
	public void setExammethod(String exammethod) {
		this.exammethod = exammethod;
	}
	public String getExamattr() {
		return examattr;
	}
	public void setExamattr(String examattr) {
		this.examattr = examattr;
	}
	@Override
	public String toString() {
		return "Grade [gid=" + gid + ", date=" + date + ", cid=" + cid + ", cname=" + cname + ", score=" + score
				+ ", credit=" + credit + ", studyhour=" + studyhour + ", exammethod=" + exammethod + ", examattr="
				+ examattr + "]";
	}
	
	
	
	
	

}
