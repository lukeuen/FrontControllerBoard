package com.luke.FrontControllerBoard.Service;

import com.luke.FrontControllerBoard.Repository.BoardDAO;
import com.luke.FrontControllerBoard.Repository.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class write implements BoardService {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "write";
    }
    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if(BoardDAO.getInstance().addPost(title,content) != 0){
            return "redirect:list";
        }
        return "redirect:error";
    }
}
