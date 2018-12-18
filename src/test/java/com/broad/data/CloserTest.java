package com.broad.data;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CloserTest {

    @Test
    public void testCloser() throws IOException {
        ByteSource byteSource = Files.asByteSource(new File("/home/broad/project/cache-around/src/test/resources/source.txt"));
        Closer closer = Closer.create();
        try (InputStream inputStream = closer.register(byteSource.openStream())) {

        } catch (Throwable e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }

    }


    @Test
    public void testTryFinally() {
        Throwable t = null;
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            t = e;
            throw e;
        } finally {
            try {
                throw new RuntimeException("3");
            } catch (Exception e) {
                t.addSuppressed(e);
            }
        }
    }
}
