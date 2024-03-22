package fr.rouen.mastergil.tptest.bonus;

import jdk.jfr.internal.Utils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class StringManipulationUnitTest {

    @Test
    public void shouldReturnFalseBecauseStringIsNull() {
        // GIVEN
        String s = null;
        boolean result;
        // WHEN
        result = StringManipulation.a(s);
        // THEN
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseBecauseStringIsOfLengthZero() {
        // GIVEN
        String s = "";
        boolean result;
        // WHEN
        result = StringManipulation.a(s);
        // THEN
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseBecauseStringIsNotPalindrome() {
        // GIVEN
        String s = "ba";
        boolean result;
        // WHEN
        result = StringManipulation.a(s);
        // THEN
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueBecauseStringIsPalindrome() {
        // GIVEN
        String s = "aziza";
        boolean result;
        // WHEN
        result = StringManipulation.a(s);
        // THEN
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturn0ForSubSequenceLength() {
        // GIVEN
        String s = "";
        int result;
        StringManipulation manipulation = new StringManipulation();
        // WHEN
        result = manipulation.b(s);
        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldReturn3ForSubSequenceLength() {
        // GIVEN
        String s = "abca";
        int result;
        StringManipulation manipulation = new StringManipulation();
        // WHEN
        result = manipulation.b(s);
        // THEN
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldReturnEmptyChainBecauseNoChain() {
        // GIVEN
        String[] strings = new String[]{};
        StringManipulation manipulation = new StringManipulation();
        String expected = "";
        String result;
        // WHEN
        result = manipulation.c(strings);
        // THEN
        assertThat(result).matches(expected);
    }

    @Test
    public void shouldReturnEmptyChainBecauseStartWithDiffLetters() {
        // GIVEN
        String s = "Prefix";
        String s2 = "Euh";
        String[] strings = new String[]{s, s2};
        StringManipulation manipulation = new StringManipulation();
        String expected = "";
        String result;
        // WHEN
        result = manipulation.c(strings);
        // THEN
        assertThat(result).matches(expected);
    }

    @Test
    public void shouldReturnPrefix() {
        // GIVEN
        String s = "Prefix";
        String s2 = "Pret";
        String[] strings = new String[]{s, s2};
        StringManipulation manipulation = new StringManipulation();
        String expected = "Pre";
        String result;
        // WHEN
        result = manipulation.c(strings);
        // THEN
        assertThat(result).matches(expected);
    }

    @Test
    public void shouldReturnOne() {
        // GIVEN
        String s = "abc";
        String s2 = "ab";
        StringManipulation manipulation = new StringManipulation();
        int expected = 1;
        int result;
        // THEN
        result = manipulation.d(s, s2);
        // THEN
        assertThat(result).isEqualTo(expected);
    }

}