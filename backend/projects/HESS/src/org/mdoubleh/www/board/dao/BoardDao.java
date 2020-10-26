package org.mdoubleh.www.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.mdoubleh.www.board.vo.BoardVo;
import org.mdoubleh.www.common.Paging;

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

	public int getBoardCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("SELECT COUNT(*) FROM board WHERE 1 = 1");
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

	public ArrayList<BoardVo> getBoardList(Paging paging) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement
					("SELECT b.db_sq, m.id, b.sj, b.cn, b.hit, b.dttm FROM board b "
							+ "INNER JOIN member m ON b.mb_sq = m.mb_sq WHERE 1 = 1 "
							+ "ORDER BY db_sq desc LIMIT ?, ?");
			pstmt.setInt(1, paging.getStartBoardNumber());
			pstmt.setInt(2, paging.getSHOW_BOARD_COUNT());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setDb_sq(rs.getInt("db_sq"));
				vo.setSj(rs.getString("sj"));
				vo.setCn(rs.getString("cn"));
				vo.setHit(rs.getInt("hit"));
				vo.setDttm(rs.getString("dttm"));
				vo.setId(rs.getString("id"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
					("INSERT INTO board(sj, cn, id) VALUES(?, ?, ?)");
			pstmt.setString(1, vo.getSj());
			pstmt.setString(2, vo.getCn());
			pstmt.setString(3, vo.getId());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

}
