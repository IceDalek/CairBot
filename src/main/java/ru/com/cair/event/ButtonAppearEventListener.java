package ru.com.cair.event;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.springframework.stereotype.Component;
import ru.com.cair.util.Constants;

@Component
public class ButtonAppearEventListener extends ListenerAdapter implements PrivateMessageReply {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("init")) {
            event.reply("Click the button to say hello")
                    .addActionRow(Button.primary("hello", "Click Me")).queue();
        }
        if (event.getName().equals("test")) {
            System.out.println("test");
//            event.reply("Click the button to say hello")
//                    .addActionRow(Button.primary("hello", "Click Me")).queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
//        sendPrivateMessage(event.getUser(), "You pushed button!");

        TextInput subject = TextInput.create("subject", "Subject", TextInputStyle.SHORT)
                .setPlaceholder("Subject of this ticket")
                .setMinLength(1)
                .setMaxLength(100) // or setRequiredRange(10, 100)
                .build();
        Modal modal = Modal.create(Constants.CREATE_GROUP_MODAL_ID, "Test")
                .addComponents(ActionRow.of(subject))
                .build();
        event.replyModal(modal).queue();
    }
}
