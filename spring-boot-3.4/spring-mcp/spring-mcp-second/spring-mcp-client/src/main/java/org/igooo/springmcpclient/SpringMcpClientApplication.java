package org.igooo.springmcpclient;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
public class SpringMcpClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMcpClientApplication.class, args);
    }

}

@Configuration
class LocalFileSystemMcpConfiguration {
    @Bean
    McpSyncClient mcpSyncClient() {
        var mcp = McpClient
                .sync(new HttpClientSseClientTransport("http://localhost:8081"))
                .build();
        mcp.initialize();
        return mcp;
    }

    @Bean
    NamedMcpClientRunner localFileSystemMcpClientRunner(ChatClient.Builder builder, McpSyncClient client) {
        var provider = new SyncMcpToolCallbackProvider(client);
        return new NamedMcpClientRunner(builder.defaultTools(provider));
    }
}

class NamedMcpClientRunner implements ApplicationRunner, BeanNameAware {
    private final AtomicReference<String> beanName = new AtomicReference<>();

    private final ChatClient.Builder builder;

    public NamedMcpClientRunner(ChatClient.Builder builder) {
        this.builder = builder;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName.set(name);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var prompt = """
                what files are in the user-context folder, and of those files,
                which hava a name that corresponds to a chinese greeting?
                """;

        var response = this.builder
                .build()
                .prompt(prompt)
                .call()
                .entity(ChineseFile.class);

        System.out.println(this.beanName.get() + " response: " + response);
    }
}

record ChineseFile(String fileName, String word) {
}
