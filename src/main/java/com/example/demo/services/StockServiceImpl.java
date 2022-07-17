package com.example.demo.services;

import com.example.demo.exceptions.StocksResponseException;
import com.example.demo.models.Stock;
import com.example.demo.models.StockGeneralResponse;
import com.example.demo.repositories.StockRepository;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;
import java.util.Objects;

@Service
@Slf4j
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    private final RestTemplate stocksApiRestTemplate;

    private final HttpHeaders httpHeaders;
    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    public StockServiceImpl(@Qualifier("stocksApiRestTemplate") RestTemplate stocksApiRestTemplate, StockRepository stockRepository) {
        this.stockRepository = stockRepository;
        this.stocksApiRestTemplate = stocksApiRestTemplate;
        httpHeaders = new HttpHeaders();
        httpHeaders.add("X-RapidAPI-Key", "1d825bbad2msheef5657b25e2c4dp17e50bjsnab372b45f421");
        httpHeaders.add("X-RapidAPI-Host", "latest-stock-price.p.rapidapi.com");
    }

    @Override
    @Scheduled(fixedRate = 60000) //// annotation is used to trigger this request once a minute, time in seconds
    public void populateStockDatabase() throws StocksResponseException {
        logger.info("==================inside populateStockDatabase========================");

        // the path that needs to be called once the rest template intializes a connection with the API
        String path = "/price";

        // a query parameter was required to be passed to the path above, and we utilize a query builder object where "Indices=NIFTY%20NEXT" is the encoded value
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(path)
                .queryParam("Indices", "NIFTY NEXT 50");

        ResponseEntity<FastList<Stock>> response = stocksApiRestTemplate.exchange(builder.build().toString(),
                HttpMethod.GET, new HttpEntity<>(httpHeaders), new ParameterizedTypeReference<FastList<Stock>>() {
        });

        // if the response status is a 200 or OK...
        if(response.getStatusCode() == HttpStatus.OK){
            // we ensure that the response is not null, and then save the body of the response object into our database via the stock respository's default given method, save()
            Objects.requireNonNull(response.getBody()).forEach(stockRepository::save);
        }else {
            // if an issue occurs, we throw an exception with a specific error message, in String format
            throw new StocksResponseException("Error: Issue retrieving stocks.");
        }

    }

    public StockGeneralResponse getAllStocks(){
        return new StockGeneralResponse(stockRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public StockGeneralResponse getStockBySymbol(String symbol) {
        Stock stock = stockRepository.findBySymbol(symbol.toUpperCase());
        if(stock != null){
            return  new StockGeneralResponse(stock, HttpStatus.OK);
        }else{
            return new StockGeneralResponse(symbol, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public StockGeneralResponse createStock(Stock stock) {
        stockRepository.save(stock);
        return new StockGeneralResponse(stock, HttpStatus.CREATED);
    }

    @Override
    public StockGeneralResponse updateStock(String symbol, Double lastPrice) throws StocksResponseException {
        Stock currStock = stockRepository.findBySymbol(symbol.toUpperCase());

        if(currStock != null){
            currStock.setLastPrice(lastPrice);
            stockRepository.save(currStock);
            return new StockGeneralResponse(currStock, HttpStatus.OK);
        }else{
            throw new StocksResponseException("Stock with entered symbol does not exist");
        }
    }

    @Override
    public StockGeneralResponse deleteStock(String symbol) {
        stockRepository.deleteDistinctBySymbol(symbol);
        return new StockGeneralResponse(symbol, HttpStatus.NO_CONTENT);
    }


}
