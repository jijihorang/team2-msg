package org.example.team2msg.msg;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.student.dao.StudentDAO;
import org.example.team2msg.msg.student.vo.StudentVO;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@WebServlet(value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login doGet");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("----------------------------");

        String sid = req.getParameter("sid");
        log.info(sid);

        String spw = req.getParameter("spw");
        log.info(spw);


        // JESSION_ID가 세션 장소에 있으면 반환 / 없으면 생성
        HttpSession session = req.getSession();


        // DB에서 해당 사용자 정보 확인 후 사용자 정보 얻어오기
        // 람다식(ifPresentOrElse) 사용
        try {
            Optional<StudentVO> result = StudentDAO.INSTANCE.get(sid, spw);
            result.ifPresentOrElse(  studentVO -> {
                session.setAttribute("sid", studentVO);
                try {
                    resp.sendRedirect("/studentList"); // 로그인 성공 시 mypage로 리다이렉트 !
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }, () -> {
                try {
                    resp.sendRedirect("/login");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } ); // 1번은 값이 있는 함수 / 2번은 아무 것도 없는 함수

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}