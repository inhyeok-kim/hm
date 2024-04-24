package com.seaweed.hm.comm.component.http.session;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        if(SessionAuthenticationContext.isAuthenticated(session)){
            SimpleSessionContext.removeLoginSession(SessionAuthenticationContext.getLoginId(session),session);
        }
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
