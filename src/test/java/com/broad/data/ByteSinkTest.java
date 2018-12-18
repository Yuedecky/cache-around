package com.broad.data;

import com.google.common.hash.Hashing;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ByteSinkTest {


    private static final String BIN_FILE_SOURCE = "/home/broad/project/cache-around/src/test/resources/bin.png";

    @Test
    public void testByteSink() throws IOException {
        File file = new File(BIN_FILE_SOURCE);

        file.deleteOnExit();

        ByteSink byteSink = Files.asByteSink(file);
        byte[] bytes = new byte[]{0x01, 0x02};
        byteSink.write(bytes);

        byte[] expected = Files.toByteArray(file);

        assertThat(expected, is(bytes));
    }


    @Test
    public void testFromSourceToSink() throws IOException {
        String source = "/home/broad/project/cache-around/src/test/resources/source.txt";

        String target = "/home/broad/project/cache-around/src/test/resources/target.txt";

        File sourceFile = new File(source);
        File targetFile = new File(target);
        targetFile.deleteOnExit();

        ByteSource byteSource = Files.asByteSource(sourceFile);

        byteSource.copyTo(Files.asByteSink(targetFile));
        assertThat(targetFile.exists(), equalTo(true));

        String sourceHash = Files.asByteSource(sourceFile).hash(Hashing.sha256()).toString();

        String targetHash = Files.asByteSource(targetFile).hash(Hashing.sha256()).toString();
        assertThat(sourceHash, equalTo(targetHash));

    }
}
