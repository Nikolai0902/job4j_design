package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    public static final String INDENT = "----";
    public static final String SEP = System.lineSeparator();

    StringBuilder builderTest = new StringBuilder();

    @Override
    public void print(Menu menu) {
        StringBuilder builder = new StringBuilder();
        menu.forEach(x -> {
            builder.append(INDENT.repeat((x.getNumber().length() / 2)));
            builder.append(x.getNumber()).append(x.getName()).append(SEP);
        });
        System.out.println(builder);
        builderTest.append(builder);
    }

    @Override
    public String printTest() {
        return builderTest.toString();
    }
}

