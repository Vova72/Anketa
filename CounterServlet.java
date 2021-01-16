package com.example.TestCounter;

import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "Count", urlPatterns = {"/count"})
public class CounterServlet extends HttpServlet {
    private AtomicInteger javaNo = new AtomicInteger(0);
    private AtomicInteger javaYes = new AtomicInteger(0);
    private AtomicInteger javaScriptNo = new AtomicInteger(0);
    private AtomicInteger javaScriptYes = new AtomicInteger(0);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String java = req.getParameter("java");
        String javaScript = req.getParameter("javaScript");
        try {
            if (java.equals("Yes")) {
                javaYes.getAndIncrement();
            }
            if (java.equals("No")) {
                javaNo.getAndIncrement();
            }
            if (javaScript.equals("Yes")) {
                javaScriptYes.getAndIncrement();
            }
            if (javaScript.equals("No")) {
                javaScriptNo.getAndIncrement();
            }
            String table = "<table border='1'>" +
                    "<tr><td>Question</td><td>Yes</td><td>No</td></tr>" +
                    "<tr><td>Do you know Java?</td><td>%s</td><td>%s</td></tr>" +
                    "<tr><td>Do you know JavaScript?</td><td>%s</td><td>%s</td></tr></table>";
            resp.getWriter().println(String.format(table, javaYes, javaNo, javaScriptYes, javaScriptNo));
        } catch (NullPointerException e) {
            e.printStackTrace();
            resp.getWriter().println("<html><h1>You have not answered all questions</h1></html>");
        }

    }
}
