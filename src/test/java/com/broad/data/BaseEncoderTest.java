package com.broad.data;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

public class BaseEncoderTest {


    @Test
    public void testBaseEncoder() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(baseEncoding.encode("Hello".getBytes()));
    }


    @Test
    public void testBaseDecoder() {
        BaseEncoding baseEncoding = BaseEncoding.base64();

        System.out.println(new String(baseEncoding.decode("SGVsbG8=")));
    }
}
