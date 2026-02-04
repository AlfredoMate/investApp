package alfred.projects.investor.unit.Controller;

import alfred.projects.investor.Controllers.StocksController;
import alfred.projects.investor.Model.Session;
import alfred.projects.investor.Model.Ticker;
import alfred.projects.investor.Model.WrapperTickerResponse;
import alfred.projects.investor.Proxy.FeignClientStockApi;
import alfred.projects.investor.Service.SessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StocksControllerTest {

    @Mock
    private FeignClientStockApi feignStockProxy;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private StocksController stocksController;

    private final String DATE_FROM = "2025-01-01";
    private final String TICKER = "AMZN";
    private final String USERNAME = "Joe";
    private final String SESSION_ID = UUID.randomUUID().toString();

    @Value("${token}")
    private String TOKEN;

    @Test
    @DisplayName("Happy path unit test for getEODStockData method")
    public void getEODStockDataHappyPath () {

        Ticker ticker = new Ticker();
        ticker.setClose(1F);
        List<Ticker> tickers = new ArrayList<>();
        tickers.add(ticker);
        WrapperTickerResponse wrapperTickerResponse = new WrapperTickerResponse();
        wrapperTickerResponse.setData(tickers);

        Session currentSession = Session.from(USERNAME);
        when(sessionService.getSession(SESSION_ID)).thenReturn(currentSession);
        when(feignStockProxy.obtainEODdata(TICKER, TOKEN, DATE_FROM)).thenReturn(wrapperTickerResponse);

        stocksController.getEODStockData(SESSION_ID, TICKER);
        verify(feignStockProxy).obtainEODdata(TICKER, TOKEN, DATE_FROM);
        verify(sessionService).getSession(SESSION_ID);
    }
}
