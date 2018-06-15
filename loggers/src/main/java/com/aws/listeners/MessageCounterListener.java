package com.aws.listeners;

import com.amazon.diagnostics.logger.LogListener;
import com.amazon.diagnostics.logger.LogMessage;
import com.amazon.diagnostics.logger.MessageType;
import java.util.EnumMap;

/**
 * TODO class description
 */
public class MessageCounterListener implements LogListener {
    private EnumMap<MessageType, Integer> counters = new EnumMap<>(MessageType.class);

    public synchronized int getCount(MessageType ofType){
        Integer count = counters.get(ofType);
        return count == null ? 0 : count;
    }

    public void reset(){
        counters.clear();
    }

    @Override
    public synchronized void write(LogMessage message) throws Exception {
        MessageType level = message.getMessageType();
        counters.put(level, getCount(level) + 1);
    }

    @Override public void idle() throws Exception {

    }
}
