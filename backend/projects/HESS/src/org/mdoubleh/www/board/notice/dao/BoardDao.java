package org.mdoubleh.www.board.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.mdoubleh.www.board.notice.vo.BoardVo;

import static org.mdoubleh.www.common.JdbcUtil.close;

public class BoardDao {
	private Connection con;

	private BoardDao() {

	}

	public static BoardDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final BoardDao INSTANCE = new BoardDao();
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public ArrayList<BoardVo> getBoardList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BoardVo> list = new ArrayList<>();
        try {
            pstmt = con.prepareStatement("SELECT * FROM noticeboard");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                BoardVo vo = new BoardVo();
                vo.setNotice_postnum(rs.getInt("notice_postnum"));
                vo.setNotice_title(rs.getString("notice_title"));
                vo.setNotice_content(rs.getString("notice_content"));
                vo.setNotice_regdate(rs.getString("notice_regdate"));
                vo.setNotice_hit(rs.getInt("notice_hit"));
                vo.setMember_id(rs.getString("member_id"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return list;
    }
	
}
