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
import org.example.team2msg.common.PageInfo;

import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(value = "/proflist")
public class ProfessorListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

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

        int page = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
        int size = 10; // 페이지당 표시할 메시지 수

        try {
            int total = MsgDAO.INSTANCE.getTotalCount(professorId);
            req.setAttribute("total",total);
            req.setAttribute("size",size);
            req.setAttribute("page",page);
            log.info("total: {} size: {} page: {}",total,size,page);

            PageInfo pageInfo = new PageInfo(page, size, total);

            List<MsgVO> messages = MsgDAO.INSTANCE.getReceivedMessages(professorId, page, size);

            req.setAttribute("pageInfo", pageInfo);
            req.setAttribute("messages", messages);
            req.getRequestDispatcher("/WEB-INF/professor/proflist.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error retrieving messages", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}
