 JSP Expression Language (EL)**

**Expression Language (EL)** in JSP is a **simpler and cleaner** way to access **Java objects (beans, request attributes, session variables, etc.)** without writing Java code (like `<%= ... %>` or `<% ... %>`).

---

### **1. Basic Syntax of EL**

```jsp
${expression}
```

Example:
```jsp
Hello, ${user.name}
```

This accesses the `name` property of the `user` object stored in any of the scoped attributes (`page`, `request`, `session`, or `application`).

---

### **2. EL Implicit Objects**

EL gives you access to several built-in variables:

| EL Object         | Description                                  |
|-------------------|----------------------------------------------|
| `pageScope`       | Variables in page scope                      |
| `requestScope`    | Variables in request scope                   |
| `sessionScope`    | Variables in session scope                   |
| `applicationScope`| Variables in application (ServletContext)    |
| `param`           | Request parameters (single value)            |
| `paramValues`     | Request parameters (multi-value, array)      |
| `header`          | HTTP headers                                 |
| `headerValues`    | Multiple header values                       |
| `cookie`          | Cookies from the request                     |
| `initParam`       | Context init parameters                      |
| `pageContext`     | JSP `PageContext` object                     |

---

### **3. EL Examples**

#### Accessing Request Parameters

```jsp
Name: ${param.name}
```

#### Accessing Cookies

```jsp
Your session ID: ${cookie.JSESSIONID.value}
```

#### Accessing Scoped Attributes

```jsp
Username (from session): ${sessionScope.username}
```

#### Working with JavaBeans

If a `User` bean is stored in session:

```jsp
<jsp:useBean id="user" class="com.model.User" scope="session" />
<p>Hello, ${user.name}</p>
```

---

### **4. Operators in EL**

| Type         | Operators                 | Example                  |
|--------------|---------------------------|--------------------------|
| Arithmetic   | `+ - * / %`               | `${2 + 3}`               |
| Logical      | `&& (and), || (or), !`    | `${param.age > 18 && param.age < 60}` |
| Relational   | `< > <= >= == !=`         | `${user.age > 18}`       |
| Empty test   | `empty`                   | `${empty param.name}`    |

---

### **5. EL with Collections & Maps**

If you have a `List` or `Map`:

```jsp
<c:forEach var="item" items="${productList}">
  ${item.name} - ${item.price}
</c:forEach>
```

Or accessing map values:

```jsp
${userMap["john"].email}
```

---

### **6. Common Use Case: Clean Form Display**

Instead of:
```jsp
<%= request.getParameter("name") %>
```

Use:
```jsp
${param.name}
```

Cleaner, safer, and easier to read!

---

### **7. EL in JSTL Tags**

EL is often used **inside JSTL** to provide logic or condition:

```jsp
<c:if test="${user.role == 'admin'}">
  <p>Welcome, Admin!</p>
</c:if>
```

---

### Summary

| Feature              | Old JSP Way            | EL Way                 |
|----------------------|------------------------|------------------------|
| Access Request Param | `<%= request.getParameter("name") %>` | `${param.name}` |
| Access Session Attr  | `<%= session.getAttribute("user") %>` | `${sessionScope.user}` |
| Access Bean Prop     | `<%= user.getName() %>` | `${user.name}`         |
| Check if Empty       | Java code              | `${empty param.name}`  |

---

Let me know once you're done with EL, and I’ll take you to **Step 14: JSTL (JSP Standard Tag Library) – Logic Without Java Code**, where you’ll learn how to write real logic (like `if`, `for`, `set`, `out`, etc.) **cleanly with tags**!
