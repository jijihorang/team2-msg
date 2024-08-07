package org.example.team2msg.msg.message;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.message.dao.MsgDAO;

import java.io.IOException;
import java.util.Optional;

@WebServlet (value = "/detail")
@Log4j2
public class ProfMsgController extends HttpServlet {

    private MsgDAO msgDAO = MsgDAO.INSTANCE; // MsgDAO 인스턴스 초기화

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doGet");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("professorId") == null) {
            resp.sendRedirect("/proflogin");
            return;
        }

        String receiver = (String) session.getAttribute("professorId");


        String messageIdStr = req.getParameter("messageId");//?messageId=xxx 값 받아오기
        if (messageIdStr != null && !messageIdStr.isEmpty()) {
            try {
                int messageId = Integer.parseInt(messageIdStr);

                Optional<MsgVO> messageOptional = null;
                try {
                    messageOptional = msgDAO.get(messageId, receiver);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

                if (messageOptional.isPresent()) {
                    req.setAttribute("message", messageOptional.get());
                    req.getRequestDispatcher("/WEB-INF/professor/detail.jsp").forward(req, resp);
                } else {
                    log.info("Message not found");
                    return;
                }
            } catch (NumberFormatException e) {
                log.error("Invalid message ID format: " + messageIdStr, e);
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid message ID format");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Message ID is missing");
        }
    }
}
