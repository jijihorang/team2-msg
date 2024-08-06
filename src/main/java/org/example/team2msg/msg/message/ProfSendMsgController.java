package org.example.team2msg.msg.message;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

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

        String sender = req.getParameter("sender");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        System.out.println("sender = " + sender);
        System.out.println("title = " + title);
        System.out.println("content = " + content);

        try {
            Integer mno = MsgDAO.INSTANCE.sendMessage(sender, title, content); // 여기서 자꾸 오류남
            req.setAttribute("mno", mno);
            req.setAttribute("title", title);
            req.setAttribute("content", content);

            req.getRequestDispatcher("/WEB-INF/professor/proflist.jsp").forward(req, resp);


        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Message sending failed. Please try again.");
            req.getRequestDispatcher("/WEB-INF/professor/profsendmsg.jsp").forward(req, resp);

        }

    }
}
