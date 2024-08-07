package org.example.team2msg.msg.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet (value = "/proflogout")
@Log4j2
public class ProfessorLogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("/proflogin");
            return;
        }

        session.invalidate();
        resp.sendRedirect("/proflogin");


    }
}
