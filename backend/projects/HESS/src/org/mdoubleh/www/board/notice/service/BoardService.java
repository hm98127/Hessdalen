package org.mdoubleh.www.board.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.mdoubleh.www.board.notice.dao.BoardDao;
import org.mdoubleh.www.board.notice.vo.BoardVo;

import static org.mdoubleh.www.common.JdbcUtil.*;

public class BoardService {
	public ArrayList<BoardVo> getBoardList() {
		BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        ArrayList<BoardVo> list = dao.getBoardList();
        close(con);
        return list;
	}
}
