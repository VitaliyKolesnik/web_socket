package com.kolesnyk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WSConfig implements WebSocketConfigurer {
    @Autowired
    private WebSocketHandler handler;

    public DispatcherServlet servlet(){
        DispatcherServlet dispatcherServlet = new DispatcherServlet(new AnnotationConfigEmbeddedWebApplicationContext());
        return dispatcherServlet;
    }

    @Bean
    public ServletRegistrationBean registrationBean(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet());
        registrationBean.setName(
                DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        registrationBean.setAsyncSupported(true);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        registrationBean.setLoadOnStartup(0);
        for (String s : registrationBean.getUrlMappings()) {
            System.out.println(s);
        }
        System.out.println(registrationBean.isAsyncSupported());
        return registrationBean;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        WebSocketHandlerRegistration webSocketHandlerRegistration = webSocketHandlerRegistry.addHandler(handler, "/socket");
        webSocketHandlerRegistration.setHandshakeHandler(handler());
        webSocketHandlerRegistration.setAllowedOrigins("http://localhost");
    }

    @Bean
    public HandshakeHandler handler() {
        return (serverHttpRequest, serverHttpResponse, webSocketHandler, map) -> {
            System.out.println(serverHttpRequest);
            return true;
        };
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }
}
