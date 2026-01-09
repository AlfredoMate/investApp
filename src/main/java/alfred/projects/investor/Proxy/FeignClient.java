package alfred.projects.investor.Proxy;

import alfred.projects.investor.Model.WrapperTickerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@org.springframework.cloud.openfeign.FeignClient(name = "stockApi",
           url = "${stockData.baseUrl}"
)
public interface FeignClient {

    @GetMapping()
    WrapperTickerResponse obtainEODdata (@RequestParam (name = "symbols") String ticker,
                                               @RequestParam (name = "api_token") String token,
                                               @RequestParam (name = "date_from") String dateFrom);
}
