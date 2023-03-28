package ru.com.cair.config;

import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.com.cair.event.ButtonAppearEventListener;
import ru.com.cair.event.MainEventListener;
import ru.com.cair.event.ModalInputEventListener;

import javax.annotation.PostConstruct;

@Configuration
public class JdaConfig {

    @Value("${bot.token}")
    @Setter
    private String token;

    @Autowired
    private MainEventListener mainEventListener;

    @Autowired
    private ButtonAppearEventListener buttonAppearEventListener;

    @Autowired
    private ModalInputEventListener modalInputEventListener;

    @PostConstruct
    public void init(){
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setActivity(Activity.listening("Тишину"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.addEventListeners(mainEventListener);
        builder.addEventListeners(buttonAppearEventListener);
        builder.addEventListeners(modalInputEventListener);
        JDA jda = builder.build();
        jda.updateCommands().addCommands(
                Commands.slash("init", "creates button").addOption(OptionType.STRING,
                        "message", "The message to repeat.", false)).queue();
    }
}
