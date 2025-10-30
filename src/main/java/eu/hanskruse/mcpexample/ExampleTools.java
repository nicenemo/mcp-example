package eu.hanskruse.mcpexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class ExampleTools {
    private final Logger logger = LoggerFactory.getLogger("ExampleTools");
    private final SecureRandom rnd = new SecureRandom();

    //tools

    @McpTool(name = "hello world", description = "Just returns a Hello world")
    public String helloWorld() {
        logger.info("hello world called");
        return "Hello, Word!";
    }

    @McpTool(name= "datetime", description = "gives the current date and time in ms since the epoch")
    public String currentDateTime() {
        return "" + System.currentTimeMillis();
    }

    @McpTool(name ="echo", description = "echo's what was provided")
    public String echo(@McpToolParam(description = "value to be echoed") final String value) {
        logger.info("echo called");
        return value;
    }


    @McpTool(name = "randomNumbers", description = "provides random numbers in a range")
    public int randomNumber(@McpToolParam( description = "Origin, the first possible value" ) final int origin,
                            @McpToolParam(description = "bound, the upper limit") final int bound) {
        return  rnd.nextInt(origin, bound);
    }

    //resources
    //prompts

}
