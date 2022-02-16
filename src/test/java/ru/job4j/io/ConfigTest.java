package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("n"), is("Nikolai"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithExc1() {
        String path = "./data/pair_with_exception.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithExc2() {
        String path = "./data/pair_with_exception_2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithExc3() {
        String path = "./data/pair_with_exception_3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithExc4() {
        String path = "./data/pair_with_exception_4.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("v"), is("=1"));
    }

    @Test
    public void whenPairWithExc5() {
        String path = "./data/pair_with_exception_5.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("v"), is("q="));
    }
}