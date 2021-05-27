package com.luke.FrontControllerBoard.Service;

import com.luke.FrontControllerBoard.Repository.BoardDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class read implements BoardService {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int idx = Integer.parseInt(request.getParameter("idx"));
        System.out.println(idx);
        request.setAttribute("listinfo", BoardDAO.getInstance().getBoardInfo(idx));
        return "read";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }
}
