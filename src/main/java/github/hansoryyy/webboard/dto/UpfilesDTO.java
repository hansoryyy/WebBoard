package github.hansoryyy.webboard.dto;

public class UpfilesDTO {
	private int upfilesNo;
	private String originFilename;
	private String genFilename;
	private int boardNo;
	private String createIp;
	private String createDt;
	
	public int getUpfilesNo() {
		return upfilesNo;
	}
	public void setUpfilesNo(int upfilesNo) {
		this.upfilesNo = upfilesNo;
	}
	public String getOriginFilename() {
		return originFilename;
	}
	public void setOriginFilename(String originFilename) {
		this.originFilename = originFilename;
	}
	public String getGenFilename() {
		return genFilename;
	}
	public void setGenFilename(String genFilename) {
		this.genFilename = genFilename;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getCreateIp() {
		return createIp;
	}
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
}
