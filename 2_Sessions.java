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
