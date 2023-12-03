package server;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;


public class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Request Received";
        String method = exchange.getRequestMethod();
        if(method.equals("GET")) {
            response = handleGet(exchange);
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = exchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
    }
    private String handleGet(HttpExchange exchange){
        String response  = "Invalid Get Request";
        URI uri = exchange.getRequestURI();
        String query = uri.getRawQuery();
        if(query != null) {
            String name = query.substring(query.indexOf("=") +1);
             StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder
       .append("<html>")
       .append("<body>")
       .append("<h1>")
       .append("Hello ")
       .append(name)
       .append("</h1>")
       .append("</body>")
       .append("</html>");


     // encode HTML content
        response = htmlBuilder.toString();
        }
       return response;
    }
}