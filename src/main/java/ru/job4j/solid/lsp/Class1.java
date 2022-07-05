package ru.job4j.solid.lsp;

/**
 * Нарушение LSP: в наследнике ослабляется постусловие.
 * Постусловие - это условие, налагаемое на возвращаемое значение метода.
 */
public class Class1 {
    private int sum;

    public Class1(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public int applyDiscount() {
        if (sum >= 100) {
            sum -= 10;
        }
        return sum;
    }
}

class Class11 extends Class1 {

    public Class11(int sum) {
        super(sum);
    }

    @Override
    public int applyDiscount() {
        return getSum() - 10;
    }
}