package com.broad.data;

import com.google.common.base.Preconditions;
import org.junit.Test;

public class PreconditionTest {

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull(){
        Preconditions.checkNotNull(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkArguement(){
        Preconditions.checkArgument("A".equals("B"));
    }
}
