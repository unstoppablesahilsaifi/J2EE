// Scripting Tags
Used to embed Java code directly into the JSP. Their use is discouraged in modern JSP in favor of JSTL and EL (Expression Language).
<%! ... %> – Declaration tag for defining methods or variables.
<%= ... %> – Expression tag, used to output the result of a Java expression.
<% ... %> – Scriptlet tag for writing Java code blocks.

 1. `<%! ... %>` – **Declaration Tag**

Iska use hum JSP page me **method ya variable declare** karne ke liye karte hain. Ye code **sirf ek baar** execute hota hai jab JSP page load hota hai.
<%@ page language="java" %>
<%! 
    int counter = 0; // Variable declaration
    public int getSquare(int x) { // Method declaration
        return x * x;
    }
%>
* Yahan `counter` ek variable hai jo puri page me use ho sakta hai.
* `getSquare()` ek method hai jo kisi bhi number ka square return karta hai.

2. `<%= ... %>` – **Expression Tag** ( (used to print on browser))

Ye tag ka use hum kisi **Java expression ka result directly page pe print** karne ke liye karte hain.

<%= 5 + 3 %>
Output:
8
Ek aur example:

```jsp
<%= getSquare(4) %>

Output:

16

Yahan `getSquare(4)` method upar se aaya hai (jo humne `<%! %>` me banaya tha).

3. `<% ... %>` – **Scriptlet Tag**

Ye tag Java code block likhne ke liye hota hai, jisme aap **logic implement kar sakte ho** jaise loops, conditions, etc.

```jsp
<%
    int a = 10;
    int b = 20;
    int sum = a + b;
%>
Sum is: <%= sum %>
```

Output:

```
Sum is: 30


* `<% %>` me humne Java code likha
* `<%= sum %>` se humne result print kiya

### Full Simple JSP Example:

```jsp
<%@ page language="java" %>

<%! 
    int counter = 0;
    public int multiply(int a, int b) {
        return a * b;
    }
%>

<%
    int x = 5;
    int y = 4;
    int result = multiply(x, y);
    counter++;
%>

<h2>Result of multiplication is: <%= result %></h2>
<p>Page accessed <%= counter %> time(s).</p>
```

### Output:

```
Result of multiplication is: 20
Page accessed 1 time(s).


********************************************************************************************************************************************************************************************************************
********************************************************************************************************************************************************************************************************************
********************************************************************************************************************************************************************************************************************

 
 1. Directive Tags
These provide global information about the JSP page and affect the overall structure.

<%@ page %> – Defines page-dependent attributes like language, contentType, errorPage, etc.
Example: <%@ page language="java" contentType="text/html" %>

<%@ include %> – Includes a static file at page translation time.
Example: <%@ include file="header.jsp" %>

<%@ taglib %> – Declares a tag library, typically used with JSTL or custom tags.
Example: <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


Bilkul! Aapko JSP ke ye 3 **Directive Tags** easy tarike se samjhaata hoon, jisme real-life example aur use-case ho.

---

## ✅ 1. `<%@ page %>` – **Page Directive**

### Kya karta hai?

Ye JSP page ka configuration set karta hai. Jaise:

* Kaunsi programming language use karni hai (`language="java"`)
* Output kis type ka hoga (`contentType="text/html"`)
* Agar error aaye to kis page pe bhejna hai (`errorPage`)

### Example:

```jsp
<%@ page language="java" contentType="text/html" %>
<html>
  <body>
    <h1>Hello from JSP Page!</h1>
  </body>
</html>
```

### Real-life Use:

* Tum JSP page ka output HTML, XML, ya JSON bana sakte ho.
* Agar kisi file me syntax error ho to `errorPage` specify kar sakte ho.

```jsp
<%@ page errorPage="errorPage.jsp" %>
```

---

## ✅ 2. `<%@ include %>` – **Include Directive**

### Kya karta hai?

Iska use kisi **static JSP file ko is page me include karne ke liye** hota hai — jaise header, footer, navbar.

### Example:

```jsp
<%@ include file="header.jsp" %>
<h1>Welcome to my website!</h1>
<%@ include file="footer.jsp" %>
```

### Real-life Use:

Socho tumhare 10 pages hain aur sab me same header/footer hai — to har page me likhne ki jagah tum `include` se reuse kar sakte ho.

📝 Note: Ye **compile time** pe include hota hai (matlab page translate hone se pehle hi code add ho jaata hai).

---

## ✅ 3. `<%@ taglib %>` – **Tag Library Directive**

### Kya karta hai?

JSP me **JSTL ya custom tag libraries** use karne ke liye `taglib` declare karte hain. JSTL = JavaServer Pages Standard Tag Library.

### Example:

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${user.loggedIn}">
    <h2>Welcome, ${user.name}!</h2>
</c:if>
```

* `uri` batata hai ki kaunsi library use ho rahi hai.
* `prefix="c"` ka matlab hai tum `<c:...>` wale tags use karoge.


## 🔄 Summary (One Line Each):

* **`<%@ page %>`** – Page ka config set karta hai (language, error page, etc.)
* **`<%@ include %>`** – Static file ko compile time pe page me jodta hai.
* **`<%@ taglib %>`** – JSTL/custom tags ko enable karta hai JSP page me.
