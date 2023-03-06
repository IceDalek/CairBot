package ru.com.cair.event;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.internal.entities.channel.concrete.PrivateChannelImpl;
import ru.com.cair.cache.UserCacheHolder;
import ru.com.cair.enums.RegistrationStep;

public class ButtonAppearEventListener extends ListenerAdapter implements PrivateMessageReply {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("init")) {
            event.reply("Click the button to say hello")
                    .addActionRow(Button.primary("hello", "Click Me")).queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("Создать группу!")) {
            sendPrivateMessage(event.getUser(), "You pushed button!");
        }
    }
}
