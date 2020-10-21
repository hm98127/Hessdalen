package org.mdoubleh.www.service;

import static org.mdoubleh.www.common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.mdoubleh.www.common.Pagenation;
import org.mdoubleh.www.dao.HessDao;
import org.mdoubleh.www.vo.ArticleVo;
import org.mdoubleh.www.vo.MemberVo;

public class HessService {
	public boolean joinMember(MemberVo memberVo) {
		HessDao dao = HessDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.insertMember(memberVo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public MemberVo getMember(String id) {
		HessDao dao = HessDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVo vo = dao.getMember(id);
		close(con);
		return vo;
	}
	
	public boolean logoutMember(MemberVo memberVo) {
        HessDao dao = HessDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;
        memberVo.setMber_sq(dao.getMemberSequence(memberVo.getId()));
        int count = dao.updateLoginState(memberVo);
        if (count > 0) {
            commit(con);
            isSucess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSucess;
    }

	public int getMemberSequence(String id) {
        HessDao dao = HessDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int sq = dao.getMemberSequence(id);
        close(con);
        return sq;
    }
	
	public int getMemberCount(String id) {
		HessDao dao = HessDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getMemberSequence(id);
		close(con);
		return count;
	}
	
	public int getArticleCount(String query) {
		HessDao dao = HessDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getArticleCount(query);
		close(con);
		return count;
	}
	
	public ArrayList<ArticleVo> getArticleList(Pagenation pagenation, String query) {
		HessDao dao = HessDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ArticleVo> list = dao.getArticleList(pagenation, query);
		close(con);
		return list;
	}
	
	public ArticleVo getArticle(int num) {
		HessDao dao = HessDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArticleVo vo = null;
		int count = dao.updateHitCount(num);
		if (count > 0) {
			commit(con);
			vo = dao.getArticle(num);
		} else {
			rollback(con);
		}
		close(con);
		return vo;
	}
	
	public boolean insertArticle(ArticleVo vo) {
		HessDao dao = HessDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.insertArticle(vo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean deleteArticle(int num) {
		HessDao dao = HessDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.deleteArticle(num);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean updateArticle(ArticleVo vo) {
		HessDao dao = HessDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.updateArticle(vo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
}
