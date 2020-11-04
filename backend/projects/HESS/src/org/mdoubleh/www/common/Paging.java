package org.mdoubleh.www.common;

public class Paging {
	// 한페이지에 보여줄 글 개수
	private final int SHOW_BOARD_COUNT = 10;
	// 페이지 번호 묶음
	private final int PAGE_GROUP = 10;

	// 현재 페이지 번호
	private int nowPageNumber;

	// 글 총 개수
	private int totalBoardCount;

	// 시작 글 번호
	private int startBoardNumber;

	// 총 페이지 개수
	private int totalPageCount;

	// 시작 페이지, 끝 페이지
	private int startPage;
	private int endPage;

	public Paging(int nowPageNumber, int totalBoardCount) {
		this.nowPageNumber = nowPageNumber;
		this.startBoardNumber = (nowPageNumber - 1) * SHOW_BOARD_COUNT;
		this.totalBoardCount = totalBoardCount;
		this.totalPageCount = (int) Math.ceil((double) totalBoardCount / SHOW_BOARD_COUNT);
		if (this.totalPageCount < 1) {
			this.totalPageCount = 1;
		}
		this.startPage = ((int) (((double) nowPageNumber / PAGE_GROUP + 0.9) - 1)) * PAGE_GROUP + 1;
		this.endPage = this.startPage + (PAGE_GROUP - 1);
		if (this.endPage > this.totalPageCount) {
			this.endPage = this.totalPageCount;
		}
	}

	public int getSHOW_BOARD_COUNT() {
		return SHOW_BOARD_COUNT;
	}

	public int getNowPageNumber() {
		return nowPageNumber;
	}

	public void setNowPageNumber(int nowPageNumber) {
		this.nowPageNumber = nowPageNumber;
	}

	public int getTotalBoardCount() {
		return totalBoardCount;
	}

	public void setTotalBoardCount(int totalBoardCount) {
		this.totalBoardCount = totalBoardCount;
	}

	public int getStartBoardNumber() {
		return startBoardNumber;
	}

	public void setStartBoardNumber(int startBoardNumber) {
		this.startBoardNumber = startBoardNumber;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

}
