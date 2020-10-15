package org.mdoubleh.www.dao;

import static org.mdoubleh.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.mdoubleh.www.vo.ArticleVo;
import org.mdoubleh.www.vo.MemberVo;

public class HessDao {
	private Connection con;
	
	private HessDao() {
		
	}
	
	public static HessDao getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final HessDao INSTANCE = new HessDao();
    }
    
    public void setConnection(Connection con) {
    	this.con = con;
    }
    
    public int insertMember(MemberVo vo) {
    	PreparedStatement pstmt = null;
    	int count = 0;
    	try {
    		pstmt = con.prepareStatement("INSERT INTO inf_mber_tb(id) VALUES(?)");
    		pstmt.setString(1, vo.getId());
    		count = pstmt.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		close(pstmt);
    	}
    	return count;
    }
    
    public int getMemberSequence(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int mber_sq = 0;
        try {
            pstmt = con.prepareStatement("SELECT mber_sq FROM inf_mber_tb WHERE id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	mber_sq = rs.getInt("mber_sq");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return mber_sq;
    }
    
    public MemberVo getMember(String id) {
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	MemberVo vo = null;
    	try {
    		pstmt = con.prepareStatement("SELECT mber_sq, id FROM inf_mber_tb WHERE binary(id) = ? AND del_fl = false");
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			vo = new MemberVo();
    			vo.setMber_sq(rs.getInt("mber_sq"));
    			vo.setId(rs.getString("id"));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
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
                    ("UPDATE inf_mber_tb SET del_fl = ? WHERE mber_sq = ? AND del_fl = false");
            pstmt.setBoolean(1, vo.isDel_fl());
            pstmt.setInt(2, vo.getMber_sq());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }
    
    public ArrayList<ArticleVo> getArticleList() {
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<ArticleVo> list = new ArrayList<>();
    	try {
    		pstmt = con.prepareStatement("SELECT * FROM inf_articl_tb");
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			ArticleVo vo = new ArticleVo();
    			vo.setArticl_sq(rs.getInt("articl_sq"));
    			vo.setHit(rs.getInt("hit"));
    			vo.setDttm(rs.getString("dttm"));
    			vo.setSj(rs.getString("sj"));
    			vo.setCn(rs.getString("cn"));
    			list.add(vo);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
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
    		pstmt = con.prepareStatement("SELECT * FROM inf_articl_tb WHERE articl_sq = ?");
    		pstmt.setInt(1, num);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			vo = new ArticleVo();
    			vo.setArticl_sq(rs.getInt("articl_sq"));
    			vo.setHit(rs.getInt("hit"));
    			vo.setDttm(rs.getString("dttm"));
    			vo.setSj(rs.getString("sj"));
    			vo.setCn(rs.getString("cn"));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		close(rs);
    		close(pstmt);
    	}
    	return vo;
    }
    
    public int insertArticle(ArticleVo vo) {
    	PreparedStatement pstmt = null;
    	int count = 0;
    	try {
    		pstmt = con.prepareStatement("INSERT INTO inf_articl_tb(sj, cn) VALUES(?, ?)");
    		pstmt.setString(1, vo.getSj());
    		pstmt.setString(2, vo.getCn());
    		count = pstmt.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		close(pstmt);
    	}
    	return count;
    }
    
    public int updateHitCount(int num) {
    	PreparedStatement pstmt = null;
    	int count = 0;
    	try {
    		pstmt = con.prepareStatement("UPDATE inf_articl_tb SET hit = hit + 1 WHERE articl_sq = ?");
    		pstmt.setInt(1, num);
    		count = pstmt.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		close(pstmt);
    	}
    	return count;
    }
}
