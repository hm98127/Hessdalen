package org.mdoubleh.www.board.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.mdoubleh.www.board.event.vo.EvBoardVo;
import org.mdoubleh.www.board.item.vo.ItemVo;
import org.mdoubleh.www.board.notice.dao.BoardDao;
import org.mdoubleh.www.board.notice.vo.BoardVo;
import org.mdoubleh.www.common.Paging;

import static org.mdoubleh.www.common.JdbcUtil.*;

public class BoardService {
	public int getBoardCount() {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getBoardCount();
		close(con);
		return count;
	}
	
	public ArrayList<BoardVo> getBoardList(Paging paging) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<BoardVo> list = dao.getBoardList(paging);
		close(con);
		return list;
	}
	
	public boolean registerBoard(BoardVo vo) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.registerBoard(vo);
		if (count > 0) {
			isSucess = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public BoardVo getBoard(int num) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		BoardVo vo = null;
		int count = dao.updateHitCount(num);
		if (count > 0) {
			commit(con);
			vo = dao.getBoard(num);
		} else {
			rollback(con);
		}
		close(con);
		return vo;
	}
	
	public String getWriterId(int num) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		String id = dao.getWriterId(num);
		close(con);
		return id;
	}
	
	public boolean modifyBoard(BoardVo vo) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.modifyBoard(vo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean deleteBoard(int num) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.deleteBoard(num);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	// 이벤트 게시판
	public int getEventBoardCount() {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getEventBoardCount();
		close(con);
		return count;
	}
	
	public ArrayList<EvBoardVo> getEventBoardList(Paging paging) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<EvBoardVo> list = dao.getEventBoardList(paging);
		close(con);
		return list;
	}
	
	public boolean registerEventBoard(EvBoardVo vo) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.registerEventBoard(vo);
		if (count > 0) {
			isSucess = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public EvBoardVo getEventBoard(int num) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		EvBoardVo vo = null;
		int count = dao.updateHitEventCount(num);
		if (count > 0) {
			commit(con);
			vo = dao.getEventBoard(num);
		} else {
			rollback(con);
		}
		close(con);
		return vo;
	}
	
	public String getWriterEventId(int num) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		String id = dao.getWriterEventId(num);
		close(con);
		return id;
	}
	
	public boolean modifyEventBoard(EvBoardVo vo) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.modifyEventBoard(vo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean deleteEventBoard(int num) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.deleteEventBoard(num);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	// 상품 게시판
	
	
}
