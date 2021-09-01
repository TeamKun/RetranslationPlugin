package net.kunmc.lab.retranslation.config.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringListParser extends Parser<List<String>> {
    @Override
    public List<String> parse(String str) {
        return new ArrayList<>(Arrays.asList(str.split(" ")));
    }
}
