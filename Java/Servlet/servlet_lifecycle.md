The lifecycle of a Java Servlet is managed by the Servlet Container (e.g., Apache Tomcat). A Servlet goes through various stages during its lifecycle, such as initialization, handling requests, and destruction. Here's an overview of the servlet lifecycle stages and corresponding methods:

1. **Initialization**: Servlet initialization is performed when the servlet is first loaded or when the container is started. The `init()` method is called during this stage.

```java
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
    public void init() throws ServletException {
        // Initialization code (e.g., database connections)
    }
}
```

2. **Request Handling**: During this stage, the servlet handles incoming HTTP requests. The `service()` method is responsible for processing requests.

```java
public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Request handling code (e.g., reading parameters, generating responses)
}
```

3. **Request Processing**: The `service()` method delegates the actual request handling to specific methods based on the HTTP method (e.g., `doGet()`, `doPost()`).

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Handle GET requests
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Handle POST requests
}
```

4. **Destruction**: The servlet container calls the `destroy()` method when the servlet is being unloaded or when the container is shutting down.

```java
public void destroy() {
    // Cleanup code (e.g., closing resources)
}
```

The servlet container manages the lifecycle of the servlet instances and ensures that only one instance of a servlet class is created. For each request, a new thread is created, and the `service()` method of the servlet instance is called to handle the request. Once the request is processed, the thread can be reused for other requests.

This is a simplified explanation of the servlet lifecycle. In practice, you can override the `init()`, `service()`, `doGet()`, `doPost()`, and `destroy()` methods to add your own code for initialization, request handling, and cleanup. Additionally, there are other methods and events, such as `contextInitialized()` and `contextDestroyed()` for the servlet context lifecycle.

Remember that the actual servlet container (e.g., Tomcat) handles many details of the servlet lifecycle, so you don't need to manage these stages explicitly in your servlet code. Your job is to implement the specific functionality you want to provide in the `doGet()` and `doPost()` methods, as well as any other custom methods you define.