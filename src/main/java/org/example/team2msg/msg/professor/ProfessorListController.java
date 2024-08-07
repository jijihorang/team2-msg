package org.example.team2msg.msg.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.message.dao.MsgDAO;
import org.example.team2msg.msg.message.MsgVO;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(value = "/proflist")
public class ProfessorListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            log.info("Session not found, redirecting to login");
            resp.sendRedirect("/proflogin");
            return;
        }

        String professorId = (String) session.getAttribute("professorId");
        if (professorId == null) {
            log.info("Professor ID not found in session, redirecting to login");
            resp.sendRedirect("/proflogin");
            return;
        }

        try {
            List<MsgVO> messages = MsgDAO.INSTANCE.list(professorId);

            req.setAttribute("messages", messages);
            req.getRequestDispatcher("/WEB-INF/professor/proflist.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error retrieving messages", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}