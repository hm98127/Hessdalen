package com.kb.www.board.member.service;

import java.sql.Connection;

import com.kb.www.board.member.dao.MemberDao;
import com.kb.www.board.member.vo.MemberVo;

import static com.kb.www.common.JdbcUtil.*;

public class MemberService {
	public int getMemberCount(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getMemberCount(id);
		close(con);
		return count;
	}
	
	public boolean joinMember(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.joinMember(memberVo);
		if (count > 0) {
			isSucess = true;
			commit(con);
		} else {
			isSucess = false;
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public MemberVo getMemberInfo(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVo vo = dao.getMemberInfo(id);
		close(con);
		return vo;
	}
	
	public boolean leaveMember(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.leaveMember(id);
		if (count > 0) {
			isSucess = true;
			commit(con);
		} else {
			isSucess = false;
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean modifyMemberInfo(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.modifyMemberInfo(vo);
		if (count > 0) {
			isSucess = true;
			commit(con);
		} else {
			isSucess = false;
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public String getMemberId(String name) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		String id = dao.getMemberId(name);
		close(con);
		return id;
	}
	
	public int getMemberSequence(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int mb_sq = dao.getMemberSequence(vo);
		close(con);
		return mb_sq;
	}
	
	public boolean updatePwd(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.updatePwd(vo);
		if (count > 0) {
			isSucess = true;
			commit(con);
		} else {
			isSucess = false;
			rollback(con);
		}
		close(con);
		return isSucess;
	}
}



















