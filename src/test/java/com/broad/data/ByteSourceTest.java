package com.broad.data;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ByteSourceTest {


    private static final String FILE_SOURCE = "/home/broad/project/cache-around/src/test/resources/source.txt";

    @Test
    public void testAsByteSource() throws IOException {

        File file = new File(FILE_SOURCE);
        ByteSource source = Files.asByteSource(file);

        byte[] bytes = source.read();

        assertThat(bytes, is(Files.toByteArray(file)));

    }


    @Test
    public void testSliceByteSource() throws IOException {

        ByteSource byteSource = ByteSource.wrap(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09});

        ByteSource slice = byteSource.slice(5, 5);
        byte[] result = slice.read();
        for(byte b:result){
            System.out.println(
                    b
            );
        }
    }

}
