package com.aws.util;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileOutputStream supporting file size measurement.
 */
public class SizeMeasuringFileOutputStream extends FileOutputStream {
    private long size;


    public SizeMeasuringFileOutputStream(String name) throws FileNotFoundException {
        this(new File(name), false);
    }

    public SizeMeasuringFileOutputStream(String name, boolean append) throws FileNotFoundException {
        this(new File(name), append);
    }

    public SizeMeasuringFileOutputStream(File file) throws FileNotFoundException {
        this(file, false);
    }

    public SizeMeasuringFileOutputStream(File file, boolean append) throws FileNotFoundException {
        super(file, append);
        if(append)
            size = file.length();
    }

    public SizeMeasuringFileOutputStream(FileDescriptor fdObj) {
        super(fdObj);
    }

    /**
     * Getter for property 'size'.
     *
     * @return Value for property 'size'.
     */
    public long getSize() {
        return size;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b);
        size++;
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
        size += b.length;
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
        size += len;
    }


}
