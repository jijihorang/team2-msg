package org.example.team2msg.msg.student;

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
@WebServlet("/studentlist")
public class StudentListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("StudentList doGet");

        HttpSession session = req.getSession();
        StudentVO student = (StudentVO) session.getAttribute("student");

        if (student == null) {
            resp.sendRedirect(req.getContextPath() + "/login?error=Not logged in");
            return;
        }

        String sid = student.getSid();

        try {
            List<MsgVO> messages = MsgDAO.INSTANCE.getReceivedMessages(sid);
            req.setAttribute("messageType", "received");
            req.setAttribute("messages", messages);
            req.setAttribute("studentName", student.getSid());
            req.getRequestDispatcher("/WEB-INF/student/studentlist.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error retrieving messages", e);
            resp.sendRedirect(req.getContextPath() + "/studentlist?error=server_error");
        }
    }
}
