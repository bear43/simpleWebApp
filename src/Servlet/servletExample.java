package Servlet;

import DAO.*;
import Entity.Dishes;
import Entity.Item;
import Entity.Officiant;
import Entity.Order;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

import static Util.SaveManager.saveArray;
import static Util.SaveManager.saveList;


@WebServlet("/getitems")
public class servletExample extends HttpServlet
{

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Map<String, String[]> map = req.getParameterMap();
        PrintWriter pw = resp.getWriter();
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        List<Item> items = new ArrayList<>();
        ItemsDAO dao = daoFactory.getItemsDAO();
        Item it;
        try
        {
            if (map.containsKey("id"))
            {
                String[] ids = map.get("id");
                for(String id : ids)
                {
                    it = (Item)dao.findByID(Integer.parseInt(id));
                    if(it != null) items.add(it);
                }
            }
            else
            {
                String[] names = map.get("name");
                String[] descriptions = map.get("description");
                String[] costs = map.get("cost");
                if(names == null) names = new String[]{null};
                    else names = names[0].split(" ");
                if(descriptions == null) descriptions = new String[]{null};
                    else descriptions = descriptions[0].split(" ");
                if(costs == null) costs = new String[]{null};
                    else costs = costs[0].split(" ");
                for(String n : names)
                    for(String d : descriptions)
                        for(String c : costs)
                            items.addAll(dao.joinResults(n, d, c == null ? 0 : Double.parseDouble(c)));
            }
            if(items.isEmpty())
              items.addAll(dao.findAll());
            items.forEach(pw::println);
        }
        catch (Exception ex)
        {
            ex.printStackTrace(pw);
        }
        finally
        {
            pw.close();
        }
    }
}
