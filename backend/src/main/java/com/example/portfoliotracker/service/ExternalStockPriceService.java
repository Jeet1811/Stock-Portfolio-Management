// package com.example.portfoliotracker.service;

// import org.springframework.web.client.RestTemplate;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @Service
// public class ExternalStockPriceService {

//     @Value("${marketstack.apikey}")
//     private String apiKey;

//     private final RestTemplate restTemplate;
//     private final String baseUrl = "http://api.marketstack.com/v1/";

//     public ExternalStockPriceService(RestTemplate restTemplate) {
//         this.restTemplate = restTemplate;
//     }

// //    public Map<String, Object> getStockQuote(String symbol) {
// //        String url = baseUrl + "eod?access_key=" + apiKey + "&symbols=" + symbol;
// //        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
// //        return response;
// //    }

//     public Map<String, Object> getStockQuote(String symbol) {
//         // Hardcoded response for testing purposes
//         Map<String, Object> response = new HashMap<>();

//         // Setting up the pagination part
//         Map<String, Object> pagination = new HashMap<>();
//         pagination.put("limit", 100);
//         pagination.put("offset", 0);
//         pagination.put("count", 100);
//         pagination.put("total", 9944);
//         response.put("pagination", pagination);

//         // Setting up the data part
//         List<Map<String, Object>> dataList = new ArrayList<>();
//         Map<String, Object> dataEntry = new HashMap<>();
//         dataEntry.put("open", 129.8);
//         dataEntry.put("high", 133.04);
//         dataEntry.put("low", 129.47);
//         dataEntry.put("close", 132.995);
//         dataEntry.put("volume", 106686703.0);
//         dataEntry.put("adj_high", 133.04);
//         dataEntry.put("adj_low", 129.47);
//         dataEntry.put("adj_close", 132.995);
//         dataEntry.put("adj_open", 129.8);
//         dataEntry.put("adj_volume", 106686703.0);
//         dataEntry.put("split_factor", 1.0);
//         dataEntry.put("dividend", 0.0);
//         dataEntry.put("symbol", "AAPL");
//         dataEntry.put("exchange", "XNAS");
//         dataEntry.put("date", "2021-04-09T00:00:00+0000");
//         dataList.add(dataEntry);


//         response.put("data", dataList);

//         return response;
//     }
// }
// ------------------------------------------------------------------------------------------

// package com.example.portfoliotracker.service;

// import org.springframework.web.client.RestTemplate;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import java.math.BigDecimal;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @Service
// public class ExternalStockPriceService {

//     @Value("${marketstack.apikey}")
//     private String apiKey;

//     private final RestTemplate restTemplate;
//     private final String baseUrl = "http://api.marketstack.com/v1/";

//     public ExternalStockPriceService(RestTemplate restTemplate) {
//         this.restTemplate = restTemplate;
//     }

//     public Map<String, Object> getStockQuote(String symbol) {
//         try {
//             String url = baseUrl + "eod?access_key=" + apiKey + "&symbols=" + symbol;
//             Map<String, Object> response = restTemplate.getForObject(url, Map.class);
//             return response;
//         } catch (Exception e) {
//             throw new RuntimeException("Failed to retrieve stock quote for symbol: " + symbol, e);
//         }
//     }

//     public BigDecimal getCurrentMarketPrice(String symbol) {
//         try {
//             Map<String, Object> response = getStockQuote(symbol);
//             if (response != null && response.containsKey("data")) {
//                 List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");
//                 if (!data.isEmpty()) {
//                     Map<String, Object> latestData = data.get(0);
//                     Object closeValue = latestData.get("close");
//                     if (closeValue instanceof Number) {
//                         return new BigDecimal(((Number) closeValue).doubleValue());
//                     } else {
//                         throw new RuntimeException("Invalid close value for symbol: " + symbol);
//                     }
//                 }
//             }
//             throw new RuntimeException("Price information not available for: " + symbol);
//         } catch (Exception e) {
//             throw new RuntimeException("Failed to retrieve current market price for symbol: " + symbol, e);
//         }
//     }
// }

// -------------------------------------------------------------------------------------------------

package com.example.portfoliotracker.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExternalStockPriceService {

    @Value("${marketstack.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://api.marketstack.com/v1/";

    public ExternalStockPriceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getStockQuote(String symbol) {
        String url = baseUrl + "eod?access_key=" + apiKey + "&symbols=" + symbol;
        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stock quote", e);
        }
    }

    public BigDecimal getCurrentMarketPrice(String symbol) {
        try {
            Map<String, Object> response = getStockQuote(symbol);
            if (response != null && response.containsKey("data")) {
                List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");
                if (!data.isEmpty()) {
                    Map<String, Object> latestData = data.get(0);
                    return new BigDecimal(latestData.get("close").toString());
                }
            }
            throw new RuntimeException("Price information not available for: " + symbol);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch current market price", e);
        }
    }
}