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

}
