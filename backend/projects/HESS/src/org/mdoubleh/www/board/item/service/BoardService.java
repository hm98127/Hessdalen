package org.mdoubleh.www.board.item.service;

import static org.mdoubleh.www.common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.mdoubleh.www.board.item.dao.BoardDao;
import org.mdoubleh.www.board.item.vo.BoardVo;

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
