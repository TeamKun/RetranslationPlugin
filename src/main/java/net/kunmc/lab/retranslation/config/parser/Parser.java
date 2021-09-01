package net.kunmc.lab.retranslation.config.parser;

public abstract class Parser<T> {
    public abstract T parse(String str);
}
