package com.example.demo.services;

import com.example.demo.models.Stock;
import com.example.demo.models.StockGeneralResponse;

public interface StockService {

    StockGeneralResponse getAllStocks();
    void populateStockDatabase();
    StockGeneralResponse getStockBySymbol(String symbol);
    StockGeneralResponse createStock(Stock stock);
    StockGeneralResponse updateStock(String symbol, Double lastPrice);
    StockGeneralResponse deleteStock(String symbol);
}
