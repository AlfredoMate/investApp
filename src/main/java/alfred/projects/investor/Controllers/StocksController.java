package alfred.projects.investor.Controllers;

import alfred.projects.investor.Model.Session;
import alfred.projects.investor.Model.Ticker;
import alfred.projects.investor.Model.WrapperTickerResponse;
import alfred.projects.investor.Proxy.FeignClientStockApi;
import alfred.projects.investor.Service.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StocksController {

    private FeignClientStockApi feignStockProxy;
    private SessionService sessionService;


    @Value("${token}")
    private String token;

    public StocksController(FeignClientStockApi feignStockProxy, SessionService sessionService) {
        this.feignStockProxy = feignStockProxy;
        this.sessionService = sessionService;
    }

    @GetMapping("/data")
    public ResponseEntity<List<Ticker>>  getEODStockData (@CookieValue(name = "SESSIONID") String sessionId, @RequestParam String ticker) {
        String dateFrom = "2025-01-01";
        WrapperTickerResponse response = feignStockProxy.obtainEODdata(ticker, token, dateFrom);
        Session currentSession = sessionService.getSession(sessionId);
        String currentUserName = currentSession.getUserName();
        return ResponseEntity.ok().header("UserName", currentUserName).body(response.getData());
    }
}