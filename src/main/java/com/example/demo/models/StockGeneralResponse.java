package com.example.demo.models;

import org.springframework.http.HttpStatus;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

public class StockGeneralResponse<T> {
    private T response;

    private HttpStatus httpStatus;

    private UnifiedSet<String> userMessages = UnifiedSet.newSet();

    public StockGeneralResponse() {
    }

    public StockGeneralResponse(T response, HttpStatus httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public UnifiedSet<String> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(UnifiedSet<String> userMessages) {
        this.userMessages = userMessages;
    }
}
