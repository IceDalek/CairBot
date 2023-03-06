package ru.com.cair.cache;

import lombok.Data;
import net.dv8tion.jda.api.entities.User;
import ru.com.cair.enums.RegistrationStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCacheHolder {

    public static Map<String, RegistrationStep> cacheList = new HashMap<>();
}
