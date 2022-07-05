package design.srp;

import static ru.job4j.design.srp.ReportEngineSalaryDollar.COURSE;
import static ru.job4j.design.srp.ReportXML.DATE_FORMAT_XML;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

import org.junit.Test;
import ru.job4j.design.srp.*;
import ru.job4j.design.storage.Employee;
import ru.job4j.design.storage.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

public class MemStoreTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenOldGeneratedHTML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE HTML>")
                .append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta http-equiv=\"Сontent-Type\" content=\"text/html; cgarser")
                .append(System.lineSeparator())
                .append("<title>Таблица</title>")
                .append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<table>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th>")
                .append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td>")
                .append(System.lineSeparator())
                .append("</table>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenOldGeneratedSalaryDollar() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineSalaryDollar(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append("Американский доллар: ").append(worker.getSalary() / COURSE).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenOldGeneratedHr() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerOne = new Employee("Ivan", now, now, 50);
        Employee workerTwo = new Employee("Ivan", now, now, 100);
        store.add(workerOne);
        store.add(workerTwo);
        Report engine = new ReportEngineHrDecr(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;").append(System.lineSeparator())
                .append(workerTwo.getName()).append(";")
                .append(workerTwo.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerOne.getName()).append(";")
                .append(workerOne.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenOldGeneratedXML() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportXML(store);
        String str = ""
                + "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employees>\n"
                + "    <employees name=\"" + worker.getName()
                + "\" hired=\"" + DATE_FORMAT_XML.format(worker.getHired().getTime())
                + "\" fired=\"" + DATE_FORMAT_XML.format(worker.getFired().getTime())
                + "\" salary=\"" + worker.getSalary() + "\"/>\n"
                + "</employees>\n";
        assertThat(engine.generate(em -> true), is(str));
    }

    @Test
    public void whenOldGeneratedJson() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportJSON(store);
        String str = "[{\"name\":\"" + worker.getName() + "\","
                + "\"hired\":{\""
                + "year\":" + now.get(Calendar.YEAR) + ","
                + "\"month\":" + now.get(Calendar.MONTH) + ","
                + "\"dayOfMonth\":" + now.get(Calendar.DAY_OF_MONTH) + ","
                + "\"hourOfDay\":" + now.get(Calendar.HOUR_OF_DAY) + ","
                + "\"minute\":" + now.get(Calendar.MINUTE) + ","
                + "\"second\":" + now.get(Calendar.SECOND) + "},"
                + "\"fired\":{\""
                + "year\":" + now.get(Calendar.YEAR) + ","
                + "\"month\":" + now.get(Calendar.MONTH) + ","
                + "\"dayOfMonth\":" + now.get(Calendar.DAY_OF_MONTH) + ","
                + "\"hourOfDay\":" + now.get(Calendar.HOUR_OF_DAY) + ","
                + "\"minute\":" + now.get(Calendar.MINUTE) + ","
                + "\"second\":" + now.get(Calendar.SECOND) + "},"
                + "\"salary\":" + worker.getSalary() + "}]";
        assertThat(engine.generate(em -> true), is(str));
    }
}