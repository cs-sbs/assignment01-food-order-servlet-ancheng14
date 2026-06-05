package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String path = req.getPathInfo();
        if (path == null || path.length() <= 1) {
            out.println("Error: Order ID missing");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(path.substring(1));
        } catch (NumberFormatException e) {
            out.println("Error: Invalid order ID");
            return;
        }

        ArrayList<Order> orders =
                (ArrayList<Order>) getServletContext().getAttribute("orders");

        for (Order o : orders) {
            if (o.getId() == id) {
                out.println(o);
                return;
            }
        }

        out.println("Error: Order not found");
    }
}