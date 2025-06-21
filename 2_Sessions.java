Session Management in Servlets

Web is **stateless** — each request is independent. So, to track user information (like login), we use **sessions**.

In Java EE, session management is handled through the `HttpSession` interface.

## **1. What is a Session?**

* A **session** is a server-side storage of user data across multiple requests from the **same client**.
* Identified via a **session ID**, usually stored in a cookie (like `JSESSIONID`).

## **2. Creating or Getting a Session**

```java
HttpSession session = request.getSession(); // creates one if it doesn’t exist
```

### Or:

```java
HttpSession session = request.getSession(false); // returns null if no session exists
```

---

## **3. Setting and Getting Session Attributes**

```java
// Store data
session.setAttribute("username", "john");

// Retrieve data
String name = (String) session.getAttribute("username");
```

---

## **4. Invalidating a Session (Logout)**

```java
session.invalidate();
```

---

## **Example: Login Session Flow**

### `LoginServlet.java` – sets session

```java
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        if("admin".equals(user) && "1234".equals(pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user);
            response.sendRedirect("dashboard");
        } else {
            response.getWriter().println("Invalid login");
        }
    }
}
```

---

### `DashboardServlet.java` – reads session

```java
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String name = (String) session.getAttribute("username");
            response.getWriter().println("Welcome, " + name);
        } else {
            response.sendRedirect("form.jsp");
        }
    }
}

## **5. Session Timeout**

### Set timeout in web.xml:

```xml
<session-config>
    <session-timeout>30</session-timeout> <!-- in minutes -->
</session-config>
```

### Or programmatically:

session.setMaxInactiveInterval(60 * 10); // 10 minutes


| Feature          | Description                                |
| ---------------- | ------------------------------------------ |
| `getSession()`   | Returns current session or creates new one |
| `setAttribute()` | Stores user data                           |
| `getAttribute()` | Retrieves stored data                      |
| `invalidate()`   | Logs out user                              |
| Session timeout  | Auto logout after idle time                |


+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


Best Example for session (Login & Logout)

    Bilkul! Ab mai aapko **J2EE (Java EE / Jakarta EE)** ka ek simple **session management** example dikhata hoon jisme:

* User login karega
* Server session banayega
* Session me username store hoga
* Logout karne par session destroy hoga

### 🧾 Tools:

* Servlet (J2EE)
* JSP (view)
* Apache Tomcat (server)
* No database (abhi ke liye static login)

### 1. **login.html** (Login form – static HTML)

```html
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <form action="LoginServlet" method="post">
        Username: <input type="text" name="username" />
        <input type="submit" value="Login" />
    </form>
</body>
</html>

### 2. **LoginServlet.java** (Login logic + session create)

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");

        // Check username (for demo purposes, no password or DB)
        if (username != null && !username.isEmpty()) {
            HttpSession session = request.getSession(); // Create session
            session.setAttribute("username", username); // Store username
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("login.html"); // invalid, retry
        }
    }
}

### 3. **welcome.jsp** (Session check and greet user)

```jsp
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%
    HttpSession session = request.getSession(false);
    String username = (session != null) ? (String) session.getAttribute("username") : null;

    if (username == null) {
        response.sendRedirect("login.html"); // session expired or not logged in
    } else {
%>
    <h2>Welcome, <%= username %>!</h2>
    <a href="LogoutServlet">Logout</a>
<%
    }
%>


### 4. **LogoutServlet.java** (Logout logic)

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // get existing session
        if (session != null) {
            session.invalidate(); // destroy session
        }

        response.sendRedirect("login.html"); // redirect to login
    }
}
### 5. **web.xml** (Deployment descriptor)

```xml
<web-app>
    <servlet>
        <servlet-name>LoginServlet</servlet-name>
        <servlet-class>LoginServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>LoginServlet</servlet-name>
        <url-pattern>/LoginServlet</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>LogoutServlet</servlet-name>
        <servlet-class>LogoutServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>LogoutServlet</servlet-name>
        <url-pattern>/LogoutServlet</url-pattern>
    </servlet-mapping>
</web-app>

* User fills `login.html` → form submit to `LoginServlet`
* Servlet creates **session** and stores username
* JSP (`welcome.jsp`) checks session and greets user
* Logout servlet clears session

