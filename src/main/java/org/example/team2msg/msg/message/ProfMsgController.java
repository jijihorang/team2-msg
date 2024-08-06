package org.example.team2msg.msg.message;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet (value = "/profdetail")
@Log4j2
public class ProfMsgController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        req.getRequestDispatcher("/WEB-INF/professor/detail.jsp").forward(req, resp);
    }

}
