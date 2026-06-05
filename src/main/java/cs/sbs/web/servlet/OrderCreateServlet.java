package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OrderCreateServlet extends HttpServlet {

    private static final ArrayList<Order> orders = new ArrayList<>();
    private static int nextId = 1001;

    @Override
    public void init() {
        getServletContext().setAttribute("orders", orders);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String qty = req.getParameter("quantity");

        if (customer == null || food == null || qty == null ||
                customer.isBlank() || food.isBlank() || qty.isBlank()) {
            out.println("Error: All fields are required.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(qty);
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
            return;
        }

        Order order = new Order(nextId++, customer, food, quantity);
        orders.add(order);

        out.println("Order Created: " + order.getId());
        out.println("\n" + order);
    }
}