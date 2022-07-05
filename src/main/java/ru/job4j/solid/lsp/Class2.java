package ru.job4j.solid.lsp;

/**
 * нарушение LSP: усиление предусловий в подклассе.
 * Предусловие - это условие, которое проверяет корректность
 * аргументов конструктора или метода.
 */
public class Class2 {
    private int rows;

    public Class2(int rows) {
        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }

    public boolean isHuge(int rows) {
        return rows > 10;
    }
}

class Class22 extends Class2 {

    public Class22(int rows) {
        super(rows);
    }

    @Override
    public boolean isHuge(int rows) {
        return rows > 8;
    }
}