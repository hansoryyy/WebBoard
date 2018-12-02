package github.hansoryyy.webboard.dto;

public class PostDTO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private String createDate; // countrId - 
	
	public PostDTO() {}
	public PostDTO(int seq, String title, String content, String writer) {
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	

}
