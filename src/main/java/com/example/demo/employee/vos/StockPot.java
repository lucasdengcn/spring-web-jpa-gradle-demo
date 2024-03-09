package com.example.demo.employee.vos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockPot {

    private String symbol;
    private String code;
    private String name;
    private String trade;

    @JsonProperty("pricechange")
    private double priceChange;

    @JsonProperty("changepercent")
    private double changePercent;

    private String buy;
    private String sell;
    private String settlement;
    private String open;
    private String high;
    private String low;
    private long volume;
    private long amount;

    @JsonProperty("ticktime")
    private String tickTime;
    private double per;
    private double pb;
    private double mktcap;
    private double nmc;

    @JsonProperty("turnoverratio")
    private double turnOverRatio;

}
