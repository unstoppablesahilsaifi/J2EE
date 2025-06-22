Awesome! You're doing great. Let's move on to:

---

### **Step 14: JSTL (JSP Standard Tag Library) – Logic Without Java Code**

**JSTL** (JavaServer Pages Standard Tag Library) is a powerful tag library that allows you to write **logic in JSP pages without Java code**, making pages cleaner, more maintainable, and more secure.

---

### **1. How to Use JSTL in JSP**

#### a. Add JSTL to Your Project

If you're using Maven:

```xml
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>jstl</artifactId>
  <version>1.2</version>
</dependency>
```

Or add `jstl.jar` and `standard.jar` to `WEB-INF/lib` for older setups.

#### b. Declare JSTL Taglib in JSP

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

---

### **2. Common JSTL Tag Categories**

| Category         | Prefix | Example Tag         | Description                          |
|------------------|--------|---------------------|--------------------------------------|
| Core             | `c`    | `<c:if>`, `<c:forEach>` | Logic & control flow                |
| Formatting       | `fmt`  | `<fmt:formatDate>`  | Dates, numbers, i18n                 |
| SQL (legacy)     | `sql`  | `<sql:query>`       | Querying databases (NOT recommended now) |
| XML              | `x`    | `<x:parse>`         | Working with XML                     |
| Functions        | `fn`   | `fn:length()`, etc. | String operations                    |

---

### **3. Core JSTL Tags**

#### a. `<c:out>` – Print values (safe HTML output)

```jsp
<c:out value="${user.name}" default="Guest"/>
```

#### b. `<c:if>` – Conditional rendering

```jsp
<c:if test="${user.loggedIn}">
  Welcome, ${user.name}
</c:if>
```

#### c. `<c:choose>`, `<c:when>`, `<c:otherwise>` – If-Else Ladder

```jsp
<c:choose>
  <c:when test="${user.role == 'admin'}">
    Welcome, Admin
  </c:when>
  <c:otherwise>
    Welcome, User
  </c:otherwise>
</c:choose>
```

#### d. `<c:forEach>` – Loop over items (arrays, lists, maps)

```jsp
<c:forEach var="product" items="${productList}">
  ${product.name} - ${product.price}<br/>
</c:forEach>
```

You can also loop by index:

```jsp
<c:forEach var="i" begin="1" end="5">
  Number: ${i}<br/>
</c:forEach>
```

#### e. `<c:set>` – Set a variable

```jsp
<c:set var="name" value="John Doe" scope="session" />
```

#### f. `<c:remove>` – Remove a variable

```jsp
<c:remove var="name" scope="session" />
```

---

### **4. JSTL Functions (optional)**

If you want string operations, include:

```jsp
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
```

Then use:

```jsp
<c:if test="${fn:contains(user.name, 'John')}">
  Hello John!
</c:if>
```

Other useful functions:
- `fn:length()`
- `fn:toLowerCase()`
- `fn:trim()`
- `fn:startsWith()`

---

### **5. JSTL Formatting Tags (optional)**

For date and number formatting:

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>
```

You can also format currency:

```jsp
<fmt:formatNumber value="${product.price}" type="currency" />
```

---

### Summary Table

| Tag             | Purpose                         | Example                            |
|-----------------|----------------------------------|------------------------------------|
| `<c:out>`        | Print data                      | `<c:out value="${user.name}"/>`    |
| `<c:if>`         | Conditional logic               | `<c:if test="${flag}">...</c:if>`  |
| `<c:choose>`     | Multi-condition check           | Like `if-else`                     |
| `<c:forEach>`    | Loop through collections        | `${item.property}`                |
| `<c:set>`        | Set a value to a variable       | `<c:set var="x" value="10"/>`     |
| `<fmt:formatDate>`| Format date values             | `<fmt:formatDate ... />`           |
| `<fn:contains>`  | String operations               | `${fn:contains(x, 'abc')}`         |

---
