package com.example.demo.model;

/**
 * Represents the details of a mutual fund.
 */
public class MutualFund {
    private String ticker;
    private String name;

    // Constructors
    public MutualFund() {}

    public MutualFund(String ticker, String name) {
        this.ticker = ticker;
        this.name = name;
    }

    // Getters and Setters
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
