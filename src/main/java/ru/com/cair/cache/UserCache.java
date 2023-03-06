package ru.com.cair.cache;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCache {

    private String id;

    private int registrationStep;

}
