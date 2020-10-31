package org.mdoubleh.www.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.mdoubleh.www.board.item.vo.ItemVo;
import org.mdoubleh.www.board.vo.BoardVo;
import org.mdoubleh.www.board.vo.EvBoardVo;
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

	// 공지사항 게시판
	public int getBoardCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("SELECT COUNT(*) "
							+ "FROM board "
							+ "WHERE 1 = 1");
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
					("SELECT b.bd_sq, m.id, b.sj, b.cn, b.hit, b.dttm "
							+ "FROM board b "
							+ "INNER JOIN member m "
							+ "ON b.id = m.id "
							+ "WHERE 1 = 1 "
							+ "ORDER BY bd_sq desc "
							+ "LIMIT ?, ?");
			pstmt.setInt(1, paging.getStartBoardNumber());
			pstmt.setInt(2, paging.getSHOW_BOARD_COUNT());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setBd_sq(rs.getInt("bd_sq"));
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
					("INSERT INTO board(sj, cn, id) "
							+ "VALUES(?, ?, ?)");
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
	
	public BoardVo getBoard(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			pstmt = con.prepareStatement
					("SELECT b.bd_sq, b.sj, b.cn, b.hit, b.dttm, m.id "
							+ "FROM board b "
							+ "INNER JOIN member m "
							+ "ON b.id = m.id "
							+ "WHERE bd_sq = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setBd_sq(rs.getInt("bd_sq"));
				vo.setSj(rs.getString("sj"));
				vo.setCn(rs.getString("cn"));
				vo.setHit(rs.getInt("hit"));
				vo.setDttm(rs.getString("dttm"));
				vo.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}
	
	public int updateHitCount(int num) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("UPDATE board "
							+ "SET hit = hit + 1 "
							+ "WHERE bd_sq = ?");
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
					("SELECT m.id "
							+ "FROM board b "
							+ "INNER JOIN member m "
							+ "ON b.id = m.id "
							+ "WHERE bd_sq = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}
	
	public int modifyBoard(BoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("UPDATE board "
							+ "SET sj = ?, cn = ?, dttm = ? "
							+ "WHERE bd_sq = ?");
			pstmt.setString(1, vo.getSj());
			pstmt.setString(2, vo.getCn());
			pstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pstmt.setInt(4, vo.getBd_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int deleteBoard(int num) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("DELETE FROM board "
							+ "WHERE bd_sq = ?");
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	// 이벤트 게시판
	public int getEventBoardCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("SELECT COUNT(*) "
							+ "FROM ev_board "
							+ "WHERE 1 = 1");
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
	
	public ArrayList<EvBoardVo> getEventBoardList(Paging paging) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EvBoardVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement
					("SELECT b.ev_sq, m.id, b.sj, b.cn, b.hit, b.dttm "
							+ "FROM ev_board b "
							+ "INNER JOIN member m "
							+ "ON b.id = m.id "
							+ "WHERE 1 = 1 "
							+ "ORDER BY ev_sq desc "
							+ "LIMIT ?, ?");
			pstmt.setInt(1, paging.getStartBoardNumber());
			pstmt.setInt(2, paging.getSHOW_BOARD_COUNT());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvBoardVo vo = new EvBoardVo();
				vo.setEv_sq(rs.getInt("ev_sq"));
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
	
	public int registerEventBoard(EvBoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("INSERT INTO ev_board(sj, cn, id) "
							+ "VALUES(?, ?, ?)");
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
	
	public int updateHitEventCount(int num) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("UPDATE ev_board "
							+ "SET hit = hit + 1 "
							+ "WHERE ev_sq = ?");
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public EvBoardVo getEventBoard(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EvBoardVo vo = null;
		try {
			pstmt = con.prepareStatement
					("SELECT b.ev_sq, b.sj, b.cn, b.hit, b.dttm, m.id "
							+ "FROM ev_board b "
							+ "INNER JOIN member m "
							+ "ON b.id = m.id "
							+ "WHERE ev_sq = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new EvBoardVo();
				vo.setEv_sq(rs.getInt("ev_sq"));
				vo.setSj(rs.getString("sj"));
				vo.setCn(rs.getString("cn"));
				vo.setHit(rs.getInt("hit"));
				vo.setDttm(rs.getString("dttm"));
				vo.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}
	
	public String getWriterEventId(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		try {
			pstmt = con.prepareStatement
					("SELECT m.id "
							+ "FROM ev_board b "
							+ "INNER JOIN member m "
							+ "ON b.id = m.id "
							+ "WHERE ev_sq = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}
	
	public int modifyEventBoard(EvBoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("UPDATE ev_board "
							+ "SET sj = ?, cn = ?, dttm = ? "
							+ "WHERE ev_sq = ?");
			pstmt.setString(1, vo.getSj());
			pstmt.setString(2, vo.getCn());
			pstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pstmt.setInt(4, vo.getEv_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int deleteEventBoard(int num) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("DELETE FROM ev_board "
							+ "WHERE ev_sq = ?");
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	// 상품 게시판
	
	
}
