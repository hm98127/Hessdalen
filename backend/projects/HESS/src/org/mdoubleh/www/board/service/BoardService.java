package org.mdoubleh.www.board.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.mdoubleh.www.board.dao.BoardDao;
import org.mdoubleh.www.board.vo.BoardVo;
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
	
}
