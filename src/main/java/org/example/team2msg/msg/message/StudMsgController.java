package org.example.team2msg.msg.message;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.message.dao.MsgDAO;
import org.example.team2msg.msg.student.StudentVO;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/studetail")
@Log4j2
public class StudMsgController extends HttpServlet {

    private MsgDAO msgDAO = MsgDAO.INSTANCE; // MsgDAO 인스턴스 초기화

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Student detail doGet");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("student") == null) {
            resp.sendRedirect("/login");
            return;
        }

        StudentVO student = (StudentVO) session.getAttribute("student");
        String receiver = student.getSid();

        String messageIdStr = req.getParameter("messageId");
        if (messageIdStr != null && !messageIdStr.isEmpty()) {
            try {
                int messageId = Integer.parseInt(messageIdStr);

                Optional<MsgVO> messageOptional = msgDAO.get(messageId, receiver);
                if (messageOptional.isPresent()) {
                    req.setAttribute("message", messageOptional.get());
                    req.getRequestDispatcher("/WEB-INF/student/studentdetail.jsp").forward(req, resp);
                } else {
                    log.info("Message not found");
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Message not found");
                }
            } catch (NumberFormatException e) {
                log.error("Invalid message ID format: " + messageIdStr, e);
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid message ID format");
            } catch (Exception e) {
                log.error("Error getting message detail", e);
                throw new ServletException(e);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Message ID is missing");
        }
    }
}
