package org.mdoubleh.www.dao;

import static org.mdoubleh.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.mdoubleh.www.common.Pagenation;
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
        int sq = 0;
        try {
            pstmt = con.prepareStatement("SELECT mber_sq FROM inf_mber_tb WHERE id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	sq = rs.getInt("mber_sq");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return sq;
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
    
    public String getWriterId(int num) {
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String id = null;
    	try {
    		pstmt = con.prepareStatement("SELECT m.id FROM inf_articl_tb b INNER JOIN inf_mber_tb m ON b.mb_sq = m.mber_sq WHERE articl_sq = ?");
    		pstmt.setInt(1, num);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			id = rs.getString("id");
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		close(rs);
    		close(pstmt);
    	}
    	return id;
    }
    
    public int getArticleCount(String query) {
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int count = 0;
    	try {
    		pstmt = con.prepareStatement("SELECT COUNT(*) FROM inf_articl_tb WHERE 1 = 1" + query);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			count = rs.getInt(1);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
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
    				("SELECT b.articl_sq" +
    						", m.id" +
    						", b.sj" +
    						", b.cn" +
    						", b.hit" +
    						", b.dttm" +
    						" FROM inf_articl_tb b" +
    						" INNER JOIN inf_mber_tb m ON b.mb_sq = m.mber_sq" +
    						" WHERE 1 = 1" + query +
    						" ORDER BY articl_sq DESC" +
    						" limit ?, ?");
    		pstmt.setInt(1, pagenation.getStartArticleNumber());
    		pstmt.setInt(2, pagenation.getSHOW_ARTICLE_COUNT());
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			ArticleVo vo = new ArticleVo();
    			vo.setArticl_sq(rs.getInt("articl_sq"));
    			vo.setHit(rs.getInt("hit"));
    			vo.setDttm(rs.getString("dttm"));
    			vo.setSj(rs.getString("sj"));
    			vo.setCn(rs.getString("cn"));
    			vo.setId(rs.getString("id"));
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
    		pstmt = con.prepareStatement("SELECT" +
    				" b.articl_sq" +
    				", b.mb_sq" +
    				", b.sj" +
    				", b.cn" +
    				", b.hit" +
    				", b.dttm" +
    				", m.id" +
    				" FROM inf_articl_tb b" +
    				" INNER JOIN inf_mber_tb m ON b.mb_sq = m.mber_sq" +
    				" WHERE articl_sq = ?");
    		pstmt.setInt(1, num);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			vo = new ArticleVo();
    			vo.setArticl_sq(rs.getInt("articl_sq"));
    			vo.setHit(rs.getInt("hit"));
    			vo.setDttm(rs.getString("dttm"));
    			vo.setSj(rs.getString("sj"));
    			vo.setCn(rs.getString("cn"));
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
    
    public int insertArticle(ArticleVo vo) {
    	PreparedStatement pstmt = null;
    	int count = 0;
    	try {
    		pstmt = con.prepareStatement("INSERT INTO inf_articl_tb(mb_sq, sj, cn) VALUES(?, ?, ?)");
    		pstmt.setInt(1, vo.getMb_sq());
    		pstmt.setString(2, vo.getSj());
    		pstmt.setString(3, vo.getCn());
    		count = pstmt.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		close(pstmt);
    	}
    	return count;
    }
    
    public int deleteArticle(int num) {
    	PreparedStatement pstmt = null;
    	int count = 0;
    	try {
    		pstmt = con.prepareStatement("DELETE FROM inf_articl_tb WHERE articl_sq = ?");
    		pstmt.setInt(1, num);
    		count = pstmt.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		close(pstmt);
    	}
    	return count;
    }
    
    public int updateArticle(ArticleVo vo) {
    	PreparedStatement pstmt = null;
    	int count = 0;
    	try {
    		pstmt = con.prepareStatement("UPDATE inf_articl_tb SET sj = ?, cn = ?, dttm = ? WHERE articl_sq = ?");
    		pstmt.setString(1, vo.getSj());
    		pstmt.setString(2, vo.getCn());
    		pstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    		pstmt.setInt(4, vo.getArticl_sq());
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
