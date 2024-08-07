package org.example.team2msg.msg.message;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import org.example.team2msg.msg.message.dao.MsgDAO;
import org.example.team2msg.msg.student.StudentVO;

@Log4j2
@WebServlet(value = "/student/sendmsg")
public class StudSendMsgController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> studentList = MsgDAO.INSTANCE.getStudentList();
            List<String> professorList = MsgDAO.INSTANCE.getProfessorList();

            HttpSession session = req.getSession();
            StudentVO student = (StudentVO) session.getAttribute("student");
            if (student == null) {
                resp.sendRedirect(req.getContextPath() + "/login?error=Not logged in");
                return;
            }

            req.setAttribute("studentList", studentList);
            req.setAttribute("professorList", professorList);
            req.setAttribute("student", student);

            req.getRequestDispatcher("/WEB-INF/student/stusendmsg.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error retrieving student or professor list", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        StudentVO student = (StudentVO) session.getAttribute("student");

        if (student == null) {
            resp.sendRedirect(req.getContextPath() + "/login?error=Not logged in");
            return;
        }

        String sender = student.getSid();  // StudentVO 객체에서 sid 값을 추출합니다.

        String receiver = req.getParameter("receiver");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
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
            resp.sendRedirect(req.getContextPath() + "/studentlist");
        } catch (Exception e) {
            log.error("Message sending failed", e);
            resp.sendRedirect(req.getContextPath() + "/student/sendmsg?error=Message sending failed");
        }
    }
}
