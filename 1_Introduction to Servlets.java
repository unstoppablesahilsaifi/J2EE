==> What is a Servlet?
A Servlet is a Java class that handles HTTP requests and generates responses, usually in a web application. It runs inside a Servlet container (like Apache Tomcat).

==> Basic Lifecycle of a Servlet:
There are three main life-cycle methods defined in javax.servlet.Servlet interface:
init(): Called once when the servlet is first loaded.
service(): Called every time the servlet is requested.
destroy(): Called when the servlet is being taken down.
  
Servlet Flow:
Client (browser) sends HTTP request.
Web container maps request to a Servlet.
Servlet processes the request in the service() method.
Servlet sends HTTP response back to the client.

Lifecycle in Code (Basic Example)

  public class LifeCycleServlet extends HttpServlet {

    public void init() {
        System.out.println("Servlet initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("Servlet is handling request");
    }

    public void destroy() {
        System.out.println("Servlet destroyed");
    }
}

==> Servlet Configuration
  <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.example.HelloServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>

Explanation:
<servlet> defines the servlet class.
<servlet-mapping> binds it to a URL pattern (/hello).
When user visits http://localhost:8080/YourApp/hello, it invokes HelloServlet.
.
