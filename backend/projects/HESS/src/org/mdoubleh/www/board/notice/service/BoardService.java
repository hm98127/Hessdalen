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
	
	public boolean registerBoard(BoardVo vo) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;
        int count = dao.registerBoard(vo);
        if (count > 0) {
            commit(con);
            isSucess = true;
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
        int count = dao.getHit(num);
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
