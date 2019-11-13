package github.hansoryyy.webboard.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	
	private int boardNo;
	private String boardGp;
	private String boardType;
	private String boardPw;
	private String writer;
	private String title;
	private String contents;
	private int hits;
	private int like;
	private int hate;
	private String fixedYn;
	private String secertYn;
	private String email;
	private int replySeq;
	private int replyLev;
	private Date writeDt;	//JAVA.UTIL.DATE
	private String writeTm;
	private String createIp;
	private String createDt;
	private String updateIp;
	private String updateDt;
	private String rowState;
	
	private List<String[]> files;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardGp() {
		return boardGp;
	}
	public void setBoardGp(String boardGp) {
		this.boardGp = boardGp;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getBoardPw() {
		return boardPw;
	}
	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getHate() {
		return hate;
	}
	public void setHate(int hate) {
		this.hate = hate;
	}
	public String getFixedYn() {
		return fixedYn;
	}
	public void setFixedYn(String fixedYn) {
		this.fixedYn = fixedYn;
	}
	public String getSecertYn() {
		return secertYn;
	}
	public void setSecertYn(String secertYn) {
		this.secertYn = secertYn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getReplySeq() {
		return replySeq;
	}
	public void setReplySeq(int replySeq) {
		this.replySeq = replySeq;
	}
	public int getReplyLev() {
		return replyLev;
	}
	public void setReplyLev(int replyLev) {
		this.replyLev = replyLev;
	}
	public Date getWriteDt() {
		return writeDt;
	}
	public void setWriteDt(Date writeDt) {
		this.writeDt = writeDt;
	}
	public String getWriteTm() {
		return writeTm;
	}
	public void setWriteTm(String writeTm) {
		this.writeTm = writeTm;
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
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	public String getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
	public String getRowState() {
		return rowState;
	}
	public void setRowState(String rowState) {
		this.rowState = rowState;
	}
	public List<String[]> getFiles() {
		return files;
	}
	public void setFiles(List<String[]> files) {
		this.files = files;
	}
	
	
	
	
	

}
