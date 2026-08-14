package com.shatteredpixel.shatteredpixeldungeon.ap;

import com.shatteredpixel.shatteredpixeldungeon.ui.APChatbox;
import com.watabou.utils.Signal;

public class APChat {

    public static final int MAX_LINES = 500;

    private static String[] messages = new String[MAX_LINES];
    private static int size = 0;
    private static int front = 0;
    private static int back = 0;

    public static Signal<String> signal = new Signal<>();

    public static APChatbox createChatbox() {

        String[] msgs = new String[size];
        for (int i=0; i<=size-1; i++) {
            msgs[i] = getMessage(i);
        }

        return new APChatbox(msgs);
    }

    private static void addToFront(String text) {
        messages[front] = text;

        front = (front+1) % MAX_LINES;

        if (size < MAX_LINES) {
            size++;
        } else {
            back = (back+1) % MAX_LINES;
        }
    }

    public static String[] getAllMessages() {
        return messages;
    }

    public static String getMessage(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return messages[ (back + index) % MAX_LINES ];
    }

    public static void i( String text ) {

        //check if the line is longer than max?

        System.out.println("Broadcasting...");

        addToFront(text);

        signal.dispatch(text);
    }
}
