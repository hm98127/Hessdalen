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
			pstmt = con.prepareStatement
					("INSERT INTO "
							+ "mall_member(member_name, member_id, member_pwd) "
							+ "VALUES(?, ?, ?)");
			pstmt.setString(1, vo.getMember_name());
			pstmt.setString(2, vo.getMember_id());
			pstmt.setString(3, vo.getMember_pwd());
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
			pstmt = con.prepareStatement
					("SELECT COUNT(*) FROM mall_member WHERE BINARY(member_id) = ?");
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
					.prepareStatement
					("SELECT member_postnum, member_name, member_id, member_pwd FROM mall_member "
							+ "WHERE BINARY(member_id) = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setMember_postnum(rs.getInt("member_postnum"));
				vo.setMember_name(rs.getString("member_name"));
				vo.setMember_id(rs.getString("member_id"));
				vo.setMember_pwd(rs.getString("member_pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}
	
	public int getLogin(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("UPDATE mall_member SET check_login = ? "
							+ "WHERE member_postnum = ?");
			pstmt.setBoolean(1, vo.isCheck_login());
			pstmt.setInt(2, vo.getMember_postnum());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int getMemberNumber(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			pstmt = con.prepareStatement
					("SELECT member_postnum FROM mall_member WHERE BINARY(member_id) = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("member_postnum");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return num;
	}
	
	public int getMemberNumber(MemberVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			pstmt = con.prepareStatement
					("SELECT member_postnum FROM mall_member WHERE member_name = ? AND BINARY(member_id) = ?");
			pstmt.setString(1, vo.getMember_name());
			pstmt.setString(2, vo.getMember_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("member_postnum");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return num;
	}
	
	public int getMemberPwd(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("UPDATE mall_member SET member_pwd = ? WHERE member_postnum = ?");
			pstmt.setString(1, vo.getMember_pwd());
			pstmt.setInt(2, vo.getMember_postnum());
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
			pstmt = con.prepareStatement
					("DELETE FROM mall_member WHERE BINARY(member_id) = ?");
			pstmt.setString(1, id);
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
					("SELECT member_id FROM mall_member WHERE member_name = ?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				id = rs.getString("member_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}
	
}
