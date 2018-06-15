package com.aws.listeners;

import com.amazon.diagnostics.logger.LogMessage;
import com.amazon.diagnostics.logger.listeners.BaseListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Specialized class for writing into Writer.
 */
public class WriterListener extends BaseListener implements AutoCloseable {
    private Writer writer;

    public WriterListener(Path filePath) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath.toFile()), "utf-8"));
    }

    public WriterListener(Writer writer) throws IOException {
        Objects.requireNonNull(writer);
        this.writer = writer;
    }

    @Override
    protected void writeImpl(LogMessage message) throws IOException {
        writer.write(getMessageText(message));
        writer.flush();
    }

    public Writer getWriter() {
        return writer;
    }

    @Override
    public void close() throws Exception {
        super.close();

        if (writer != null) {
            writer.flush();
            writer.close();
            writer = null;
        }
    }
}
