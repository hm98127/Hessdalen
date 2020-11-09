package org.mdoubleh.www.board.review.vo;

public class ReviewVo {
	private int review_postnum;
	private int item_postnum;
	private String review_img;
	private String review_title;
	private String review_content;
	private int review_hit;
	private String review_regdate;
	private String member_id;

	public int getReview_postnum() {
		return review_postnum;
	}

	public void setReview_postnum(int review_postnum) {
		this.review_postnum = review_postnum;
	}

	public int getItem_postnum() {
		return item_postnum;
	}

	public void setItem_postnum(int item_postnum) {
		this.item_postnum = item_postnum;
	}

	public String getReview_img() {
		return review_img;
	}

	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}

	public String getReview_title() {
		return review_title;
	}

	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public int getReview_hit() {
		return review_hit;
	}

	public void setReview_hit(int review_hit) {
		this.review_hit = review_hit;
	}

	public String getReview_regdate() {
		return review_regdate;
	}

	public void setReview_regdate(String review_regdate) {
		this.review_regdate = review_regdate;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

}
