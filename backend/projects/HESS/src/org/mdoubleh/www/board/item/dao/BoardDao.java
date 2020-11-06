package org.mdoubleh.www.board.item.dao;

import static org.mdoubleh.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.mdoubleh.www.board.item.vo.BoardVo;

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
            pstmt = con.prepareStatement("SELECT * FROM itemboard");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                BoardVo vo = new BoardVo();
                vo.setItem_postnum(rs.getInt("item_postnum"));
                vo.setItem_title(rs.getString("item_title"));
                vo.setItem_content(rs.getString("item_content"));
                vo.setItem_price(rs.getInt("item_price"));
                vo.setItem_img(rs.getString("item_img"));
                vo.setItem_group(rs.getString("item_group"));
                vo.setItem_regdate(rs.getString("item_regdate"));
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
