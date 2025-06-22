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

