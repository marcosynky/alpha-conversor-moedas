package aula_hadlers.handler;

import java.time.LocalDateTime;

public class ResponseError {

    private LocalDateTime timestamp = LocalDateTime.now(); // Usando LocalDateTime
    private String status = "error";
    private int statusCode = 400;
    private String message; // Renomeado de "error" para "message" para maior clareza
    private String error;   // Detalhamento do erro (ex: "Bad Request", "Internal Server Error")



    // Getters e Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
