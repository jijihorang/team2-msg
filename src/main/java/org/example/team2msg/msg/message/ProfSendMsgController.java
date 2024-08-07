package org.example.team2msg.msg.message;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.message.dao.MsgDAO;
import org.example.team2msg.msg.professor.ProfessorVO;

import java.io.IOException;

@WebServlet (value = "/professor/sendmsg")
@Log4j2
public class ProfSendMsgController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        req.getRequestDispatcher("/WEB-INF/professor/profsendmsg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost");

        HttpSession session = req.getSession();

        if(session == null || session.getAttribute("professorId")==null){
            resp.sendRedirect("/proflogin");
            return;
        }

        ProfessorVO professor = (ProfessorVO)session.getAttribute("professor");

        if (professor == null) {
            log.info("professor is not exist");
            resp.sendRedirect("/proflogin");
            return;
        }

        String sender = professor.getPid();
        String receiver = req.getParameter("receiver");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        log.info(sender +" "+ receiver +" "+ title +" "+ content);
        boolean isRead = false;
        boolean isBroadcast = false;

        try {
            MsgVO msg = MsgVO.builder()
                    .receiver(receiver)
                    .title(title)
                    .content(content)
                    .sender(sender)
                    .is_read(isRead)
                    .is_broadcast(isBroadcast)
                    .build();
            MsgDAO.INSTANCE.sendMessage(msg);
            resp.sendRedirect(req.getContextPath() + "/proflist");
        } catch (Exception e) {
            log.error("Message sending failed", e);
            req.setAttribute("error", "Message sending failed. Please try again.");
            req.getRequestDispatcher("/WEB-INF/professor/profsendmsg.jsp").forward(req, resp);
        }
    }
}
