package alfred.projects.investor.Service;

import alfred.projects.investor.Model.Session;
import alfred.projects.investor.Persistance.SessionPersistance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseCookie;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {


    @Mock
    private SessionPersistance sessionPersistance;

    @InjectMocks
    private SessionService sessionsService;

    private final String USERNAME = "Joe";
    private final String SESSION_ID = UUID.randomUUID().toString();

    @Test
    @DisplayName("Happy flow test of createSession method")
    public void createSessionHappyFlow () {
        sessionsService.createSession(USERNAME);
        verify(sessionPersistance).addSession(anyString(), any(Session.class));
    }

    @Test
    @DisplayName("Happy flow test of getSession method")
    public void getSessionHappyFlow (){
        sessionsService.getSession(SESSION_ID);
        verify(sessionPersistance).getSession(SESSION_ID);
    }

    @Test
    @DisplayName(
            "Happy flow test of createCookie method. Test that cookie is the one expected"
    )
    public void createCookie() {

        ResponseCookie cookieExptected = ResponseCookie.from("SESSIONID", SESSION_ID)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60*60)
                .sameSite("None")
                .build();

        ResponseCookie cookieObtained =  sessionsService.createCookie(SESSION_ID);
        assertEquals(cookieObtained, cookieExptected);

    }
}
