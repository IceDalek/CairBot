package ru.com.cair;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import ru.com.cair.event.ButtonAppearEventListener;
import ru.com.cair.event.MainEventListener;
import ru.com.cair.event.ModalInputEventListener;

public class App {

    public static void main(String[] args) {
        String token = "";
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setActivity(Activity.listening("Тишину"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.addEventListeners(new MainEventListener());
        builder.addEventListeners(new ButtonAppearEventListener());
        builder.addEventListeners(new ModalInputEventListener());
        JDA jda = builder.build();
        jda.updateCommands().addCommands(
                Commands.slash("init", "creates button")
                        .addOption(OptionType.STRING, "message", "The message to repeat.", false)).queue();
        jda.updateCommands().addCommands(
                Commands.slash("test", "creates button").addOption(OptionType.STRING,
                        "message", "The message to repeat.", false)).queue();


    }
}
