package org.java.hrm.util;

import org.java.hrm.domain.Message;
import org.java.hrm.service.HrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private HrmService service;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("=====================在这里面处理登陆成功的逻辑=========================================");

        User user = (User)authentication.getPrincipal();
        String username = user.getUsername();
        org.java.hrm.domain.User user1 = new org.java.hrm.domain.User();
        user1.setUsername(username);

        List<Message> unreadMessages = service.getUnreadMessages(user1);
        System.out.println("维读消息：" + unreadMessages);

        service.modifyLoginTime(username);
        response.sendRedirect("/main");
    }
}
