Filters in Servlets – Pre/Post Processing of Requests**

A **Filter** is a special Java component that allows you to intercept requests **before they reach a servlet** and/or **after the servlet sends a response**. Filters are part of the **Servlet API** and are useful for cross-cutting concerns like:

* Authentication
* Logging
* Compression
* Input validation
* Setting headers

---

### **1. Key Methods in `Filter` Interface**

```java
public interface Filter {
    void init(FilterConfig config) throws ServletException;
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException;
    void destroy();
}
```

* `init()` – runs once when the filter is created
* `doFilter()` – runs **for every request** matched by the filter
* `destroy()` – runs when the server shuts down or filter is removed

---

### **2. Basic Filter Example**

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter("/dashboard")  // apply to dashboard servlet
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            // User is logged in, continue to the servlet
            chain.doFilter(request, response);
        } else {
            // Not logged in, redirect to login page
            res.sendRedirect("form.jsp");
        }
    }

    public void init(FilterConfig config) {}
    public void destroy() {}
}
```

---

### **3. Filter Chain**

* The `FilterChain` allows the request to pass to the **next filter** or to the **servlet** if no more filters exist.
* If you don’t call `chain.doFilter()`, the request will be **blocked**.

---

### **4. Filter Mapping Options**

#### a. With `@WebFilter` annotation:

```java
@WebFilter("/secure/*")   // applies to all URLs under /secure
```

#### b. In `web.xml`:

```xml
<filter>
    <filter-name>LogFilter</filter-name>
    <filter-class>com.example.LogFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>LogFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

---

### **5. Multiple Filters**

If multiple filters are configured for the same resource, they are executed in the order defined in `web.xml`. Each one must call `chain.doFilter()` to pass control.

---

### Use Cases Summary:

| Use Case        | Example Filter Behavior                               |
| --------------- | ----------------------------------------------------- |
| Logging         | Log every incoming request URI                        |
| Authentication  | Check if the user is logged in before allowing access |
| Compression     | GZip response data                                    |
| Header settings | Add custom security headers to every response         |
