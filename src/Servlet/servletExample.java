package Servlet;

import DAO.*;
import Entity.Item;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet("/test")
public class servletExample extends HttpServlet
{

    private DAO dao;

    @Override
    public void init() throws ServletException
    {
        dao = new DAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter pw = resp.getWriter();
        try
        {
            pw.println("dao = " + dao);
            pw.println("now dao = " + dao);
            dao.save(new Item("test", "hope", 777));
            dao.close();
            pw.println("dao closed");
        }
        catch(Exception ex)
        {
            pw.println("FUCK, EXCEPTION");
            ex.printStackTrace(pw);
            pw.flush();
        }
        finally
        {
            pw.close();
        }
    }
}
