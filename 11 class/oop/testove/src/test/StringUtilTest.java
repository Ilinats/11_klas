package test;

import main.StringUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void testIsEmpty() {
        StringUtil stringUtil = new StringUtil();
        assertTrue(stringUtil.isEmpty(null));
        assertTrue(stringUtil.isEmpty(""));
        assertTrue(stringUtil.isEmpty("   "));
        assertFalse(stringUtil.isEmpty("Hello"));
    }

    @Test
    public void testReverse() {
        StringUtil stringUtil = new StringUtil();
        assertNull(stringUtil.reverse(null));
        assertEquals("", stringUtil.reverse(""));
        assertEquals("olleH", stringUtil.reverse("Hello"));
        assertEquals("   ", stringUtil.reverse("   "));
    }
}
