package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short sh = 2;
        int i = 3;
        long l = 4;
        float f = 5;
        double d = 6;
        char ch = 'c';
        boolean bool = true;
        LOG.debug("Usage symbols: byte - {}, short - {}, int - {}, long - {}, float - {}, double - {}, char - {}, boolean - {}",
                b, sh, i, l, f, d, ch, bool);
    }
}
