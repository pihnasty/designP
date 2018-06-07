package com.aws;

import com.amazon.diagnostics.logger.MessageType;
import com.amazon.diagnostics.statistics.AsyncTimeCounterSet;
import com.amazon.diagnostics.statistics.TimeCounterSet;
import java.util.Stack;


/**
 * TODO class description
 */
public class MultiTimeCounterSet implements TimeCounterSet {
    private final Stack<TimeCounterSet> frames = new Stack<>();

    public void pushFrame(String frameName){
        frames.push(new AsyncTimeCounterSet(frameName));
    }

    public void popFrame(MessageType dumpMessageLevel){
        TimeCounterSet timers = frames.pop();
        timers.dump(dumpMessageLevel);
    }

    @Override
    public void startCounter(String name) {
        for(TimeCounterSet frame : frames){
            frame.startCounter(name);
        }
    }

    @Override
    public void stopCounter(String name) {
        for(TimeCounterSet frame : frames){
            frame.stopCounter(name);
        }
    }

    @Override
    public void dump(MessageType messageLevel) {
        for(TimeCounterSet frame : frames){
            frame.dump(messageLevel);
        }
    }

    @Override
    public long incrementCounter(String counterName, long nanoTime) {
        for(TimeCounterSet frame : frames){
            frame.incrementCounter(counterName, nanoTime);
        }
        return -1;
    }

    @Override
    public void reset() {
        frames.clear();
    }
}
