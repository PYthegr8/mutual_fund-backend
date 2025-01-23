package com.example.demo.controller;

import com.example.demo.model.MutualFund;
import com.example.demo.service.MutualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mutual-funds")
public class MutualFundController {

    private final MutualFundService mutualFundService;

    @Autowired
    public MutualFundController(MutualFundService mutualFundService) {
        this.mutualFundService = mutualFundService;
    }

    @GetMapping
    public List<MutualFund> getAllMutualFunds() {
        return mutualFundService.getAllMutualFunds();
    }

//    @GetMapping("/calculate")
//    public double calculateFutureValue(
//            @RequestParam String ticker,
//            @RequestParam double amount,
//            @RequestParam int time) {
//
//        double beta = mutualFundService.fetchBeta(ticker);
//        double marketReturnRate = mutualFundService.fetchMarketReturnRate();
//        double riskFreeRate = 0.03;
//
//       double adjustedRate = riskFreeRate + Math.abs(beta) * (marketReturnRate - riskFreeRate);
//
//        return mutualFundService.calculateFutureValue(amount, adjustedRate, time);
//    }


    @GetMapping("/calculate")
    public Map<String, Double> calculateFutureValue(
            @RequestParam String ticker,
            @RequestParam double amount,
            @RequestParam int time) {

        double beta = mutualFundService.fetchBeta(ticker);
        double marketReturnRate = mutualFundService.fetchMarketReturnRate();
        double riskFreeRate = 0.03;

        double adjustedRate = riskFreeRate + beta * (marketReturnRate - riskFreeRate);
        double futureValue = mutualFundService.calculateFutureValue(amount, adjustedRate, time);

        // Calculate annualized return
        double annualizedReturn = Math.pow(futureValue / amount, 1.0 / time) - 1;

        // Return both future value and annualized return
        Map<String, Double> result = new HashMap<>();
        result.put("futureValue", futureValue);
        result.put("annualizedReturn", annualizedReturn * 100); // Convert to percentage
        return result;
    }

}
