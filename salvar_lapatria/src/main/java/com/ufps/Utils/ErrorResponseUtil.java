package com.ufps.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseUtil {

    public static ResponseEntity<?> buildErrorResponse(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(true, e.getMessage()));
    }

    public static ResponseEntity<?> buildErrorResponse(String message, Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(true, message + ": " + e.getMessage()));
    }

    // Clase interna para representar la estructura del error
    public static class ErrorResponse {
        private boolean error;
        private String message;

        public ErrorResponse(boolean error, String message) {
            this.error = error;
            this.message = message;
        }

        // Getters y setters
        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
