package com.kb.www.service;

import com.kb.www.common.Pagenation;
import com.kb.www.dao.BoardDao;
import com.kb.www.vo.ArticleVo;
import com.kb.www.vo.MemberHistoryVo;
import com.kb.www.vo.MemberVo;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kb.www.common.JdbcUtil.*;

public class BoardService {
    public int getArticleCount(String query) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count = dao.getArticleCount(query);
        close(con);
        return count;
    }

    public ArrayList<ArticleVo> getArticleList(Pagenation pagenation, String query) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        ArrayList<ArticleVo> list = dao.getArticleList(pagenation, query);
        close(con);
        return list;
    }

    public ArticleVo getArticle(int num) {
        BoardDao dao = BoardDao.getInstance();
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
        BoardDao dao = BoardDao.getInstance();
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
        BoardDao dao = BoardDao.getInstance();
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
        BoardDao dao = BoardDao.getInstance();
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

    public boolean joinMember(MemberVo memberVo, MemberHistoryVo memberHistoryVo) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;
        int count_01 = dao.insertMember(memberVo);
        memberHistoryVo.setMb_sq(dao.getMemberSequence(memberVo.getId()));
        int count_02 = dao.insertMemberHistory(memberHistoryVo);
        if (count_01 > 0 && count_02 > 0) {
            commit(con);
            isSucess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSucess;
    }

    public MemberVo getMember(String id) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        MemberVo vo = dao.getMember(id);
        close(con);
        return vo;
    }

    public boolean loginMember(MemberVo memberVo, MemberHistoryVo memberHistoryVo) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;
        int count_01 = dao.updateLoginState(memberVo);
        int count_02 = dao.insertMemberHistory(memberHistoryVo);
        if (count_01 > 0 && count_02 > 0) {
            commit(con);
            isSucess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSucess;
    }

    public boolean logoutMember(MemberVo memberVo, MemberHistoryVo memberHistoryVo) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;
        memberVo.setSq(dao.getMemberSequence(memberVo.getId()));
        memberHistoryVo.setMb_sq(memberVo.getSq());
        int count_01 = dao.updateLoginState(memberVo);
        int count_02 = dao.insertMemberHistory(memberHistoryVo);
        if (count_01 > 0 && count_02 > 0) {
            commit(con);
            isSucess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSucess;
    }

    public int getMemberSequence(String id) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int sq = dao.getMemberSequence(id);
        close(con);
        return sq;
    }

    public String getWriterId(int num) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        String id = dao.getWriterId(num);
        close(con);
        return id;
    }

    public ArrayList<MemberHistoryVo> getMemberHistory(String id) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        ArrayList<MemberHistoryVo> list = dao.getMemberHistory(id);
        close(con);
        return list;
    }

    public boolean updateMember(MemberVo memberVo, MemberHistoryVo memberHistoryVo) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;
        int count_01 = dao.updateMember(memberVo);
        int count_02 = dao.insertMemberHistory(memberHistoryVo);
        if (count_01 > 0 && count_02 > 0) {
            commit(con);
            isSucess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSucess;
    }

    public boolean leaveMember(MemberVo memberVo, MemberHistoryVo memberHistoryVo) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;
        int count_01 = dao.leaveMember(memberVo);
        int count_02 = dao.insertMemberHistory(memberHistoryVo);
        if (count_01 > 0 && count_02 > 0) {
            commit(con);
            isSucess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSucess;
    }

    public int getMemberCount(String id) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count = dao.getMemberCount(id);
        close(con);
        return count;
    }
}










