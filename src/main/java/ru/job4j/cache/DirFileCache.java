package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        if (!cache.containsKey(key)) {
            try (BufferedReader in = new BufferedReader(new FileReader(cachingDir + key))) {
                StringBuffer stringBuffer = new StringBuffer();
                in.lines().forEach(s -> stringBuffer.append(s).append(System.lineSeparator()));
                put(key, stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return get(key);
    }
}
