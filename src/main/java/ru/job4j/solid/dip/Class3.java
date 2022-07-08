package ru.job4j.solid.dip;

/**
 * Наркшен принцип DIP.
 * Модули верхнего уровня зависят от модулей нижнего уровня.
 */
public class Class3 {
    LessonOne lessonOne = new LessonOne();
    LessonTwo lessonTwo = new LessonTwo();

    void result() {
        lessonOne.selectWork();
        lessonTwo.selectWork();
    }
}

class LessonOne {
    void selectWork() {
        System.out.println("ООП");
    }
}

class LessonTwo {
    void selectWork() {
        System.out.println("SOLID");
    }
}
