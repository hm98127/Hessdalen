package org.mdoubleh.www.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
							+ "member(member_name, member_id, member_pwd) "
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
	
}
