package org.igooo.ai.receipt.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AIConfiguration {
	@Bean
	ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
}
