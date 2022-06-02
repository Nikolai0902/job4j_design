package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Owner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "home")
@XmlAccessorType(XmlAccessType.FIELD)
public class Home {

    @XmlAttribute
    private boolean target;

    @XmlAttribute
    private int rooms;
    private String street;
    private Owner owner;

    @XmlElementWrapper(name = "systemSafes")
    @XmlElement(name = "systemSafe")
    private String[] systemSafe;

    public Home() {
    }

    public Home(boolean target, int rooms, String street, Owner owner, String[] systemSafe) {
        this.target = target;
        this.rooms = rooms;
        this.street = street;
        this.owner = owner;
        this.systemSafe = systemSafe;
    }

    @Override
    public String toString() {
        return "Home{" + "target=" + target + ", rooms="
                + rooms + ", street='" + street + '\'' + ", owner="
                + owner + ", systemSafe=" + Arrays.toString(systemSafe) + '}';
    }

    /**
     * Получаем контекст для доступа к АПИ context
     * Указываем, что нам нужно форматирование marshaller
     * Сериализуем
     * Для десериализации нам нужно создать десериализатор unmarshaller
     * десериализуем
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        final Home home = new Home(true, 4,
                "Red", new Owner("Nik", "Ivanov", "02.02.1995"),
                new String[] {"Fire", "Alarm "});

        JAXBContext context = JAXBContext.newInstance(Home.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(home, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Home result = (Home) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
