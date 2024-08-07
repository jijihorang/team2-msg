package org.example.team2msg.msg.message;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.message.dao.MsgDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet (value = "/professor/deleteMsg")
@Log4j2
public class ProfMsgDelController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost");

        String mnoStr = req.getParameter("replyToId");

        int mno = Integer.parseInt(mnoStr);

        log.info("mno: " + mno);

        boolean result = false;

        try {
            result = MsgDAO.INSTANCE.delete(mno);
            log.info("Message deleted successfully");
            resp.sendRedirect("/proflist");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
