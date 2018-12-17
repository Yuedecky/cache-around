package com.broad.data;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.ByteSink;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FilesTest {


    private static final String SOURCE_FILE = "/home/broad/projects/qf/cache-around/src/test/resources/source.txt";


    private static final String TARGET_FILE = "/home/broad/projects/qf/cache-around/src/test/resources/target.txt";

    @Test
    public void copyFileWithGuava() throws IOException {
        File file = new File(TARGET_FILE);
        Files.copy(new File(SOURCE_FILE), file);
        assertThat(file.exists(), equalTo(true));
    }


    @Test
    public void copyFileWithJDKNio() throws IOException {
        java.nio.file.Files.copy(
                Paths.get("/home/broad/projects/qf/cache-around/src/test/resources/", "io", "source.txt"),
                Paths.get("/home/broad/projects/qf/cache-around/src/test/resources/", "io", "target.txt"),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void testMoveFile() throws IOException {
        File sourceFile = new File("/home/broad/projects/qf/cache-around/src/test/resources/s.txt");
        if (!sourceFile.exists()) {
            sourceFile.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(sourceFile);
        outputStream.write("ddddddddd".getBytes());
        File targetFile = new File("/home/broad/projects/qf/cache-around/src/test/resources/t.txt");
        Files.move(sourceFile, targetFile);
        assertThat(targetFile.exists(), equalTo(true));
        assertThat(sourceFile.exists(), equalTo(false));


    }

    @Test
    public void testFileToString() throws IOException {
        final String exportString = "Hello ,Linux Mint 19.1 is a long term support release which will be supported until 2023. It comes with updated software and brings refinements and many new features to make your desktop even more comfortable to use.\n" +
                "New features:\n" +
                "This new version of Linux Mint contains many improvements.\n" +
                "For an overview of the new features please visit: http://www.baidu.com";
        List<String> stringList = Files.readLines(new File(SOURCE_FILE), Charsets.UTF_8);
        String result = Joiner.on("\n").join(stringList);
        assertThat(result, equalTo(exportString));
    }

    @After
    public void tearDown() {
        File targetFile = new File(TARGET_FILE);
        if (targetFile.exists()) {
            targetFile.delete();
        }
    }


    @Test
    public void writeFileToByteSink() throws IOException {
        String expectedValue = "Hello World";
        File file = new File("test.txt");
        ByteSink sink = Files.asByteSink(file);
        sink.write(expectedValue.getBytes());
        String result = Files.toString(file, Charsets.UTF_8);
        assertEquals(expectedValue, result);
    }


    @Test
    public void testReadFileUsingCharSource() throws IOException {
        String content = "Hello World";
        File file = new File("test.txt");
        CharSource charSource = Files.asCharSource(file, Charsets.UTF_8);
        assertEquals(content, charSource.read());

    }


    @Test
    public void testReadFromCharStreams() throws IOException {
        String expectedValue = "Hello World";
        FileReader reader = new FileReader("test.txt");
        String result = CharStreams.toString(reader);
        assertEquals(result, expectedValue);
        reader.close();
    }


}
