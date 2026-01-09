package alfred.projects.investor.Controllers;

import alfred.projects.investor.Model.Ticker;
import alfred.projects.investor.Model.WrapperTickerResponse;
import alfred.projects.investor.Proxy.FeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StocksController {

    private FeignClient feignStockProxy;

    @Value("${token}")
    private String token;

    public StocksController(FeignClient feignStockProxy) {
        this.feignStockProxy = feignStockProxy;
    }

    @GetMapping("/data")
    public List<Ticker> getEODStockData (@RequestParam String ticker) {
        String dateFrom = "2025-01-01";
        WrapperTickerResponse response = feignStockProxy.obtainEODdata(ticker, token, dateFrom);
        return response.getData();
    }
}