package alfred.projects.investor.Controller;

import alfred.projects.investor.Controllers.LoginController;
import alfred.projects.investor.Service.LoginService;
import alfred.projects.investor.Service.SessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseCookie;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private LoginController loginController;

    private final String USERNAME = "Joe";
    private final String PASSWORD = "password";
    private final String SESSION_ID = UUID.randomUUID().toString();
    private final ResponseCookie responseCookie = ResponseCookie.from("SESSIONID", SESSION_ID).build();

    @Test
    @DisplayName("Happy path unit test for validateLogin method")
    public void validateLoginHappyPath () {

        when(sessionService.createSession(USERNAME)).thenReturn(SESSION_ID);
        when(sessionService.createCookie(SESSION_ID)).thenReturn(responseCookie);
        loginController.validateLogin(USERNAME, PASSWORD);
        verify(loginService).login(USERNAME, PASSWORD);
        verify(sessionService).createSession(USERNAME);
        verify(sessionService).createCookie(any());
    }

}
