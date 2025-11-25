package com.dugas.covenantclassroom.model;

public class Request {
    
    public record loginRequest(String username, String password) { 
        @Override
        public String toString() {
            return "loginRequest{username='" + username + "', password='[PROTECTED]'}";
        }
    }
}
