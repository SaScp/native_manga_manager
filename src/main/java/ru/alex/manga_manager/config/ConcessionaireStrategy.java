package ru.alex.manga_manager.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import ru.alex.manga_manager.config.testingsession.SessionsManager;

public class ConcessionaireStrategy extends ConcurrentSessionControlAuthenticationStrategy {
    /**
     * @param sessionRegistry the session registry which should be updated when the
     *                        authenticated session is changed.
     */


    private SessionsManager sessionsManager;
    private HttpSession session;
    public ConcessionaireStrategy(SessionRegistry sessionRegistry, SessionsManager sessionsManager) {
        super(sessionRegistry);

        super.setMaximumSessions(1);
        this.sessionsManager = sessionsManager;
    }

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        super.onAuthentication(authentication, request, response);


    }
}
