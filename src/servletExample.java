import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;


@WebServlet("/test")
public class servletExample extends HttpServlet
{
    @Resource(name = "jdbc/db")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter pw = resp.getWriter();
        try
        {
            Context ctx = new InitialContext();
            Connection c = ds.getConnection();
            Statement s = c.createStatement();
            pw.println("execute = " + s.execute("INSERT INTO ss(test) VALUES ('fix');"));
            s.close();
            c.close();
        }
        catch(Exception ex)
        {
            pw.println("FUCK, EXCEPTION");
            pw.println(ex.toString());
            pw.flush();
        }
        finally
        {
            pw.close();
        }
    }
}
