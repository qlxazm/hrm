package org.java.hrm.controller;

import org.java.hrm.domain.Message;
import org.java.hrm.domain.Shout;
import org.java.hrm.service.HrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.Date;

@Controller
public class STOMPMessageController {
    private static final Logger logger = LoggerFactory.getLogger(STOMPMessageController.class);

    @Autowired
    private SimpMessageSendingOperations messaging;

    @Autowired
    private HrmService service;

    @MessageMapping("/marco")
    public Shout handleShout(Shout shout) {
        logger.info("接收到了来自客户端的消息：" + shout.getMessage());
        Shout outgoing = new Shout();
        outgoing.setMessage("这是响应的消息！");
        return outgoing;
    }

    /**
     * 发送给所有用户
     * @param message
     * @param mv
     * @return
     */
    @RequestMapping("/sendMessage")
    public ModelAndView sendMessage(String message, ModelAndView mv) {

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message messageObj = new Message(message, user.getUsername(), "all");

        // 发送信息到/topic/marco主题
        messaging.convertAndSend("/topic/marco", message);

        // 向消息表中新增加一条记录
        service.addMessage(messageObj);

        mv.setViewName("/main");
        mv.addObject("page", "default.jsp");
        return mv;
    }

    /**
     * 发送信息给管理员
     * @param message
     * @param mv
     * @return
     */
    @RequestMapping("/sendMessageToManager")
    public ModelAndView sendMessageToManager(String message, ModelAndView mv) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        messaging.convertAndSendToUser("管理员", "/queue/notification", message);
        mv.setViewName("/main");
        mv.addObject("page", "default.jsp");
        return mv;
    }

    /**
     * 发送信息给普通的用户
     * @param message
     * @param mv
     * @return
     */
    @RequestMapping("/sendMessageToUser")
    public ModelAndView sendMessageToUser(String message, ModelAndView mv) {
        messaging.convertAndSendToUser("游客", "/queue/notification", message);
        mv.setViewName("/main");
        mv.addObject("page", "default.jsp");
        return mv;
    }
}
