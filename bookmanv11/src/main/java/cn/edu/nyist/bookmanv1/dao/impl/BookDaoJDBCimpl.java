package cn.edu.nyist.bookmanv1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import cn.edu.nyist.bookmanv1.dao.BookDao;
import cn.edu.nyist.bookmanv1.util.DsUtil;
import cn.edu.nyist.bookmanv1.vo.BookVo;

public class BookDaoJDBCimpl implements BookDao {

	@Override
	public int save(BookVo bookVo) {
		Connection conn=null;
		PreparedStatement stmt=null;
		
		try {
			conn=DsUtil.getConn();
			String sql="insert into t_book(name,descri,photo,price,tid,author,pubDate)values(?,?,?,?,?,?,?)";
			
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, bookVo.getName());
			stmt.setString(2, bookVo.getDescri());
			stmt.setString(3, bookVo.getPhoto());
			stmt.setDouble(4, bookVo.getPrice());
			stmt.setInt(5, bookVo.getTid());
			stmt.setString(6, bookVo.getAuthor());
			stmt.setDate(7,new java.sql.Date(bookVo.getPubDate().getTime()));
			int ret= stmt.executeUpdate();
			return ret;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DsUtil.free(stmt, conn);
		}
		return 0;
	}

}
