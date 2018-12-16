package com.broad.data;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private String target = "/home/broad/Desktop/test.txt";

    private List<String> stringListWithNullValues = Arrays.asList(
            "Google", "Guava", "Java", null
    );

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void testJoinerOnAppendToStringBuilder() {

        try(FileWriter writer = new FileWriter(new File(target))){

            Joiner.on("#").appendTo(writer,stringListWithNullValues);
            assertTrue(Files.isFile().test(new File(target)));
        }catch (Exception e){
            fail("writer occur fetal error.");
        }

        StringBuilder builder = new StringBuilder();
        StringBuilder resultBuilder = Joiner.on("#").useForNull("DEFAULT").appendTo(builder, stringListWithNullValues);
        assertSame(builder,resultBuilder);
        assertTrue(resultBuilder.equals(""));
    }
}
