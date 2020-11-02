package org.mdoubleh.www.member.service;

import java.sql.Connection;

import org.mdoubleh.www.member.dao.MemberDao;
import org.mdoubleh.www.member.vo.MemberVo;

import static org.mdoubleh.www.common.JdbcUtil.*;

public class MemberService {
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
	
}
