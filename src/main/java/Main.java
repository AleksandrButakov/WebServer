import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.sql.rowset.FilteredRowSet;

public class Main {
    public static void main(String[] args) throws Exception {
        Frontend frontend = new Frontend();

        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //server.addServlet(new ServletHandler(frontend), "/author");
        context.addServlet(new ServletHolder((Servlet) frontend), "/authorization");

        server.start();
        server.join();
    }
}
