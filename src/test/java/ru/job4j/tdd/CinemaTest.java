package ru.job4j.tdd;

import static java.util.Calendar.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.List;

@Ignore
public class CinemaTest {

    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = getInstance();
        date.set(2020, NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(List.of(new Session3D())));
    }

    @Ignore
    @Test
    public void whenFindNull() {
        Cinema cinema = new Cinema3D();
        assertNull(cinema.find(session -> true));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenBuyExp() {
        Account accountOne = new AccountCinema();
        Account accountTwo = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = getInstance();
        date.set(2020, NOVEMBER, 10, 23, 0);
        cinema.buy(accountOne, 1, 1, date);
        cinema.buy(accountTwo, 1, 1, date);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenBuyExpDisabledPlace() {
        Account accountOne = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = getInstance();
        date.set(2020, NOVEMBER, 10, 23, 0);
        cinema.buy(accountOne, 100, 100, date);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenBuyExpNotWorkingDate() {
        Account accountOne = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = getInstance();
        date.set(2020, NOVEMBER, 12, 23, 0);
        cinema.buy(accountOne, 2, 2, date);
    }
}