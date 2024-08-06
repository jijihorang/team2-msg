package org.example.team2msg.msg;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.student.dao.StudentDAO;

import java.io.IOException;

@Log4j2
@WebServlet(value = "/sign")
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/sign.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("sid");
        String smail = request.getParameter("smail");
        String spw = request.getParameter("spw");

        try {
            // 중복 확인
            boolean isDuplicate = StudentDAO.INSTANCE.isDuplicate(sid, smail);
            if (isDuplicate) {
                response.sendRedirect("/sign?error=duplicate"); // 중복된 경우 리다이렉트
                return;
            }

            // 중복이 아닌 경우에만 회원가입 진행
            boolean isRegistered = StudentDAO.INSTANCE.register(sid, smail, spw);
            if (isRegistered) {
                response.sendRedirect("/login"); // 회원가입 성공 시 로그인 페이지로 리다이렉트
            } else {
                response.sendRedirect("/sign?error=register"); // 회원가입 실패 시 리다이렉트
            }
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생", e);
            response.sendRedirect("/sign?error=exception"); // 예외 발생 시 리다이렉트
        }
    }
}
