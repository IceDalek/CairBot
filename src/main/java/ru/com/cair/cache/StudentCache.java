package ru.com.cair.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.com.cair.enums.RegistrationStep;

@Data
public class StudentCache {

    private RegistrationStep registrationStep;

    private String name;

    private String lastName;

    private String group;

}
