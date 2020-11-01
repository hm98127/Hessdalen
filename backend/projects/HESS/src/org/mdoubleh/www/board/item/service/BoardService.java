package org.mdoubleh.www.board.item.service;

import static org.mdoubleh.www.common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.mdoubleh.www.board.item.dao.BoardDao;
import org.mdoubleh.www.board.item.vo.ItemVo;
import org.mdoubleh.www.common.Paging;

public class BoardService {
	public ArrayList<ItemVo> getBoardList(Paging paging) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ItemVo> list = dao.getBoardList(paging);
		close(con);
		return list;
	}
	
	public ArrayList<ItemVo> getBoardList() {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ItemVo> list = dao.getBoardList();
		close(con);
		return list;
	}
	
	public int getBoardCount() {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getBoardCount();
		close(con);
		return count;
	}
	
	public ItemVo getBoard(int num) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ItemVo vo = dao.getBoard(num);
		close(con);
		return vo;
	}
	
	public boolean registerBoard(ItemVo vo) {
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
	
}
