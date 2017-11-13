package com.project2.DBconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  // enable broker based stomp messaging
@EnableScheduling
@ComponentScan(basePackages="com.project2")
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer
{


	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		
		config.enableSimpleBroker("/topic", "/queue");
		config.setApplicationDestinationPrefixes("/app");
		
	}
	public void registerStompEndpoints(StompEndpointRegistry registry) 
	{
		registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
		System.out.println("Stomp End point Registered with: /chat");	
		
	}


	
}
