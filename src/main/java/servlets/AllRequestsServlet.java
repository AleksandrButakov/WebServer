package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class AllRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);

        /*
        Задача:
        Написать сервлет, который будет обрабатывать запросы на /mirror
        При получении GET запроса с параметром key=value сервлет должен вернуть в response строку содержащую value.
        Например, при GET запросе /mirror?key=hello сервер должен вернуть страницу, на которой есть слово "hello".
         */

        String pathInfo;
        String keyHello;

        /*
        pathInfo = (String) pageVariables.get("pathInfo");
        if (pathInfo.equals("/mirror")) {
            System.out.println("Yes word /mirror");
            pageVariables.put("message", "Yes word /mirror");

            keyHello = (String) pageVariables.get("parameters");
            System.out.println(keyHello);

        } else {
            System.out.println("No word /mirror");
            pageVariables.put("message", "No word /mirror");
        }
         */

        //pageVariables.put("message", "");

        //response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));

        response.getWriter().println(request.getParameter("key"));  /*это единственное концептуальное изменение в коде! по факту это всё*/

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("message", message == null ? "" : message);

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());

        return pageVariables;
    }
}