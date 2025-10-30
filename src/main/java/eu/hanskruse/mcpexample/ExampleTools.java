package eu.hanskruse.mcpexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ExampleTools {
    private final Logger logger = LoggerFactory.getLogger("ExampleTools");
    private final SecureRandom rnd = new SecureRandom();

    //tools

    @McpTool(name = "getHelloWorld", description = "Returns the string 'Hello, World!'. Useful for testing connectivity.")
    public String helloWorld() {
        logger.info("hello world called");
        return "Hello, Word!";
    }

    @McpTool(name= "getCurrentDateTime", description = "Gets the current date and time for a given time zone, presented in a human-readable format. Time zone defaults to UTC if not provided.")
    public String currentDateTime(@McpToolParam(description = "The time zone for which to get the current date and time. If not provided, it defaults to UTC. The time zone should be a valid time zone ID, such as 'America/New_York' or 'Europe/London'.") final String timezone) {
        final var zoneId = ZoneId.of(timezone != null && !timezone.trim().isEmpty() ? timezone : "UTC");
        final var zonedDateTime = ZonedDateTime.now(zoneId);
        final var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        return zonedDateTime.format(formatter);
    }

    @McpTool(name ="echoValue", description = "Returns the same value that was provided as input. Useful for testing and debugging.")
    public String echo(@McpToolParam(description = "The string value to be returned.") final String value) {
        logger.info("echo called");
        return value;
    }


    @McpTool(name = "getRandomNumber", description = "Generates a random integer within a specified range, from an inclusive origin to an exclusive bound.")
    public int randomNumber(@McpToolParam( description = "The inclusive lower bound of the random number range. Defaults to 0 if not provided." ) final Integer origin,
                            @McpToolParam(description = "The exclusive upper bound of the random number range. Defaults to 10 if not provided.") final Integer bound) {
        final var o = origin != null ? origin : 0;
        final var b = bound != null ? bound : 10;
        if (o >= b) {
            logger.error("Origin {} must be less than bound {}.", o, b);
            throw new IllegalArgumentException("Origin must be less than bound.");
        }
        return  rnd.nextInt(o, b);
    }

    //resources
    //prompts

}
