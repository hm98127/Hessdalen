package org.mdoubleh.www.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mdoubleh.www.member.vo.MemberVo;

import static org.mdoubleh.www.common.JdbcUtil.close;

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

	public int joinMember(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("INSERT INTO member(nm, id, pwd) VALUES(?, ?, ?)");
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

	public int getMemberCount(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM member WHERE BINARY(id) = ?");
			pstmt.setString(1, id);
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

	public MemberVo getMember(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		try {
			pstmt = con
					.prepareStatement("SELECT mb_sq, nm, id, pwd FROM member WHERE BINARY(id) = ? AND del_fl = false");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

	public int updateLogin(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("UPDATE member SET lgn_fl = ? WHERE mb_sq = ? AND del_fl = false");
			pstmt.setBoolean(1, vo.isLgn_fl());
			pstmt.setInt(2, vo.getMb_sq());
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
			pstmt = con.prepareStatement("SELECT mb_sq FROM member WHERE BINARY(id) = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sq = rs.getInt("mb_sq");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return sq;
	}
	
	public int modifyMember(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("UPDATE member SET pwd = ? WHERE nm = ? AND id = ?");
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getNm());
			pstmt.setString(3, vo.getId());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int deleteMember(String id) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("DELETE FROM member WHERE BINARY(id) = ?");
			pstmt.setString(1, id);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

}
