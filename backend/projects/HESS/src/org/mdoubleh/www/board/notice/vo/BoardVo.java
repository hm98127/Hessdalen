package org.mdoubleh.www.board.notice.vo;

public class BoardVo {
	private int notice_postnum;
	private String notice_title;
	private String notice_content;
	private String notice_regdate;
	private int notice_hit;
	private String member_id;

	public int getNotice_postnum() {
		return notice_postnum;
	}

	public void setNotice_postnum(int notice_postnum) {
		this.notice_postnum = notice_postnum;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getNotice_regdate() {
		return notice_regdate;
	}

	public void setNotice_regdate(String notice_regdate) {
		this.notice_regdate = notice_regdate;
	}

	public int getNotice_hit() {
		return notice_hit;
	}

	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

}
