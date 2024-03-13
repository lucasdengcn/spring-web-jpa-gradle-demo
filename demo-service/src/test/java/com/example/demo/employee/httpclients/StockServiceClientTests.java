package com.example.demo.employee.httpclients;

import com.example.demo.employee.models.StockPot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StockServiceClientTests {

    @Autowired
    StockServiceClient stockServiceClient;

    @Test
    public void test_get_stock_quotes(){
        List<StockPot> quotes = stockServiceClient.getQuotes(1);
        Assertions.assertEquals(80, quotes.size());
        System.out.println(quotes.get(0));
        //
        quotes = stockServiceClient.getQuotes(2);
        Assertions.assertEquals(80, quotes.size());
        //
    }

}
