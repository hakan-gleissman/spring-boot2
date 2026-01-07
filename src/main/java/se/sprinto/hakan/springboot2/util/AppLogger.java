package se.sprinto.hakan.springboot2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AppLogger {

    private static final Logger log = LoggerFactory.getLogger(AppLogger.class);

    public void info(String message) {
        log.info(message);
    }

    public void info(String message, Object... args) {
        log.info(message, args);
    }
}
