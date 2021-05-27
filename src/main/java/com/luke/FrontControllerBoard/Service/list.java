package com.luke.FrontControllerBoard.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class list implements BoardService {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "list";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }
}
