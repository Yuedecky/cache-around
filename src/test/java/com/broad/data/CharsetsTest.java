package com.broad.data;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CharsetsTest {


    @Test
    public void testCharsets(){
        Charset charset = Charset.forName("UTF-8");
        assertThat(charset, equalTo(Charsets.UTF_8));
    }

    @Test
    public void testCharMatcher(){
        assertThat(CharMatcher.javaDigit().matches('5'),equalTo(true));
        assertThat(CharMatcher.javaDigit().matches('X'),equalTo(false));
    }
}
