package com.kb.www.board.board.service;

import static com.kb.www.common.JdbcUtil.close;
import static com.kb.www.common.JdbcUtil.commit;
import static com.kb.www.common.JdbcUtil.getConnection;
import static com.kb.www.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kb.www.board.board.dao.ArticleDao;
import com.kb.www.board.board.vo.ArticleVo;
import com.kb.www.board.member.dao.MemberDao;
import com.kb.www.board.member.vo.MemberVo;

public class ArticleService {
	public ArrayList<ArticleVo> getArticleList() {
		ArticleDao dao = ArticleDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ArticleVo> list = dao.getArticleList();
		close(con);
		return list;
	}
	
	public boolean registerArticle(ArticleVo vo) {
		ArticleDao dao = ArticleDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.registerArticle(vo);
		if (count > 0) {
			isSucess = true;
			commit(con);
		} else {
			isSucess = false;
			rollback(con);
		}
		close(con);
		return isSucess;
	}
}
