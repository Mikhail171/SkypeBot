package com.example.config;

import com.microsoft.bot.connector.customizations.MicrosoftAppCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class BotConfig {

    private final Environment environment;

    @Autowired
    public BotConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public MicrosoftAppCredentials getCredentials() {
        return new MicrosoftAppCredentials(environment.getProperty("bot.appId"),
                environment.getProperty("bot.appPassword"));
    }
}
