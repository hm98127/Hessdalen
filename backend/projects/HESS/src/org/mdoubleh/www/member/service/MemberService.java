package org.mdoubleh.www.member.service;

import java.sql.Connection;

import org.mdoubleh.www.member.dao.MemberDao;
import org.mdoubleh.www.member.vo.MemberVo;

import static org.mdoubleh.www.common.JdbcUtil.*;

public class MemberService {
	public boolean joinMember(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.joinMember(memberVo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}

	public int getMemberCount(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getMemberCount(id);
		close(con);
		return count;
	}

	public MemberVo getMember(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVo vo = dao.getMember(id);
		close(con);
		return vo;
	}
	
	public boolean loginMember(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.updateLogin(memberVo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean logoutMember(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		memberVo.setMb_sq(dao.getMemberSequence(memberVo.getId()));
		int count = dao.updateLogin(memberVo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean modifyMember(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.modifyMember(memberVo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean deleteMember(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.deleteMember(id);
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
