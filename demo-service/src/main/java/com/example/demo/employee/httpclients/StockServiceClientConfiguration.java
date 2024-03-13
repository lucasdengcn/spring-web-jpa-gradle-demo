package com.example.demo.employee.httpclients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class StockServiceClientConfiguration {

    @Bean
    public StockServiceClient stockServiceClient(){
        RestClient restClient = RestClient.builder().baseUrl("http://vip.stock.finance.sina.com.cn").build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        StockServiceClient service = factory.createClient(StockServiceClient.class);
        return service;
    }

}
