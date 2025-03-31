package com.example.FireFly_frontend.slack;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SlackService {

    @Value("${slack.bot.token}")
    private String slackBotToken;

    public void publishMessage(String channelName, String message) {
        MethodsClient client = Slack.getInstance().methods();
        try {
            client.chatPostMessage(r -> r
                    .token(slackBotToken)
                    .channel(channelName)
                    .text(message)
            );
        } catch (IOException | SlackApiException e) {
            log.error("Unsuccessful attempt to send a slack notification. Reason: {}", e.getMessage());
        }
    }

}
