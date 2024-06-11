package com.seaweed.hm.comm.http.session;

import jakarta.servlet.http.HttpSession;

public class SessionAuthenticationContext {
    private static final String SESSION_LOGIN_NAME = "LOGIN_ID";
    private static final String SESSION_LOGIN_TYPE = "LOGIN_TYPE";

    public static long getLoginId(HttpSession session){
        return (long) session.getAttribute(SESSION_LOGIN_NAME);
    }

    public static boolean isAuthenticated(HttpSession session){
        return session.getAttribute(SESSION_LOGIN_NAME) != null && (long) session.getAttribute(SESSION_LOGIN_NAME) > 0;
    }

    public static void authenticate(HttpSession session, long userId) throws Exception {
        SimpleSessionContext.addLoginSession(userId,session);
        session.setAttribute(SESSION_LOGIN_NAME, userId);
    }

    public static void expireAuthentication(HttpSession session){
        long loginId = getLoginId(session);
        SimpleSessionContext.removeLoginSession(loginId,session);
        session.removeAttribute(SESSION_LOGIN_NAME);
    }
}
