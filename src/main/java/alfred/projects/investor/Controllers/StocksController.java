package alfred.projects.investor.Controllers;

import alfred.projects.investor.Model.Session;
import alfred.projects.investor.Model.Ticker;
import alfred.projects.investor.Model.WrapperTickerResponse;
import alfred.projects.investor.Persistance.SessionPersistance;
import alfred.projects.investor.Proxy.FeignClientStockApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StocksController {

    private FeignClientStockApi feignStockProxy;
    private SessionPersistance sessionPersistance;

    @Value("${token}")
    private String token;

    public StocksController(FeignClientStockApi feignStockProxy, SessionPersistance sessionPersistance) {
        this.feignStockProxy = feignStockProxy;
        this.sessionPersistance = sessionPersistance;
    }

    @GetMapping("/data")
    public ResponseEntity<List<Ticker>>  getEODStockData (@CookieValue(name = "SESSIONID") String sessionId, @RequestParam String ticker) {
        String dateFrom = "2025-01-01";
        WrapperTickerResponse response = feignStockProxy.obtainEODdata(ticker, token, dateFrom);
        Session currentSession = sessionPersistance.getSession(sessionId);
        String currentUserName = currentSession.getUserName();
        return ResponseEntity.ok().header("UserName", currentUserName).body(response.getData());
    }
}