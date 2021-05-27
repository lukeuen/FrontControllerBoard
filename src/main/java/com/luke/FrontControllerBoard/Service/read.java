package com.luke.FrontControllerBoard.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class read implements BoardService {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "read";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }
}
