Excellent progress! Now let's move on to:

---

### **Step 11: Listeners in Servlets – Monitoring Application, Session, and Request Events**

**Listeners** in Java EE (Servlet API) are used to **monitor and react** to important **lifecycle events** like:

* When your application starts or stops
* When a session is created or destroyed
* When a request is received or completed
* When an attribute is added, removed, or changed in any of the above scopes

---

### **1. Types of Servlet Listeners**

| Listener Interface                | Listens To                           |
| --------------------------------- | ------------------------------------ |
| `ServletContextListener`          | App startup and shutdown             |
| `HttpSessionListener`             | Session creation and destruction     |
| `ServletRequestListener`          | Request lifecycle                    |
| `ServletContextAttributeListener` | Context attribute add/remove/replace |
| `HttpSessionAttributeListener`    | Session attribute add/remove/replace |
| `ServletRequestAttributeListener` | Request attribute add/remove/replace |

---

### **2. Application Lifecycle Listener (`ServletContextListener`)**

```java
@WebListener
public class AppStartupShutdownListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("App Started");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("App Stopped");
    }
}
```

* `contextInitialized()` runs when the application is deployed.
* `contextDestroyed()` runs on undeploy/server shutdown.

---

### **3. Session Lifecycle Listener (`HttpSessionListener`)**

```java
@WebListener
public class SessionMonitor implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session Created: " + se.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session Destroyed: " + se.getSession().getId());
    }
}
```

Use case: session counting, timeout tracking, logout logging.

---

### **4. Request Lifecycle Listener (`ServletRequestListener`)**

```java
@WebListener
public class RequestLogger implements ServletRequestListener {
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request Started: " + sre.getServletRequest());
    }

    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request Ended");
    }
}
```

Use case: logging request start/end time, IP tracking, etc.

---

### **5. Attribute Listeners Example (Session)**

```java
@WebListener
public class SessionAttributeLogger implements HttpSessionAttributeListener {
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Added to session: " + event.getName() + "=" + event.getValue());
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Removed from session: " + event.getName());
    }

    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Replaced in session: " + event.getName());
    }
}
```

---

### **How to Use Listeners**

* Simply annotate your listener class with `@WebListener`
* Or register it in `web.xml`:

```xml
<listener>
    <listener-class>com.example.AppStartupShutdownListener</listener-class>
</listener>
```

---

### Summary of Common Use Cases

| Listener Type                  | Common Use Case                         |
| ------------------------------ | --------------------------------------- |
| `ServletContextListener`       | Load config at startup, clean resources |
| `HttpSessionListener`          | Track active users, session timeouts    |
| `ServletRequestListener`       | Log every request                       |
| `HttpSessionAttributeListener` | Monitor session data changes            |

---

Let me know once you’re ready to move to **Step 12: JSP Basics – Syntax, Directives, and Scriptlets**, where we start building the **View Layer** of your application!
