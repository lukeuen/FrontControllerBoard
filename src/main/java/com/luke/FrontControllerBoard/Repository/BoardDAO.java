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

    public BoardDTO getBoardInfo(int idx){
        BoardDTO boardDTO = null;
        String sql = "select * from TestBoard where idx=?";
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                boardDTO = new BoardDTO();
                boardDTO.setContent(rs.getString("content"));
                System.out.println(rs.getString("content"));
                boardDTO.setDeleted(rs.getString("deleted"));
                boardDTO.setIdx(rs.getInt("idx"));
                boardDTO.setTitle(rs.getString("title"));
                boardDTO.setWriteDate(rs.getString("writeDate"));
                boardDTO.setWriter(rs.getString("writer"));
            }

        } catch (SQLException e) {
            System.out.println("getBoardInfo : " + e);
        } finally { close(); }

        return boardDTO;
    }
    public int editPost(String title,String content,int idx){
        int row = 0;
        String sql = "update TESTBOARD set title=?,content=? where idx=?";
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3,idx);
            row = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("editPost : " + e);
        } finally { close(); }
        return row;
    }
    public int addPost(String title,String content){
        int row = 0;
        String sql = "insert into TESTBOARD (idx, title, writer, content) VALUES (TestBoard_seq.nextval,?,?,?)";
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, "테스트계정");
            pstmt.setString(3, content);
            row = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("insert : " + e);
        } finally { close(); }
        return row;
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
            System.out.println("getBoardList : " + e);
        } finally { close(); }

        return list;
    }

}
