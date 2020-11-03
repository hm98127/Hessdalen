package com.kb.www.board.board.dao;

import static com.kb.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kb.www.board.board.vo.ArticleVo;
import com.kb.www.board.member.vo.MemberVo;


public class ArticleDao {
	private Connection con;

	private ArticleDao() {
		
	}

	public static ArticleDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final ArticleDao INSTANCE = new ArticleDao();
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public ArrayList<ArticleVo> getArticleList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVo> list = new ArrayList<ArticleVo>();
		try {
			pstmt = con.prepareStatement
					("select * from article order by sq desc");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ArticleVo vo = new ArticleVo();
				vo.setSq(rs.getInt("sq"));
				vo.setSub(rs.getString("sub"));
				vo.setWriter(rs.getString("writer"));
				vo.setDt(rs.getString("dt"));
				vo.setHit(rs.getInt("hit"));
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
	
	public int registerArticle(ArticleVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement
					("insert into article(sub, cntnt, writer)"
							+ " values(?, ?, ?)");
			pstmt.setString(1, vo.getSub());
			pstmt.setString(2, vo.getCntnt());
			pstmt.setString(3, vo.getWriter());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return count;
	}
}











