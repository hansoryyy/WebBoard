package github.hansoryyy.webboard.dto;

public class VisitDTO {

	private int visitNo;
    private String visitIp;
    private String visitDate;
    private String visitRefer;
    private String visitAgent;
    
	public int getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(int visitNo) {
		this.visitNo = visitNo;
	}
	public String getVisitIp() {
		return visitIp;
	}
	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getVisitRefer() {
		return visitRefer;
	}
	public void setVisitRefer(String visitRefer) {
		this.visitRefer = visitRefer;
	}
	public String getVisitAgent() {
		return visitAgent;
	}
	public void setVisitAgent(String visitAgent) {
		this.visitAgent = visitAgent;
	}
   

}
