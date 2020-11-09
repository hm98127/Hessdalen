package org.mdoubleh.www.board.item.service;

import static org.mdoubleh.www.common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.mdoubleh.www.board.item.dao.BoardDao;
import org.mdoubleh.www.board.item.vo.BoardVo;
import org.mdoubleh.www.board.review.vo.ReviewVo;
import org.mdoubleh.www.common.Paging;

public class BoardService {
	public int getBoardCount() {
		BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count = dao.getBoardCount();
        close(con);
        return count;
	}
	
	public ArrayList<BoardVo> getBoardList() {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<BoardVo> list = dao.getBoardList();
		close(con);
		return list;
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
		BoardVo vo = dao.getBoard(num);
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
	
	public ArrayList<ReviewVo> getReviewBoardList() {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ReviewVo> list = dao.getReviewBoardList();
		close(con);
		return list;
	}
	
}
