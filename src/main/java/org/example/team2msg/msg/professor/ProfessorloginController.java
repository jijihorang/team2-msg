package org.example.team2msg.msg.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.professor.dao.ProfessorDAO;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/proflogin")
@Log4j2
public class ProfessorloginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid=req.getParameter("id");
        String upw=req.getParameter("pw");

        //jsessionID가 있으면 반환 없으면 생성
        HttpSession session = req.getSession();

        //DB에서 사용자 정보를 확인해 정보를 얻어오기
        try {
            Optional<ProfessorVO> result = ProfessorDAO.INSTANCE.get(uid, upw);
            result.ifPresentOrElse( memberVO -> {
                Cookie loginCookie = new Cookie("member",uid);
                loginCookie.setPath("/");
                loginCookie.setMaxAge(60*60*24);

                resp.addCookie(loginCookie);

                try {
                    resp.sendRedirect("/mypage");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }, () -> {
                try {
                    resp.sendRedirect("/login");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
