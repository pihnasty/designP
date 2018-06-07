package com.aws.listeners;


import com.aws.LogMessage;
import com.aws.MessageType;
import com.aws.util.AnsiEscapeCodes;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Specialized class for writing into System.out
 */
public class ConsoleListener extends BaseListener {

    private AtomicBoolean colorizeOutput = new AtomicBoolean(false);

    public boolean getColorizeOutput() {
        return colorizeOutput.get();
    }

    public void setColorizeOutput(boolean colorizeOutput) {
        this.colorizeOutput.set(colorizeOutput);
    }

    public ConsoleListener() {
    }

    @Override
    public synchronized void writeImpl(LogMessage message) throws IOException {
        if (getColorizeOutput())
            System.out.print(getMessageColor(message.getMessageType()) + getMessageText(message) + AnsiEscapeCodes.RESET_FOREGROUND);
        else
            System.out.print(getMessageText(message));
        System.out.flush();
    }

    private static String getMessageColor(MessageType level){
        switch (level){
            default:
            case DEBUG:
                return AnsiEscapeCodes.SET_FOREGROUND_DARK_GRAY;
            case INFO:
                return AnsiEscapeCodes.RESET_FOREGROUND;
            case WARNING:
                return AnsiEscapeCodes.SET_FOREGROUND_DARK_YELLOW;
            case ERROR:
                return AnsiEscapeCodes.SET_FOREGROUND_DARK_RED;
            case CRITICAL:
                return AnsiEscapeCodes.SET_FOREGROUND_DARK_RED;
            case MANDATORY:
                return AnsiEscapeCodes.SET_FOREGROUND_LIGHT_BLUE;
        }
    }
}
