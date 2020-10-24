package org.mdoubleh.www.member.vo;

public class MemberVo {
	private int mb_sq;
	private String nm;
	private String id;
	private String pwd;
	private String dttm;
	private boolean lgn_fl;
	private boolean del_fl;

	public int getMb_sq() {
		return mb_sq;
	}

	public void setMb_sq(int mb_sq) {
		this.mb_sq = mb_sq;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDttm() {
		return dttm;
	}

	public void setDttm(String dttm) {
		this.dttm = dttm;
	}

	public boolean isLgn_fl() {
		return lgn_fl;
	}

	public void setLgn_fl(boolean lgn_fl) {
		this.lgn_fl = lgn_fl;
	}

	public boolean isDel_fl() {
		return del_fl;
	}

	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}

}
