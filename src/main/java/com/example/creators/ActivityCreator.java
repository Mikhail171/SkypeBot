package com.example.creators;

import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ActivityTypes;
import org.springframework.stereotype.Component;

@Component
public class ActivityCreator {

    private ActivityCreator() {

    }

    public static Activity createEchoActivity(Activity activity) {
        String textActivity = activity.text();
        if (textActivity == null) {
            textActivity = "Бот проснулся!";
        }

        return createEmptyActivity(activity)
                .withText(textActivity);
    }

    private static Activity createEmptyActivity(Activity activity) {
        return new Activity()
                .withType(ActivityTypes.MESSAGE)
                .withRecipient(activity.from())
                .withFrom(activity.recipient());
    }
}
