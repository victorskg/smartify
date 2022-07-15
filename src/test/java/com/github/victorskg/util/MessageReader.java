package com.github.victorskg.util;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class MessageReader {

    private final static String MESSAGES_FILE = "ValidationMessages.properties";

    public static String read(final String property) {
        try (final var inputStream = MessageReader.class.getClassLoader()
                .getResourceAsStream(MESSAGES_FILE)) {
            final var messageProperties = new Properties();
            messageProperties.load(new InputStreamReader(inputStream, UTF_8));
            return messageProperties.getProperty(property);
        } catch (IOException ex) {
            throw new RuntimeException(
                    String.format("Error reading [%s] file. Caused by: %s", MESSAGES_FILE, ex.getMessage()));
        }
    }

}
