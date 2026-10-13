package file.service;

import com.sun.net.httpserver.HttpExchange;

public interface FileService {
    public void save(HttpExchange exchange);

    //public void read(HttpExchange exchange);
}
