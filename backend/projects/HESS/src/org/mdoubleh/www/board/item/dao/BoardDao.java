package org.mdoubleh.www.board.item.dao;

import static org.mdoubleh.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.mdoubleh.www.board.item.vo.ItemVo;
import org.mdoubleh.www.common.Paging;

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

	public ArrayList<ItemVo> getBoardList(Paging paging) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement
					("SELECT b.item_sq, m.id, b.price, b.nm, b.cont, b.item_img, b.kind "
							+ "FROM itemboard b "
							+ "INNER JOIN member m "
							+ "ON b.id = m.id "
							+ "WHERE 1 = 1 "
							+ "ORDER BY item_sq desc "
							+ "LIMIT ?, ?");
			pstmt.setInt(1, paging.getStartBoardNumber());
			pstmt.setInt(2, paging.getSHOW_BOARD_COUNT());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemVo vo = new ItemVo();
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setPrice(rs.getInt("price"));
				vo.setNm(rs.getString("nm"));
				vo.setCont(rs.getString("cont"));
				vo.setId(rs.getString("id"));
				vo.setItem_img(rs.getString("item_img"));
				vo.setKind(rs.getString("kind"));
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
	
	public ArrayList<ItemVo> getBoardList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement
					("SELECT b.item_sq, b.item_img "
							+ "FROM itemboard");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemVo vo = new ItemVo();
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setItem_img(rs.getString("item_img"));
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
	
	public int getBoardCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement
					("SELECT COUNT(*) "
							+ "FROM itemboard "
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
	
	public ItemVo getBoard(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVo vo = null;
		try {
			pstmt = con.prepareStatement
					("SELECT b.item_sq, b.nm, b.price, item_img, m.id "
							+ "FROM itemboard b "
							+ "INNER JOIN member m "
							+ "ON b.id = m.id "
							+ "WHERE item_sq = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ItemVo();
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setNm(rs.getString("nm"));
				vo.setPrice(rs.getInt("price"));
				vo.setItem_img(rs.getString("item_img"));
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

}
