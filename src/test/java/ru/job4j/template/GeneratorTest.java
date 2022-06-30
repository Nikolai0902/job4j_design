package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@Ignore
public class GeneratorTest {

    @Ignore
    @Test
    public void produce() {
        String pattern = "I am a ${name}, Who are ${subject}?";
        Map<String, String> value = Map
                .of("name", "Petr Arsentev", "subject", "you");
        Generator generator = new MyGenerator();
        assertThat("I am a Petr Arsentev, Who are you?",
                is(generator.produce(pattern, value)));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void invalidKeyException() {
        String pattern = "I am a ${name}, Who are ${subject}?";
        Map<String, String> value = Map
                .of("name", "Petr Arsentev", "key", "you");
        Generator generator = new MyGenerator();
        generator.produce(pattern, value);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void excessKeyException() {
        String pattern = "I am a ${name}, Who are ${subject}?";
        Map<String, String> value = Map
                .of("name", "Petr Arsentev", "name", "Ivan Ivanov",
                        "key", "you");
        Generator generator = new MyGenerator();
        generator.produce(pattern, value);
    }
}