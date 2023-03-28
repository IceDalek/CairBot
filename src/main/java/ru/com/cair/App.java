package ru.com.cair;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.com.cair.event.ButtonAppearEventListener;
import ru.com.cair.event.MainEventListener;
import ru.com.cair.event.ModalInputEventListener;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
       SpringApplication.run(App.class);
    }
}
