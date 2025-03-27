package org.igooo.springmcpserver;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;

@SpringBootApplication
public class SpringMcpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMcpServerApplication.class, args);
    }

    @Bean
    ToolCallbackProvider fileSystemToolProvider(FileTools fileTools) {
        return MethodToolCallbackProvider.builder().toolObjects(fileTools).build();
    }
}

@Component
class FileTools {

    @Tool(description = "returns all the files in a folder. D:\\user-context")
    String[] listFiles() {
        System.out.println("calling listFiles");
        return new File("D:\\user-context").list();
    }

}
