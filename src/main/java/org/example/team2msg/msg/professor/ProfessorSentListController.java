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

@WebServlet (value = "/proflist/sent")
@Log4j2
public class ProfessorSentListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        ProfessorVO professor = (ProfessorVO)session.getAttribute("professor");

        if (professor == null) {
            resp.sendRedirect(req.getContextPath() + "/proflogin");
            return;
        }

        String pid = professor.getPid();

        try {
            List<MsgVO> messages = MsgDAO.INSTANCE.getSentMessages(pid);
            req.setAttribute("messageType", "sent");
            req.setAttribute("messages", messages);
            req.setAttribute("professorName", professor.getPid());
            req.getRequestDispatcher("/WEB-INF/professor/profsentlist.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost");
    }
}
