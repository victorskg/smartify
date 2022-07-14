package com.github.victorskg.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageReader {

    public static String read(final String property) {
        try (final var inputStream = MessageReader.class.getClassLoader()
                .getResourceAsStream("ValidationMessages.properties")) {
            final var messageProperties = new Properties();
            messageProperties.load(new InputStreamReader(inputStream, UTF_8));
            return messageProperties.getProperty(property);
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Error reading [ValidationMessages.properties] file. Caused by: " + ex.getMessage());
        }
    }

}
