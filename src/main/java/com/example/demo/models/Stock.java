package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "stocks")
public class Stock {
    @Id
    public String symbol;
    public double dayHigh;
    public double dayLow;
    public String identifier;
    public double lastPrice;
    public String lastUpdateTime;
    public double open;
    public double previousClose;
    public double totalTradedValue;
    public long totalTradedVolume;
    public long yearHigh;
    public long yearLow;
    public double change;
    public double pChange;
//    public double perChange365d;
    public double perChange30d;

    public void setSymbol(String symbol) {
        this.symbol = symbol.toUpperCase();
    }
}
