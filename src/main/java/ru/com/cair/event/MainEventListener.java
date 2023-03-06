package ru.com.cair.event;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.entities.channel.concrete.PrivateChannelImpl;
import ru.com.cair.cache.UserCache;
import ru.com.cair.cache.UserCacheHolder;
import ru.com.cair.enums.RegistrationStep;

import java.util.Arrays;

public class MainEventListener extends ListenerAdapter implements PrivateMessageReply {

    //Создавать канал по шаблону
    //Спросить имя и фамилию
    //Список групп


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel() instanceof PrivateChannelImpl) {

            String message = event.getMessage().getContentRaw();
            User author = event.getAuthor();
            if (author.isBot()) {
                return;
            }
            if (message.equals("test")) {
                UserCacheHolder.cacheList.put(author.getId(), RegistrationStep.FIO_INPUT);
                sendPrivateMessage(author, "Привет! напиши свое ФИО");
            } else {
                RegistrationStep step = UserCacheHolder.cacheList.get(author.getId());
                if (step.equals(RegistrationStep.FIO_INPUT)) {
                    String fio = message;
                    sendPrivateMessage(author, "Проверь данные, твое фио: " + fio,
                            "Теперь укажи свою группу!");
                    UserCacheHolder.cacheList.put(author.getId(), RegistrationStep.GROUP_INPUT);
                }
                if (step.equals(RegistrationStep.GROUP_INPUT)) {
                    String group = message;
                    sendPrivateMessage(author, "Проверь данные, твоя группа: " + group,
                            "Если все данные верны, напиши \"Да\"");
                    UserCacheHolder.cacheList.put(author.getId(), RegistrationStep.CONFIRMATION);
                }
                if (step.equals(RegistrationStep.CONFIRMATION)) {
                    if (message.equalsIgnoreCase("Да")) {
                        sendPrivateMessage(author, "Вы успешно добавлены!");
                        UserCacheHolder.cacheList.remove(author.getId());
                        //тут мы добавляем его в бд и назначаем роль
                    }
                }
            }
        }
    }
}
