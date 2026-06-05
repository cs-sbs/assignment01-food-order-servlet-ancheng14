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

        String name = req.getParameter("name");

        out.println("Menu List:\n");

        // ✅ 关键修复点：处理空搜索
        if (name == null || name.trim().isEmpty()) {
            // 空搜索 → 返回全部菜单
            for (MenuItem item : menu) {
                out.println(item);
            }
            return;
        }

        // 非空搜索 → 按名称过滤
        for (MenuItem item : menu) {
            if (item.getName().toLowerCase()
                    .contains(name.trim().toLowerCase())) {
                out.println(item);
            }
        }
    }
}