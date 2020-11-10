package org.mdoubleh.www.member.service;

import java.sql.Connection;

import org.mdoubleh.www.member.dao.MemberDao;
import org.mdoubleh.www.member.vo.MemberVo;
import org.mdoubleh.www.member.vo.NaverVo;

import static org.mdoubleh.www.common.JdbcUtil.*;

public class MemberService {
	public boolean getNaverLogout(NaverVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		vo.setNaver_postnum(dao.getNaverNumber(vo.getNaver_id()));
		int count = dao.getNaverLogin(vo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean getNaverId(NaverVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.getNaverId(vo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public int getNaverCount(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getNaverCount(id);
		close(con);
		return count;
	}
	
	public boolean joinMember(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.joinMember(vo);
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
	
	public boolean getLogin(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.getLogin(vo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public boolean getLogout(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		vo.setMember_postnum(dao.getMemberNumber(vo.getMember_id()));
		int count = dao.getLogin(vo);
		if (count > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSucess;
	}
	
	public int getMemberNumber(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int num = dao.getMemberNumber(vo);
		close(con);
		return num;
	}
	
	public boolean getMemberPwd(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.getMemberPwd(vo);
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
	
	public boolean deleteMember(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.deleteMember(id);
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
	
}
