package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResultTests {

    final static Integer okValue = 1;
    final static String ErrValue = "error";

    @Test
    void testIsOk() {
        Result<Integer, String> result1 = Result.ok(okValue);
        Result<Integer, String> result2 = Result.err(ErrValue);

        Assertions.assertTrue(result1.isOk());
        Assertions.assertFalse(result2.isOk());
    }

    @Test
    void testIsErr() {
        Result<Integer, String> result1 = Result.ok(okValue);
        Result<Integer, String> result2 = Result.err(ErrValue);

        Assertions.assertFalse(result1.isErr());
        Assertions.assertTrue(result2.isErr());
    }

    @Test
    void testUnwrap() {
        Result<Integer, String> result1 = Result.ok(okValue);
        Result<Integer, String> result2 = Result.err(ErrValue);

        Assertions.assertEquals(1, result1.unwrap());
        Assertions.assertEquals("error", result2.unwrap());
    }

}
