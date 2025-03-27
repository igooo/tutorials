package org.igooo.springmcp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
public class SpringMcpFirstApplication {

    static final String FOLER = "D:\\user-context";

    public static void main(String[] args) {
        SpringApplication.run(SpringMcpFirstApplication.class, args);
    }

}

@Configuration
class LocalToolsAutoConfiguration {

    @Bean
    NamedMcpClientRunner namedMcpClientRunner(ChatClient.Builder builder, Tools tools) {
        return new NamedMcpClientRunner(builder.defaultTools(tools));
    }

    @Component
    static class Tools {
        @Tool(description = "returns all the files in a folder called " + SpringMcpFirstApplication.FOLER + ".")
        String[] listFiles() {
            System.out.println("returning the files from " + SpringMcpFirstApplication.FOLER);
            return new File(SpringMcpFirstApplication.FOLER).list();
        }
    }
}

class NamedMcpClientRunner implements ApplicationRunner, BeanNameAware {
    private final AtomicReference<String> beanName = new AtomicReference<>();

    private final ChatClient.Builder builder;

    NamedMcpClientRunner(ChatClient.Builder builder) {
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
