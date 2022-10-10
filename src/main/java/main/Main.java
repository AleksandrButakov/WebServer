package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.sql.rowset.FilteredRowSet;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        // TODO: Added logical

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();

        /**
         * коммент
         */

    }
}
