package com.luke.FrontControllerBoard.Service;

import com.luke.FrontControllerBoard.Repository.BoardDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class delete implements BoardService{
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int idx = Integer.parseInt(request.getParameter("idx"));
        if(BoardDAO.getInstance().deletePost(idx) != 0)
            return "redirect:list";
        else
            return "redirect:error";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }
}
