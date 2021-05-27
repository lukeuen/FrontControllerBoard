package com.luke.FrontControllerBoard.Repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    // DataSource, Singleton
    private DataSource ds;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Context init;
    private static final BoardDAO instance = new BoardDAO();

    public static BoardDAO getInstance() {
        return instance;
    }

    private BoardDAO() {
        try {
            init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
        } catch (NamingException e) {
            System.out.println("생성자 예외 발생 : " + e);
        }
    }
    private void close() {
        try {
            if(rs != null)		{		rs.close();		rs = null;			}
            if(pstmt != null) 	{		pstmt.close();	pstmt = null;		}
            if(conn != null)	{		conn.close();	conn = null;		}
        } catch (SQLException e) {
            System.out.println("close : " + e);
        }
    }

    public List<BoardDTO> getBoardList(){
        List<BoardDTO> list = null;
        String sql = "select * from TestBoard where deleted='n' order by idx desc";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();


            list = new ArrayList<BoardDTO>();
            while (rs.next()) {
                BoardDTO dto = new BoardDTO();
                dto.setContent(rs.getString("content"));
                dto.setDeleted(rs.getString("deleted"));
                dto.setIdx(rs.getInt("idx"));
                dto.setTitle(rs.getString("title"));
                dto.setWriteDate(rs.getString("writeDate"));
                dto.setWriter(rs.getString("writer"));
                list.add(dto);
            }

        } catch (SQLException e) {
            System.out.println("selectOne : " + e);
        } finally { close(); }


        return list;
    }

}
