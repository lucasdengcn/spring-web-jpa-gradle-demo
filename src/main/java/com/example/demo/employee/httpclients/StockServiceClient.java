package com.example.demo.employee.httpclients;

import com.example.demo.employee.records.StockPot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface StockServiceClient {

    @GetExchange("/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?num=80&sort=symbol&asc=1&node=hs_a&symbol=&_s_r_a=page")
    List<StockPot> getQuotes(@RequestParam int page);

    @GetExchange("/books/{id}")
    StockPot getBook(@PathVariable long id);

    @PostExchange("/books")
    StockPot saveBook(@RequestBody StockPot book);

    @DeleteExchange("/books/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable long id);

}
