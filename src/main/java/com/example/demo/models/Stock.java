package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {
    public double dayHigh;
    public double dayLow;
    public String identifier;
    public double lastPrice;
    public String lastUpdateTime;
    public double open;
    public double previousClose;
    public String symbol;
    public double totalTradedValue;
    public long totalTradedVolume;
    public long yearHigh;
    public long yearLow;
    public double change;
    public double pChange;
    public double perChange365d;
    public double perChange30d;
}
