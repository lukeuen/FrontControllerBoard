package com.luke.FrontControllerBoard.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {
    String doGet(HttpServletRequest request, HttpServletResponse response) throws Throwable;
    String doPost(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
