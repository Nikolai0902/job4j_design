package design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static design.srp.ReportEngine.DATE_FORMAT;

import org.junit.Test;

import java.util.Calendar;

public class MemStoreTest {

    @Test
    public void whenOldGenerated() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        String expect = "Name; Hired; Fired; Salary;"
                + System.lineSeparator()
                + worker.getName() + ";"
                + DATE_FORMAT.format(worker.getHired().getTime()) + ";"
                + DATE_FORMAT.format(worker.getFired().getTime()) + ";"
                + worker.getSalary() + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenOldGeneratedHTML() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineHTML(store);
        String expect = String.format("<%s>", "Name, Hired, Fired, Salary")
                + System.lineSeparator()
                + String.format("<%s>", worker.getName())
                + String.format("<%s>", worker.getHired().getTime())
                + String.format("<%s>", worker.getFired().getTime())
                + String.format("<%s>", worker.getSalary())
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenOldGeneratedSalaryDollar() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineSalaryDollar(store);
        String expect = "Name; Hired; Fired; Salary;"
                + System.lineSeparator()
                + worker.getName() + ";"
                + DATE_FORMAT.format(worker.getHired().getTime()) + ";"
                + DATE_FORMAT.format(worker.getFired().getTime()) + ";"
                + "Американский доллар: " + worker.getSalary() / 50 + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenOldGeneratedHr() {
        Store store = new MemStoreSortedHr();
        Calendar now = Calendar.getInstance();
        Employee workerOne = new Employee("Ivan", now, now, 50);
        Employee workerTwo = new Employee("Ivan", now, now, 100);
        store.add(workerOne);
        store.add(workerTwo);
        Report engine = new ReportEngineHrDecr(store);
        String expect = "Name; Salary;" + System.lineSeparator()
                + workerTwo.getName() + ";"
                + workerTwo.getSalary() + ";"
                + System.lineSeparator()
                + workerOne.getName() + ";"
                + workerOne.getSalary() + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }
}