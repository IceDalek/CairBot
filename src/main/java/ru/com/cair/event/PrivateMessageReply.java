package ru.com.cair.event;

import net.dv8tion.jda.api.entities.User;

import java.util.Arrays;

public interface PrivateMessageReply {

    default void sendPrivateMessage(User user, String... messages) {
        user.openPrivateChannel().queue((privateChannel ->
        {
            Arrays.stream(messages).forEach(msg -> privateChannel.sendMessage(msg).queue());
        }));
    }
}
