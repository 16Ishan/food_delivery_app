package com.delivery.common.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * Log application startup.
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class LogApplicationStartup {

    private static final String ENV_PROPS_SPRING_APPLICATION_NAME = "spring.application.name";
    private static final String ENV_PROPS_SPRING_DATASOURCE_URL = "spring.datasource.url";
    private static final String ENV_PROPS_SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    private static final String ENV_PROPS_SPRING_CONFIGSERVER_URL = "spring.cloud.config.uri";
    private static final String ENV_PROPS_SERVER_SSL_KEYSTORE = "server.ssl.key-store";
    private static final String ENV_PROPS_SERVER_PORT = "server.port";
    private static final String ENV_PROPS_SERVER_SERVLET_CONTEXT_PATH = "server.servlet.context-path";
    private static final String ENV_PROPS_SPRING_MAIL_HOST = "spring.mail.host";
    private static final String ENV_PROPS_SPRING_MAIL_PORT = "spring.mail.port";
    private static final String ENV_PROPS_APP_MAIL_NOTIFICATIONS_PDL = "spring.mail.to-pdl";
    private static final String ENV_PROPS_APP_MAIL_MONITORING_PDL = "spring.mail.monitoring-pdl";
    private static final String ENV_PROPS_APP_MAIL_AUTOMATION_PDL = "application.mail.automation-pdl";
    private static final String DEFAULT_HOST_ADDRESS = "127.0.0.1";

    private final Environment env;

    private static final String SECTION_BREAK_START = "\n---------------------------------------------------------------------------------------------------------------------------------";
    private static final String APP_IS_RUNNING = "\n    Application '{}' is running!";
    private static final String ACCESS_URLS = "\n    Access URLs:";
    private static final String LOCAL_ADDRESS = "\n        - Local:              {}://127.0.0.1:{}{}";
    private static final String EXTERNAL_ADDRESS = "\n        - External:           {}://{}:{}{}";
    private static final String PROFILES = "\n    Profile(s):               {}";
    private static final String CONFIG_SERVER = "\n    Config Server:";
    private static final String CONFIG_SERVER_URL = "\n        - URL:                {}/{}/{}";
    private static final String DATASOURCE = "\n    Datasource:";
    private static final String DATASOURCE_URL = "\n        - URL:                {}";
    private static final String DATASOURCE_USERNAME = "\n        - Username:           {}";
    private static final String MAIL_SERVER = "\n    Mail Server:";
    private static final String MAIL_SERVER_SEND_TO = "\n        - Send To:            {}";
    private static final String MAIL_SERVER_HOST                = "\n        - Host:               {}";
    private static final String MAIL_SERVER_PORT                = "\n        - Port:               {}";
    private static final String MAIL_SERVER_NOTIFICATIONS_PDL   = "\n        - Notifications PDL:  {}";
    private static final String MAIL_SERVER_MONITORING_PDL      = "\n        - Monitoring PDL:     {}";
    private static final String MAIL_SERVER_AUTOMATION_PDL      = "\n        - Automation PDL:     {}";
    private static final String SECTION_BREAK_END               = "\n---------------------------------------------------------------------------------------------------------------------------------\n";

    @EventListener({ApplicationReadyEvent.class})
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void logApplicationStartup() {
        String applicationName = Optional.ofNullable(env.getProperty(ENV_PROPS_SPRING_APPLICATION_NAME))
                .filter(StringUtils::isNotBlank).map(key -> env.getProperty(ENV_PROPS_SPRING_APPLICATION_NAME)).orElse("unnamed");
        String protocol = Optional.ofNullable(env.getProperty(ENV_PROPS_SERVER_SSL_KEYSTORE)).map(key -> "https").orElse("http");
        String serverPort = Optional.ofNullable(env.getProperty(ENV_PROPS_SERVER_PORT)).filter(StringUtils::isNotBlank).orElse("8080");
        String contextPath = Optional.ofNullable(env.getProperty(ENV_PROPS_SERVER_SERVLET_CONTEXT_PATH)).filter(StringUtils::isNotBlank).orElse("/");
        String hostAddress = getHostAddress();
        String[] profiles = Optional.of(env.getActiveProfiles()).filter(ArrayUtils::isNotEmpty).orElse(env.getDefaultProfiles());
        String configServerUrl = Optional.ofNullable(env.getProperty(ENV_PROPS_SPRING_CONFIGSERVER_URL)).filter(StringUtils::isNotBlank).orElse(null);
        String datasourceUrl = Optional.ofNullable(env.getProperty(ENV_PROPS_SPRING_DATASOURCE_URL)).filter(StringUtils::isNotBlank).orElse(null);
        String datasourceUsername = Optional.ofNullable(env.getProperty(ENV_PROPS_SPRING_DATASOURCE_USERNAME)).filter(StringUtils::isNotBlank).orElse(null);
        String mailServerHost = Optional.ofNullable(env.getProperty(ENV_PROPS_SPRING_MAIL_HOST)).filter(StringUtils::isNotBlank).orElse(null);
        String mailServerPort = Optional.ofNullable(env.getProperty(ENV_PROPS_SPRING_MAIL_PORT)).filter(StringUtils::isNotBlank).orElse(null);
        String mailNotificationsPdl = Optional.ofNullable(env.getProperty(ENV_PROPS_APP_MAIL_NOTIFICATIONS_PDL)).filter(StringUtils::isNotBlank).orElse("undefined");
        String mailMonitoringPdl = Optional.ofNullable(env.getProperty(ENV_PROPS_APP_MAIL_MONITORING_PDL)).filter(StringUtils::isNotBlank).orElse("undefined");
        String mailAutomationPdl = Optional.ofNullable(env.getProperty(ENV_PROPS_APP_MAIL_AUTOMATION_PDL)).filter(StringUtils::isNotBlank).orElse("undefined");

        if (StringUtils.isNotBlank(mailServerHost)) {
            String logString = "\n" +
                    SECTION_BREAK_START +
                    APP_IS_RUNNING +
                    ACCESS_URLS +
                    LOCAL_ADDRESS +
                    EXTERNAL_ADDRESS +
                    PROFILES +
                    CONFIG_SERVER +
                    CONFIG_SERVER_URL +
                    DATASOURCE +
                    DATASOURCE_URL +
                    DATASOURCE_USERNAME +
                    MAIL_SERVER +
                    MAIL_SERVER_SEND_TO +
                    MAIL_SERVER_HOST +
                    MAIL_SERVER_PORT +
                    MAIL_SERVER_NOTIFICATIONS_PDL +
                    MAIL_SERVER_MONITORING_PDL +
                    MAIL_SERVER_AUTOMATION_PDL +
                    SECTION_BREAK_END;

            log.info(logString,
                    applicationName,
                    protocol,
                    serverPort,
                    contextPath,
                    protocol,
                    hostAddress,
                    serverPort,
                    contextPath,
                    profiles,
                    configServerUrl,
                    applicationName,
                    String.join(",", profiles),
                    datasourceUrl,
                    datasourceUsername,
                    mailServerHost,
                    mailServerPort,
                    mailNotificationsPdl,
                    mailMonitoringPdl,
                    mailAutomationPdl
            );
        } else if (StringUtils.isNotBlank(datasourceUrl)) {
            String logString = "\n" +
                    SECTION_BREAK_START +
                    APP_IS_RUNNING +
                    ACCESS_URLS +
                    LOCAL_ADDRESS +
                    EXTERNAL_ADDRESS +
                    PROFILES +
                    CONFIG_SERVER +
                    CONFIG_SERVER_URL +
                    DATASOURCE +
                    DATASOURCE_URL +
                    DATASOURCE_USERNAME +
                    SECTION_BREAK_END;

            log.info(logString,
                    applicationName,
                    protocol,
                    serverPort,
                    contextPath,
                    protocol,
                    hostAddress,
                    serverPort,
                    contextPath,
                    profiles,
                    configServerUrl,
                    applicationName,
                    String.join(",", profiles),
                    datasourceUrl,
                    datasourceUsername
            );
        } else {
            String logString = "\n" +
                    SECTION_BREAK_START +
                    APP_IS_RUNNING +
                    ACCESS_URLS +
                    LOCAL_ADDRESS +
                    EXTERNAL_ADDRESS +
                    PROFILES +
                    CONFIG_SERVER +
                    CONFIG_SERVER_URL +
                    SECTION_BREAK_END;

            log.info(logString,
                    applicationName,
                    protocol,
                    serverPort,
                    contextPath,
                    protocol,
                    hostAddress,
                    serverPort,
                    contextPath,
                    profiles,
                    configServerUrl,
                    applicationName,
                    String.join(",", profiles)
            );
        }
    }

    private String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host address could not be determined, using `{}` as fallback", DEFAULT_HOST_ADDRESS);
        }
        return DEFAULT_HOST_ADDRESS;
    }

}
