package org.mdoubleh.www.board.item.vo;

public class BoardVo {
	private int item_postnum;
	private String item_title;
	private String item_content;
	private int item_price;
	private String item_img;
	private String item_group;
	private String item_regdate;
	private boolean item_event;
	private String member_id;

	public int getItem_postnum() {
		return item_postnum;
	}

	public void setItem_postnum(int item_postnum) {
		this.item_postnum = item_postnum;
	}

	public String getItem_title() {
		return item_title;
	}

	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}

	public String getItem_content() {
		return item_content;
	}

	public void setItem_content(String item_content) {
		this.item_content = item_content;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public String getItem_img() {
		return item_img;
	}

	public void setItem_img(String item_img) {
		this.item_img = item_img;
	}

	public String getItem_group() {
		return item_group;
	}

	public void setItem_group(String item_group) {
		this.item_group = item_group;
	}

	public String getItem_regdate() {
		return item_regdate;
	}

	public void setItem_regdate(String item_regdate) {
		this.item_regdate = item_regdate;
	}

	public boolean isItem_event() {
		return item_event;
	}

	public void setItem_event(boolean item_event) {
		this.item_event = item_event;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

}
