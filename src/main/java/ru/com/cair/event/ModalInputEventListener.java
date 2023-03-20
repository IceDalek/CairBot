package ru.com.cair.event;

import lombok.SneakyThrows;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.*;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.com.cair.util.Constants;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModalInputEventListener extends ListenerAdapter {

    private void createCategories(Guild guild, String groupName) throws ExecutionException, InterruptedException {
        List<Category> checkCategory = guild.getCategoriesByName(groupName, true);
        List<Category> categories = guild.getCategoriesByName("test", true);
        if(checkCategory.size() == 0){
            List<GuildChannel> channels = categories.get(0).getChannels();
            Category createdCategory = guild.createCategory(groupName).submit().get();
            for (GuildChannel categoryChannel : channels) {

                if (categoryChannel instanceof TextChannel) {
                    createdCategory.createTextChannel(getFormattedName(groupName, categoryChannel)).queue();
                }
                if (categoryChannel instanceof VoiceChannel) {
                    createdCategory.createVoiceChannel(getFormattedName(groupName, categoryChannel)).queue();
                }
                if (categoryChannel instanceof NewsChannel) {
                    createdCategory.createNewsChannel(getFormattedName(groupName, categoryChannel)).queue();
                }
                if (categoryChannel instanceof StageChannel) {
                    createdCategory.createStageChannel(getFormattedName(groupName, categoryChannel)).queue();
                }
            }
        }
    }

    private String getFormattedName(String groupName, GuildChannel categoryChannel) {
        return String.format("%s-%s", categoryChannel.getName(), groupName);
    }

    @SneakyThrows
    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals(Constants.CREATE_GROUP_MODAL_ID)) {
            String subject = event.getValue("subject").getAsString();
            createCategories(event.getGuild(), subject);
            event.reply("Thanks for your request!").setEphemeral(true).queue();
        }
    }

}
