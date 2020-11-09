package org.mdoubleh.www.board.item.dao;

import static org.mdoubleh.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.mdoubleh.www.board.item.vo.BoardVo;
import org.mdoubleh.www.board.review.vo.ReviewVo;
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
	
	public int getBoardCount() {
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement
            		("SELECT COUNT(*) FROM itemboard WHERE 1 = 1");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                count =rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return count;
	}
	
	public ArrayList<BoardVo> getBoardList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement
					("SELECT * FROM itemboard");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setItem_postnum(rs.getInt("item_postnum"));
				vo.setItem_title(rs.getString("item_title"));
				vo.setItem_content(rs.getString("item_content"));
				vo.setItem_price(rs.getInt("item_price"));
				vo.setItem_img(rs.getString("item_img"));
				vo.setItem_group(rs.getString("item_group"));
				vo.setItem_regdate(rs.getString("item_regdate"));
				vo.setItem_event(rs.getBoolean("item_event"));
				vo.setMember_id(rs.getString("member_id"));
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

	public ArrayList<BoardVo> getBoardList(Paging paging) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement
					("SELECT b.item_postnum, "
							+ "m.member_id, "
							+ "b.item_title, "
							+ "b.item_content, "
							+ "b.item_price, "
							+ "b.item_img,"
							+ "b.item_group, "
							+ "b.item_regdate, "
							+ "b.item_event "
							+ "FROM itemboard b "
							+ "INNER JOIN mall_member m "
							+ "ON b.member_id = m.member_id "
							+ "WHERE 1 = 1 "
							+ "ORDER BY item_postnum DESC "
							+ "LIMIT ?, ?");
			pstmt.setInt(1, paging.getStartBoardNumber());
			pstmt.setInt(2, paging.getSHOW_BOARD_COUNT());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setItem_postnum(rs.getInt("item_postnum"));
				vo.setItem_title(rs.getString("item_title"));
				vo.setItem_content(rs.getString("item_content"));
				vo.setItem_price(rs.getInt("item_price"));
				vo.setItem_img(rs.getString("item_img"));
				vo.setItem_group(rs.getString("item_group"));
				vo.setItem_regdate(rs.getString("item_regdate"));
				vo.setItem_event(rs.getBoolean("item_event"));
				vo.setMember_id(rs.getString("member_id"));
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
			pstmt = con.prepareStatement(
					"INSERT INTO itemboard(item_title, item_content, item_price, item_group, item_img, member_id) "
							+ "VALUES(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, vo.getItem_title());
			pstmt.setString(2, vo.getItem_content());
			pstmt.setInt(3, vo.getItem_price());
			pstmt.setString(4, vo.getItem_group());
			pstmt.setString(5, vo.getItem_img());
			pstmt.setString(6, vo.getMember_id());
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
			pstmt = con.prepareStatement("SELECT * FROM itemboard WHERE item_postnum = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setItem_postnum(rs.getInt("item_postnum"));
				vo.setItem_title(rs.getString("item_title"));
				vo.setItem_content(rs.getString("item_content"));
				vo.setItem_price(rs.getInt("item_price"));
				vo.setItem_img(rs.getString("item_img"));
				vo.setItem_group(rs.getString("item_group"));
				vo.setItem_regdate(rs.getString("item_regdate"));
				vo.setItem_event(rs.getBoolean("item_event"));
				vo.setMember_id(rs.getString("member_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}

	public String getWriterId(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		try {
			pstmt = con.prepareStatement("SELECT m.member_id FROM " + "itemboard b INNER JOIN " + "mall_member m ON "
					+ "b.member_id = m.member_id " + "WHERE item_postnum = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("member_id");
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
            		("UPDATE itemboard SET "
            				+ "item_title = ?, "
            				+ "item_content = ?, "
            				+ "item_price = ?, "
            				+ "item_img = ?, "
            				+ "item_group = ?, "
            				+ "item_regdate = ? "
            				+ "WHERE item_postnum = ?");
            pstmt.setString(1, vo.getItem_title());
            pstmt.setString(2, vo.getItem_content());
            pstmt.setInt(3, vo.getItem_price());
            pstmt.setString(4, vo.getItem_img());
            pstmt.setString(5, vo.getItem_group());
            pstmt.setString(6, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            pstmt.setInt(7, vo.getItem_postnum());
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
            		("DELETE FROM itemboard WHERE item_postnum = ?");
            pstmt.setInt(1, num);
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }
	
	public ArrayList<ReviewVo> getReviewBoardList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("SELECT * FROM reviewboard");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReviewVo vo = new ReviewVo();
				vo.setReview_postnum(rs.getInt("review_postnum"));
				vo.setItem_postnum(rs.getInt("item_postnum"));
				vo.setReview_img(rs.getString("review_img"));
				vo.setReview_title(rs.getString("review_title"));
				vo.setReview_content(rs.getString("review_content"));
				vo.setReview_hit(rs.getInt("review_hit"));
				vo.setReview_regdate(rs.getString("review_regdate"));
				vo.setMember_id(rs.getString("member_id"));
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

}
