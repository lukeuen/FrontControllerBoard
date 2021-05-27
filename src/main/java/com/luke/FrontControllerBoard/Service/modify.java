package com.luke.FrontControllerBoard.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class modify implements BoardService {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "modify";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }
}
