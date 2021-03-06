package org.mdoubleh.www.board.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
                //2020-11-10 00:00:00.0
                vo.setNotice_regdate(rs.getString("notice_regdate").substring(0,16));
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
	
	public int registerBoard(BoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("INSERT INTO noticeboard(notice_title, notice_content, member_id) "
							+ "VALUES(?, ?, ?, ?)");
			pstmt.setString(1, vo.getNotice_title());
			pstmt.setString(2, vo.getNotice_content());
			pstmt.setString(3, vo.getMember_id());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public BoardVo getBoard(int num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardVo vo = null;
        try {
            pstmt = con.prepareStatement
            		("SELECT * FROM noticeboard WHERE notice_postnum = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                vo = new BoardVo();
                vo.setNotice_postnum(rs.getInt("notice_postnum"));
                vo.setNotice_title(rs.getString("notice_title"));
                vo.setNotice_content(rs.getString("notice_content"));
                vo.setNotice_regdate(rs.getString("notice_regdate"));
                vo.setNotice_hit(rs.getInt("notice_hit"));
                vo.setMember_id(rs.getString("member_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return vo;
    }
	
	public int getHit(int num) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
            		("UPDATE noticeboard SET notice_hit = notice_hit + 1 WHERE notice_postnum = ?");
            pstmt.setInt(1, num);
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }
	
	public int modifyBoard(BoardVo vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
            		("UPDATE noticeboard SET "
            				+ "notice_title = ?, "
            				+ "notice_content = ?, "
            				+ "notice_regdate = ? "
            				+ "WHERE notice_postnum = ?");
            pstmt.setString(1, vo.getNotice_title());
            pstmt.setString(2, vo.getNotice_content());
            pstmt.setString(3,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            pstmt.setInt(4, vo.getNotice_postnum());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }
	
	public String getWriterId(int num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String id = null;
        try {
            pstmt = con.prepareStatement
            		("SELECT m.member_id FROM "
            				+ "noticeboard b INNER JOIN "
            				+ "mall_member m ON "
            				+ "b.member_id = m.member_id "
            				+ "WHERE notice_postnum = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                id = rs.getString("member_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return id;
    }
	
	public int deleteBoard(int num) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
            		("DELETE FROM noticeboard WHERE notice_postnum = ?");
            pstmt.setInt(1, num);
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }
	
}
