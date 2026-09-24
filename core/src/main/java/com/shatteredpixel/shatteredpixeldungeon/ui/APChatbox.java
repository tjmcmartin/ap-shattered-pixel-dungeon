package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.ap.APChat;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.Signal;

import java.util.ArrayList;

public class APChatbox extends Component {

    private Chat chat;
    private ScrollPane pane;
    private ArrayList<String> msgsToAdd = new ArrayList<>();

    public APChatbox(String[] text) {
        super();
        createChildren();

        chat = new Chat(text);
        APChat.signal.add(chat);

        pane = new ScrollPane(chat);
        add(pane);
    }

    @Override
    protected void layout() {

        pane.setRect(0, 0, width, height);
        pane.layout();

        chat.layout();

    }

    private class Chat extends Component implements Signal.Listener<String> {

        private ArrayList<String> messages = new ArrayList<>();

        private Chat(String[] text) {
            super();

            for (String msg : text) {
                messages.add(msg);
                add( PixelScene.renderTextBlock(msg, 6) );
            }

        }

        @Override
        public boolean onSignal(String text) {

            synchronized (msgsToAdd) {
                msgsToAdd.add(text);
            }

            return false;
        }

        @Override
        protected void layout() {
            float curY = 0;
            for (int i=length-1; i>=0; i--) {
                RenderedTextBlock entry = (RenderedTextBlock) members.get(i);
                entry.setHightlighting(false);
                entry.maxWidth((int) width);
                entry.setPos(0, curY);
                curY += entry.height()+2;
            }

            height = curY;

        }

        @Override
        public synchronized void update() {
            synchronized (msgsToAdd) {
                for (String msg : msgsToAdd) {

                    System.out.println("Signal received: \""+msg+"\"");

                    messages.add(msg);
                    add( PixelScene.renderTextBlock( msg, 6 )  );

                    if (length > APChat.MAX_LINES) {
                        RenderedTextBlock oldMsg = (RenderedTextBlock) members.get(0);
                        remove(oldMsg);
                        oldMsg.destroy();

                        messages.remove(0);

                    }
                }

                layout();

                msgsToAdd.clear();

            }
            super.update();
        }
    }
}
