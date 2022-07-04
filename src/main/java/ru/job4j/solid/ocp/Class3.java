package ru.job4j.solid.ocp;

import java.awt.*;

/**
 * здесь нарушен OCP принцип, т.к. при изменении способов отправки уведомлений придется менять существующий код.
 */
public class Class3 {
    public void send(String msg, TrayIcon.MessageType type) {
        if (type == TrayIcon.MessageType.INFO) {
            sendSMS(msg);
        } else if (type == TrayIcon.MessageType.WARNING) {
            sendEMAIL(msg);
        }
    }

    public static void sendSMS(String str) {
    }

    public static void sendEMAIL(String str) {
    }
}
