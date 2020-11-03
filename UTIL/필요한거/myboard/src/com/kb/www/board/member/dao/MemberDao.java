package com.kb.www.board.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kb.www.board.member.vo.MemberVo;

import static com.kb.www.common.JdbcUtil.*;

public class MemberDao {
	private Connection con;
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private static class LazyHolder {
		private static final MemberDao INSTANCE = new MemberDao();
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int getMemberCount(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from member where binary(id)=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
	
	public int joinMember(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement
					("insert into member(nm, id, pwd) values(?, ?, ?)");
			pstmt.setString(1, vo.getNm());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPwd());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return count;
	}
	
	public MemberVo getMemberInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		try {
			pstmt = con.prepareStatement
					("select * from member where binary(id)=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVo();
				vo.setMb_sq(rs.getInt("mb_sq"));
				vo.setNm(rs.getString("nm"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return vo;
	}
	
	public int leaveMember(String id) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement
					("delete from member where binary(id)=?");
			pstmt.setString(1, id);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return count;
	}
	
	public int modifyMemberInfo(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement
					("update member set nm=? where binary(id)=?");
			pstmt.setString(1, vo.getNm());
			pstmt.setString(2, vo.getId());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return count;
	}
	
	public String getMemberId(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		try {
			pstmt = con.prepareStatement
					("select id from member where nm=?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
	
	public int getMemberSequence(MemberVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mb_sq = 0;
		try {
			pstmt = con.prepareStatement
					("select mb_sq from member "
							+ "where nm=? and binary(id)=?");
			pstmt.setString(1, vo.getNm());
			pstmt.setString(2, vo.getId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mb_sq = rs.getInt("mb_sq");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return mb_sq;
	}
	
	public int updatePwd(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement
					("update member set pwd=? where mb_sq=?");
			pstmt.setString(1, vo.getPwd());
			pstmt.setInt(2, vo.getMb_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return count;
	}
}


































