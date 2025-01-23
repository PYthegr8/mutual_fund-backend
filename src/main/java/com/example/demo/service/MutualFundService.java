package com.example.demo.service;

import com.example.demo.model.MutualFund;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MutualFundService {

    private static final Logger logger = LoggerFactory.getLogger(MutualFundService.class);
    private final RestTemplate restTemplate;

    public MutualFundService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Fetches the beta value for a given mutual fund.
     * @param ticker Mutual fund ticker.
     * @return Beta value.
     */
    public double fetchBeta(String ticker) {
        String apiUrl = "https://api.newtonanalytics.com/stock-beta/?ticker={ticker}&index=^GSPC&interval=1mo&observations=12";

        // Make the API call and parse the response
        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class, ticker);

        if (response != null && response.containsKey("data")) {
            double beta = Double.parseDouble(response.get("data").toString());
            logger.info("Fetched Beta for {}: {}", ticker, beta);
            return beta;
        }

        logger.warn("Beta API failed. Defaulting beta to 0.0");
        return 0.0;
    }

    /**
     * Fetches the market return rate based on S&P 500 data.
     * @return Market return rate.
     */
    public double fetchMarketReturnRate() {
        String apiUrl = "https://api.stlouisfed.org/fred/series/observations?series_id=SP500&api_key=d26079fc190512773ac705629a92f8ea&file_type=json";

        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);

        if (response != null && response.containsKey("observations")) {
            List<Map<String, String>> observations = (List<Map<String, String>>) response.get("observations");

            // Filter and parse valid values
            List<Double> values = new ArrayList<>();
            for (Map<String, String> obs : observations) {
                String value = obs.get("value");
                if (!value.equals(".")) {
                    values.add(Double.parseDouble(value));
                }
            }

            if (values.size() >= 2) {
                double firstValue = values.get(0);
                double lastValue = values.get(values.size() - 1);

                double marketReturnRate = (lastValue - firstValue) / firstValue;
                logger.info("Market Return Rate: {}", marketReturnRate);
                return marketReturnRate;
            }
        }

        logger.warn("Market Return Rate API failed. Defaulting to 0.08");
        return 0.08; // Default value
    }

    /**
     * Calculates the future value of an investment.
     * @param amount Initial investment amount.
     * @param rate Rate of return.
     * @param time Investment duration in years.
     * @return Future value.
     */
    public double calculateFutureValue(double amount, double rate, int time) {
        logger.info("Calculating Future Value with Amount: {}, Rate: {}, Time: {}", amount, rate, time);
        double futureValue = amount * Math.pow(1 + rate, time);
        logger.info("Future Value: {}", futureValue);
        return futureValue;
    }

    /**
     * Returns a list of hardcoded mutual funds.
     * @return List of mutual funds.
     */
    public List<MutualFund> getAllMutualFunds() {
        return Arrays.asList(
                new MutualFund("VFIAX", "Vanguard 500 Index Fund"),
                new MutualFund("SPY", "SPDR S&P 500 ETF Trust")
        );
    }
}
