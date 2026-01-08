package alfred.projects.investor.Proxy;

import alfred.projects.investor.Model.Ticker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "stockApi",
           url = "${stockData.baseUrl}"
)
public interface FeignStockProxy {

    @GetMapping()
    List<Ticker> obtainEODdata (@RequestParam (name = "symbols") String ticker,
                                @RequestParam (name = "api_token") String token,
                                @RequestParam (name = "date_from") String dateFrom);
}
