package com.seaweed.hm.comm.http.session;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class SimpleSessionContext {

    private static int MAX_COUNT;;

    @Value("${simple.auth.max-count}")
    public void setMaxCount(int count){
        MAX_COUNT = count;
    }

    private static HashMap<Long, List<HttpSession>> LOGIN_SESSION = new HashMap<>();

    public static void addLoginSession(long loginId, HttpSession session) throws Exception {
        if(LOGIN_SESSION.containsKey(loginId)){
            if(LOGIN_SESSION.get(loginId).size() >= MAX_COUNT && MAX_COUNT > 0) throw new Exception();
            LOGIN_SESSION.get(loginId).add(session);
        }
    }

    public static void removeLoginSession(long loginId, HttpSession session) {
        if(LOGIN_SESSION.containsKey(loginId)){
            if(!LOGIN_SESSION.get(loginId).isEmpty()){
                LOGIN_SESSION.get(loginId).removeIf(httpSession -> httpSession.getId().equals(session.getId()));
            }
        }
    }


}
