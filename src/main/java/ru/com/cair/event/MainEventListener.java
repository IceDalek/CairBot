package ru.com.cair.event;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.entities.channel.concrete.PrivateChannelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.com.cair.cache.StudentCache;
import ru.com.cair.cache.UserCacheHolder;
import ru.com.cair.entity.Student;
import ru.com.cair.enums.RegistrationStep;
import ru.com.cair.service.StudentService;

@Component
public class MainEventListener extends ListenerAdapter implements PrivateMessageReply {

    //Создавать канал по шаблону
    //Спросить имя и фамилию
    //Список групп

    @Autowired
    private StudentService studentService;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel() instanceof PrivateChannelImpl) {

            String message = event.getMessage().getContentRaw();
            User author = event.getAuthor();
            String group = "";
            String fio = "";
            if (author.isBot()) {
                return;
            }
            if (message.equals("test")) {
                StudentCache studentCache = new StudentCache();
                studentCache.setRegistrationStep(RegistrationStep.FIO_INPUT);
                UserCacheHolder.cacheList.put(author.getId(), studentCache);
                sendPrivateMessage(author, "Привет! напиши свое ФИО");
            } else {
                StudentCache studentCache = UserCacheHolder.cacheList.get(author.getId());
                RegistrationStep step = studentCache.getRegistrationStep();
                if (step.equals(RegistrationStep.FIO_INPUT)) {
                    fio = message;
                    sendPrivateMessage(author, "Проверь данные, твое фио: " + fio,
                            "Теперь укажи свою группу!");
                    String[] split = fio.split(" ");
                    studentCache.setLastName(split[0]);
                    studentCache.setName(split[1]);
                    studentCache.setRegistrationStep(RegistrationStep.GROUP_INPUT);
                    UserCacheHolder.cacheList.put(author.getId(), studentCache);
                }
                if (step.equals(RegistrationStep.GROUP_INPUT)) {
                    group = message;
                    sendPrivateMessage(author, "Проверь данные, твоя группа: " + group,
                            "Если все данные верны, напиши \"Да\"");
                    studentCache.setGroup(group);
                    studentCache.setRegistrationStep(RegistrationStep.CONFIRMATION);
                    UserCacheHolder.cacheList.put(author.getId(), studentCache);
                }
                if (step.equals(RegistrationStep.CONFIRMATION)) {
                    if (message.equalsIgnoreCase("Да")) {
                        studentCache = UserCacheHolder.cacheList.get(author.getId());
                        Student student = new Student();
                        student.setLessonGroup(studentCache.getGroup());
                        student.setName(studentCache.getName());
                        student.setLastName(studentCache.getLastName());
                        studentService.createStudent(student);
                        sendPrivateMessage(author, "Вы успешно добавлены!");
                        UserCacheHolder.cacheList.remove(author.getId());
                    }
                }
            }
        }
    }
}
