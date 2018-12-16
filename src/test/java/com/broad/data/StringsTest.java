package com.broad.data;

import com.google.common.base.Strings;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class StringsTest {

    @Test
    public void testStringEmpty(){


        assertThat(Strings.emptyToNull(""), nullValue());

        assertThat(Strings.nullToEmpty(null),equalTo(""));

        assertThat(Strings.commonPrefix("Hi","hello"),equalTo(""));

        assertThat(Strings.commonSuffix("Do","go"),equalTo("o"));

        assertThat(Strings.repeat("66",2),equalTo("6666"));
    }
}
