package org.igooo.daangn.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.io.InputStream;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public <T> T prompt(String prompt, Class<T> t) {
        return this.chatClient.prompt().user((spec) -> spec.text(prompt)).call().entity(t);
    }

    public <T> T prompt(String prompt, String mimeType, InputStream inputStream, Class<T> t) {
        return this.chatClient.prompt().user((spec) -> spec.text(prompt).media(
                MimeTypeUtils.parseMimeType(mimeType), new InputStreamResource(inputStream)
        )).call().entity(t);
    }

}
