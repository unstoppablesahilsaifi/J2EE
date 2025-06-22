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

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Zaroor! Yahaan do practical Java Servlet examples diye gaye hain jisme **cookies** ka use kiya gaya hai — simple aur real-world context ke sath.

---

### ✅ Example 1: **Set and Read Cookie (Simple Hello App)**

#### 1. **SetCookieServlet.java** – Cookie set karta hai:

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SetCookieServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");

        // Create a cookie
        Cookie userCookie = new Cookie("username", "Rahul");
        userCookie.setMaxAge(60 * 60); // 1 hour
        response.addCookie(userCookie); // Add cookie to response

        PrintWriter out = response.getWriter();
        out.println("Cookie set! <a href='ReadCookieServlet'>Go to ReadCookieServlet</a>");
    }
}
```

#### 2. **ReadCookieServlet.java** – Cookie ko read karta hai:

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ReadCookieServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        boolean found = false;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    out.println("Hello, " + c.getValue());
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            out.println("No username cookie found.");
        }
    }
}
```

#### 3. **web.xml** configuration:

```xml
<web-app>
    <servlet>
        <servlet-name>SetCookie</servlet-name>
        <servlet-class>SetCookieServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>SetCookie</servlet-name>
        <url-pattern>/SetCookieServlet</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>ReadCookie</servlet-name>
        <servlet-class>ReadCookieServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>ReadCookie</servlet-name>
        <url-pattern>/ReadCookieServlet</url-pattern>
    </servlet-mapping>
</web-app>
```

---

### ✅ Example 2: **"Remember Me" Login with Cookie**

#### 1. **login.html**:

```html
<form action="LoginWithCookieServlet" method="post">
    Username: <input name="username" type="text"><br>
    <input type="checkbox" name="remember" value="yes"> Remember Me<br>
    <input type="submit" value="Login">
</form>
```

#### 2. **LoginWithCookieServlet.java**:

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginWithCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String remember = request.getParameter("remember");

        if ("yes".equals(remember)) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
            response.addCookie(cookie);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Welcome, " + username + "!<br>");
        out.println("<a href='CheckRememberedUserServlet'>Go to Home</a>");
    }
}
```

#### 3. **CheckRememberedUserServlet.java**:

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckRememberedUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Cookie[] cookies = request.getCookies();
        String rememberedUser = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    rememberedUser = cookie.getValue();
                    break;
                }
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (rememberedUser != null) {
            out.println("Welcome back, " + rememberedUser + "!");
        } else {
            out.println("No remembered user. Please <a href='login.html'>login</a>.");
        }
    }
}
```

---

### 🔐 Summary:

* **Cookies** store user data in the browser (client-side)
* They can be used for personalization, "remember me" login, etc.
* In contrast, **sessions** are stored on the server and are safer for sensitive data









    Bahut achha sawal!
"**Remember Me**" ka matlab hota hai:

> **User ko baar-baar login karne ki zarurat na ho.**
> Jab bhi user website dobara khole, to wo **automatic login ho jaye**.

### 🔁 Real-life Example:

Aapne kabhi dekha hoga — jab aap **Facebook**, **Gmail**, ya **Flipkart** pe login karte ho, to ek checkbox hota hai:
✔️ **Remember Me** ya **Keep me signed in**

Agar aap ye checkbox select karte ho, to:

* Website aapka **username/email (ya token)** ek **cookie** me store kar leti hai
* Jab aap dobara aate ho, to website automatically us cookie se aapko **pehchan leti hai**
* Aapko **login page** nahi dikhata, seedha dashboard ya homepage dikhata hai

---

### 🧠 Technically kaise kaam karta hai?

1. User checkbox tick karta hai → server ek **cookie create karta hai**, jaise:

   ```
   Cookie: username = rahul; maxAge = 7 days
   ```

2. Jab user browser close karke phir se site kholta hai:

   * Browser ye cookie server ko bhejta hai
   * Server cookie read karke user ko **auto login** kar deta hai

---

### ❗ Important Note:

"Remember Me" me **password store nahi hota** — sirf identification token ya username hota hai.

### 🔐 Security Risk bhi hota hai:

Agar koi shared computer pe "Remember Me" tick kare aur logout na kare, to doosra user bina password dale uska account access kar sakta hai.

Isliye:

* Public computers pe ye option avoid karna chahiye
* Cookies me sensitive info (jaise password) kabhi bhi direct store nahi karni chahiye






**Cookies ke through bhi login kiya ja sakta hai bina session banaye** — lekin **dhyan dene wali baat ye hai ki ye login "temporary aur limited" tarike se hota hai**, aur **sensitive data handle karne ke liye secure nahi hota**.


---

### 🔄 Cookies vs Session – Login ke liye:

| Feature              | Cookie                              | Session                           |
|----------------------|--------------------------------------|------------------------------------|
| Store hoti hai       | Browser (Client-side)               | Server memory (Server-side)        |
| Login kaise yaad rakhta hai | Cookie me username/token store karke | Server session ID store karke     |
| Secure?              | ❌ Kam secure (easily modify ho sakta hai) | ✅ Zyada secure                    |
| Use kab?             | "Remember Me", simple personalization | Secure login, sensitive data       |

---

### ✅ Login via Cookie – Possible hai:

Aap cookie me **username ya token** store karke user ko automatically "logged in" dikh sakte ho.

#### Example Flow:

1. User login form submit karta hai
2. Agar usne "Remember Me" tick kiya:
   - Server ek cookie set karta hai:
     ```java
     Cookie c = new Cookie("username", "rahul");
     c.setMaxAge(7 * 24 * 60 * 60); // 7 din
     response.addCookie(c);
     ```
3. Next time jab user site pe aaye:
   - Servlet ya JSP cookie read kare:
     ```java
     Cookie[] cookies = request.getCookies();
     // if username cookie exists → auto-login logic
     ```

🔐 **BUT** — isme session nahi hai, to:
- Server ko user ka authentication manually verify karna hoga
- Sensitive actions (jaise order, bank, profile change) allow nahi karne chahiye

---

### 🔐 Kyun sirf cookie se login risky hota hai?

1. **Cookie browser me hoti hai** — koi bhi inspect karke dekh sakta hai
2. User cookie modify kar sakta hai (developer tools me)
3. Session me server control me hota hai — user kuch bhi nahi kar sakta

---

### ✅ Best Practice (Real-world sites):

- Login ke baad **session** banaya jata hai (safe)
- "Remember Me" ke liye **secure token** cookie me store ki jati hai (not username directly)
- Token se server dobara session bana deta hai jab user site pe lautta hai

