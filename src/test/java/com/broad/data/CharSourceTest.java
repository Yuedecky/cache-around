package com.broad.data;

import com.google.common.collect.ImmutableList;
import com.google.common.io.CharSource;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharSourceTest {


    @Test
    public void testCharSourceWrap() throws IOException {

        CharSource source = CharSource.wrap("9999ddd");
        String read = source.read();
        assertThat(read, equalTo("9999ddd"));
        ImmutableList<String> immutableList = source.readLines();

        assertThat(immutableList.size(), equalTo(1));
        assertThat(source.length(), equalTo(7L));
        assertThat(source.lengthIfKnown().get(), equalTo(7L));


    }

    @Test
    public void testCharSourceConcat() throws IOException {
        CharSource source = CharSource.concat(CharSource.wrap("test\n"), CharSource.wrap("4444"));
        Reader reader = source.openStream();
        reader.skip(2);
        assertThat(reader.read(),equalTo(115));
        assertThat(source.length(), equalTo(9L));
        assertThat(source.readLines().size(), equalTo(2));
    }


}
