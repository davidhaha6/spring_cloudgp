package com.david.cloud.binderhttp;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sc on 2019-03-13.
 */
public class MessageReceiverHandlerInterceptor implements HandlerInterceptor {

    public static final String ENDPOINT_URI = "/message/receive";

    private MessageChannel inputChannel;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        if (request.getRequestURI().equals(ENDPOINT_URI)) {
            processEndPoint(request,response);
            return false;
        }
        return true;
    }

    private void processEndPoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //请求内容
        InputStream inputStream=request.getInputStream();
        // 接收到客户端发送的 HTTP 实体，需要 MessageChannel 回写
        byte[] requestBody = StreamUtils.copyToByteArray(inputStream);
        // 写入到 MessageChannel
        inputChannel.send(new GenericMessage<>(requestBody));
        response.getWriter().write("ok");
    }

    public void setInputChannel(MessageChannel inputChannel) {
        this.inputChannel = inputChannel;
    }
}
