package com.broad.data;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class SplitterTest {

    @Test
    public void testSplitOnSplit(){

        List<String> result = Splitter.on("|").splitToList("Hello|World");

        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("Hello"));
    }

    @Test
    public void splitOnSplitEmitNullValues(){
        List<String> result = Splitter.on("|").splitToList("Hello|Word||This");

        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(4));

        List<String> resultOmitNull = Splitter.on("|").omitEmptyStrings().splitToList(" Hello|World | |");

        assertThat(resultOmitNull,notNullValue());
        assertThat(resultOmitNull.size(),equalTo(3));

        List<String> splitLimit = Splitter.fixedLength(2).on("|").splitToList("aabbcc");
        assertThat(splitLimit.size(),equalTo(3));

    }

    @Test
    public void testSplitterOnPattern(){

        List<String> result = Splitter.onPattern("\\|")
                .on("|").omitEmptyStrings().trimResults().splitToList(" Hello |World ||");
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("Hello"));
        assertThat(result.get(1),equalTo("World"));
    }

    @Test
    public void testSplitterOnPatternMap(){
        Map<String,String> result = Splitter.onPattern("\\|").on("|")
                .omitEmptyStrings().trimResults().withKeyValueSeparator("=").split("Hello=Wolrd|world=Hello|");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
    }


}
