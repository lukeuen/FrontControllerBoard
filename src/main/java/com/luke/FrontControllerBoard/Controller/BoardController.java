package com.luke.FrontControllerBoard.Controller;

import com.luke.FrontControllerBoard.Service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // forward에 사용할 prefix와 suffix
    private final String prefix = "/WEB-INF/Board/views/";
    private final String suffix = ".jsp";

    // 주소 : 객체를 저장할 Map
    private Map<String, BoardService> map = new HashMap<String, BoardService>();

    // 서블릿 초기화 메서드
    // [주소=클래스이름] => [주소=클래스] => [주소=객체]
    @Override
    public void init() throws ServletException {
        String configFile = getInitParameter("configFile");
        System.out.println(configFile);

        Properties pro = new Properties();
        FileInputStream fis = null;

        try {
            // JSP의 application 객체에 호출했던 메서드와 동일한 작업, 프로젝트 폴더 기준 경로를 잡을 때 사용
            String configFilePath = getServletContext().getRealPath(configFile);

            fis = new FileInputStream(configFilePath);	// 경로를 전달하여 파일데이터를 읽을 준비

            pro.load(fis);	// 불러온 파일의 내용을 pro 객체에 저장

        } catch(IOException e) {
            throw new ServletException(e);
        } finally {
            if(fis != null) try { fis.close(); } catch (Exception e1) {}
        }
        System.out.println("pro : " + pro);


        // pro 객체에 있는 value(패키지.클래스이름)을 이용해서 실제 클래스의 객체를 생성하고 불러온다
        Iterator<Object> it = pro.keySet().iterator();

        while(it.hasNext()) {
            String command = (String) it.next();
            String handlerClassName = pro.getProperty(command);	// 클래스 이름을 불러와서

            try {
                Class<?> handlerClass = Class.forName(handlerClassName); 	// 클래스를 로드하고
                BoardService handlerInstance = (BoardService) handlerClass.newInstance();	// 객체를 생성
                map.put(command, handlerInstance);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //request의 요청한 URI를 command에 저장한다.
        String command = req.getRequestURI();
        //command가 ContextPath 경로 요청일때
        if(command.indexOf(req.getContextPath()) == 0) {//ContextPath가 앞에나오면 무조건 0이다.
            //ContextPath를 제외한 나머지 명령을 커맨드에 저장한다
            command = command.substring(req.getContextPath().length());
        }

        String viewPage = null;

        BoardService action = map.get(command); 	// 주소를 이용하여 객체를 불러오기

        if(action == null) {
            req.getRequestDispatcher(prefix + "error" + suffix).forward(req, resp);
            return;
        }
        try {
            switch(req.getMethod()) {
                case "GET": 	viewPage = action.doGet(req, resp); break;
                case "POST": 	viewPage = action.doPost(req, resp); break;
                default: 		resp.sendRedirect(req.getContextPath());
            }
        } catch(Throwable e) {
            throw new ServletException(e);
        }


        // 리다이렉트와 포워드를 구분하여 처리하기
        if(viewPage.indexOf("redirect:") == 0) {	// 문자열 시작이 redirect: 이면
            String location = req.getContextPath() + viewPage.split(":")[1];
            resp.sendRedirect(location);
        }
        else {
            viewPage = prefix + viewPage + suffix;	// viewPage 앞뒤로 접두사/접미사를 붙여서 경로를 완성한다
            RequestDispatcher rd = req.getRequestDispatcher(viewPage);	// forward 준비
            System.out.println("forward to : " + viewPage);					// 출력, 확인
            rd.forward(req, resp);									// forward~
        }
    }
}
