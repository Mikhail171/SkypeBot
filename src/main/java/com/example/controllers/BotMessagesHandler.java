package com.example.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.microsoft.bot.connector.ConnectorClient;
import com.microsoft.bot.connector.Conversations;
import com.microsoft.bot.connector.customizations.MicrosoftAppCredentials;
import com.microsoft.bot.connector.implementation.ConnectorClientImpl;
import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ResourceResponse;
import com.example.creators.ActivityCreator;
import com.example.creators.ConversationCreator;
import com.example.senders.ResourceResponseSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Controller
@RequestMapping(path = "/api/messages")
public class BotMessagesHandler {

    private final MicrosoftAppCredentials credentials;

    @Autowired
    public BotMessagesHandler(MicrosoftAppCredentials credentials) {
        this.credentials = credentials;
    }

    @PostMapping(path = "")
    public ResourceResponse create
            (@RequestBody
             @Valid
             @JsonDeserialize(using = DateTimeDeserializer.class)
                     Activity activity) {
        ConnectorClient connector =
                new ConnectorClientImpl(activity.serviceUrl(), credentials);

        Activity echoActivity = ActivityCreator.createEchoActivity(activity);
        Conversations conversation = ConversationCreator.createResponseConversation(connector);

        return ResourceResponseSender.send(conversation, activity, echoActivity);
    }
}
