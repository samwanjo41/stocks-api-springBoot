package com.example.demo.models;

import org.springframework.http.HttpStatus;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

public class StockGeneralResponse<T> {
    private T response;

    private HttpStatus httpStatus;

    private UnifiedSet<String> userMessages = UnifiedSet.newSet();
}
