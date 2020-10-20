package com.kb.www.dao;

import com.kb.www.common.Pagenation;
import com.kb.www.vo.ArticleVo;
import com.kb.www.vo.MemberHistoryVo;
import com.kb.www.vo.MemberVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.kb.www.common.JdbcUtil.close;

public class BoardDao {
    private Connection con;

    private BoardDao() {

    }

    public static BoardDao getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final BoardDao INSTANCE = new BoardDao();
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    public int getArticleCount(String query) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
                    ("select count(*) from board" +
                            " where 1=1" + query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                count =rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return count;
    }

    public ArrayList<ArticleVo> getArticleList(Pagenation pagenation, String query) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ArticleVo> list = new ArrayList<>();
        try {
            pstmt = con.prepareStatement
                    ("select b.num" +
                            ", m.id" +
                            ", b.subject" +
                            ", b.content" +
                            ", b.hit" +
                            ", b.wdate" +
                            ", b.udate" +
                            ", b.ddate" +
                            " from board b" +
                            " inner join member m on b.mb_sq = m.sq" +
                            " where 1=1" + query +
                            " order by num desc" +
                            " limit ?, ?");
            pstmt.setInt(1, pagenation.getStartArticleNumber());
            pstmt.setInt(2, pagenation.getSHOW_ARTICLE_COUNT());
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ArticleVo vo = new ArticleVo();
                vo.setNum(rs.getInt("num"));
                vo.setSubject(rs.getString("subject"));
                vo.setContent(rs.getString("content"));
                vo.setHit(rs.getInt("hit"));
                vo.setWdate(rs.getString("wdate"));
                vo.setUdate(rs.getString("udate"));
                vo.setDdate(rs.getString("ddate"));
                vo.setId(rs.getString("id"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return list;
    }

    public ArticleVo getArticle(int num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArticleVo vo = null;
        try {
            pstmt = con.prepareStatement
                    ("select" +
                            " b.num" +
                            ", b.mb_sq" +
                            ", b.subject" +
                            ", b.content" +
                            ", b.hit" +
                            ", b.wdate" +
                            ", b.udate" +
                            ", b.ddate" +
                            ", m.id" +
                            " from board b" +
                            " inner join member m on b.mb_sq = m.sq" +
                            " where num=?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                vo = new ArticleVo();
                vo.setNum(rs.getInt("num"));
                vo.setSubject(rs.getString("subject"));
                vo.setContent(rs.getString("content"));
                vo.setHit(rs.getInt("hit"));
                vo.setWdate(rs.getString("wdate"));
                vo.setUdate(rs.getString("udate"));
                vo.setDdate(rs.getString("ddate"));
                vo.setId(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return vo;
    }

    public int insertArticle(ArticleVo vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
                    ("insert into board(mb_sq, subject, content) value(?, ?, ?)");
            pstmt.setInt(1, vo.getMb_sq());
            pstmt.setString(2, vo.getSubject());
            pstmt.setString(3, vo.getContent());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }

    public int deleteArticle(int num) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("delete from board where num=?");
            pstmt.setInt(1, num);
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }

    public int updateArticle(ArticleVo vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("update board set subject=?, content=?, udate=? where num=?");
            pstmt.setString(1, vo.getSubject());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            pstmt.setInt(4, vo.getNum());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }

    public int updateHitCount(int num) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("update board set hit=hit+1 where num=?");
            pstmt.setInt(1, num);
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }

    public int insertMember(MemberVo vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
                    ("insert into member(id, pwd) value(?, ?)");
            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getPwd());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }

    public int getMemberSequence(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int sq = 0;
        try {
            pstmt = con.prepareStatement("select sq from member where id=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                sq = rs.getInt("sq");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return sq;
    }

    public int insertMemberHistory(MemberHistoryVo vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
                    ("insert into member_history(mb_sq, evt_type) value(?, ?)");
            pstmt.setInt(1, vo.getMb_sq());
            pstmt.setInt(2, vo.getEvt_type());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }

    public MemberVo getMember(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVo vo = null;
        try {
            pstmt = con.prepareStatement
                    ("select sq, id, pwd from member where binary(id)=? and leave_fl=false");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                vo = new MemberVo();
                vo.setSq(rs.getInt("sq"));
                vo.setId(rs.getString("id"));
                vo.setPwd(rs.getString("pwd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return vo;
    }

    public int updateLoginState(MemberVo vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
                    ("update member set lgn_fl=? where sq=? and leave_fl=false");
            pstmt.setBoolean(1, vo.isLgn_fl());
            pstmt.setInt(2, vo.getSq());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }

    public String getWriterId(int num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String id = null;
        try {
            pstmt = con.prepareStatement
                    ("select" +
                            " m.id" +
                            " from board b" +
                            " inner join member m on b.mb_sq = m.sq" +
                            " where num=?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                id = rs.getString("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return id;
    }

    public ArrayList<MemberHistoryVo> getMemberHistory(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MemberHistoryVo> list = new ArrayList<>();
        try {
            pstmt = con.prepareStatement
                    ("select " +
                            "mh.evt_type " +
                            ", mh.dttm " +
                            "from member m " +
                            "left join member_history mh on m.sq = mh.mb_sq " +
                            "where id=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                MemberHistoryVo vo = new MemberHistoryVo();
                vo.setEvt_type(rs.getInt("evt_type"));
                vo.setDttm(rs.getString("dttm"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return list;
    }

    public int updateMember(MemberVo vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
                    ("update member set pwd=? where id=?");
            pstmt.setString(1, vo.getPwd());
            pstmt.setString(2, vo.getId());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }

    public int leaveMember(MemberVo vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
                    ("update member set leave_fl=? where id=?");
            pstmt.setBoolean(1, true);
            pstmt.setString(2, vo.getId());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }

    public int getMemberCount(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
                    ("select count(*) from member" +
                            " where binary(id)=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                count =rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return count;
    }
}









