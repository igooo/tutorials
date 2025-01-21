package org.igooo.ai.receipt.ai;

import java.io.InputStream;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class ChatService {
	private final ChatClient chatClient;

	public ChatService(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	public <T> T prompt(String prompt, String mimeType, InputStream inputStream ,Class<T> t) {
		return this.chatClient.prompt().user((spec) -> spec.text(prompt).media(
				MimeTypeUtils.parseMimeType(mimeType), new InputStreamResource(inputStream)
		)).call().entity(t);
	}
}
