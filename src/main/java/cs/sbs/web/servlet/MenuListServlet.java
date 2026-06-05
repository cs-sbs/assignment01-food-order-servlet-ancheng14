package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MenuListServlet extends HttpServlet {

    private static final ArrayList<MenuItem> menu = new ArrayList<>();

    @Override
    public void init() {
        menu.add(new MenuItem(1, "Fried Rice", 8));
        menu.add(new MenuItem(2, "Fried Noodles", 9));
        menu.add(new MenuItem(3, "Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String searchQuery = req.getParameter("search");

        out.println("Menu List:\n");

        // 空搜索 -> 返回全部菜单
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            for (MenuItem item : menu) {
                out.println(item);
            }
            return;
        }

        // 搜索
        boolean found = false;
        for (MenuItem item : menu) {
            if (item.getName().toLowerCase().contains(searchQuery.trim().toLowerCase())) {
                out.println(item);
                found = true;
            }
        }

        if (!found) {
            out.println("No items found.");
        }
    }
}