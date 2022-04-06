package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int deleted;
        int changed = 0;
        Map<Integer, String> map  = new HashMap<>();
        for (var element : previous) {
            map.put(element.getId(), element.getName());
        }
        for (var element : current) {
            String result = map.put(element.getId(), element.getName());
            if (result == null) {
                added++;
            } else if (!result.equals(element.getName())) {
                changed++;
            }
        }
        deleted = map.size() - current.size();
        return new Info(added, changed, deleted);
    }
}
