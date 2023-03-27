import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

public class JUnitAssertSampleTest {

    /**
     * 检查对象是否为空，不为空报错
     */
    @Test
    public void testAssertNull() {
        Assertions.assertNull(null, "not null");
        Assert.notNull("", "is null");
    }

    /**
     * 检查对象是否不为空，为空报错
     */
    @Test
    public void testAssertNotNull() {
        assertNotNull("", "is null");
        Assert.isNull(null, "not null");
    }

    /**
     * 检查对象值是否相等，不相等报错
     */
    @Test
    public void testAssertEquals() {
        Assertions.assertEquals(1, 1);
    }

    /**
     * 检查条件是否为真，不为真报错
     */
    @Test
    public void testAssertTrue() {
        Assertions.assertTrue(true, "not true");
        Assert.isTrue(true, "not true");
    }

    /**
     * 检查条件是否为假，为真报错
     */
    @Test
    public void testAssertFalse() {
        Assertions.assertFalse(false, "is true");
    }

    /**
     * 检查对象引用是否相等，不相等报错
     */
    @Test
    public void testAssertSame() {
        Object o = new Object();
        Assertions.assertSame(o, o, "not same");
    }

    /**
     * 检查对象引用是否不等，相等报错
     */
    @Test
    public void testAssertNotSame() {
        Object o = new Object();
        Assertions.assertNotSame(o, new Object(), "same");
    }

    /**
     * 检查数组值是否相等，遍历比较，不相等报错
     */
    @Test
    public void testAssertArrayEquals() {

        String[] str = new String[]{};
        Assertions.assertArrayEquals(str, str, "not same");
    }


    /**
     * 重复测试
     */
    @RepeatedTest(5)
    void repeatTest(TestInfo testInfo, RepetitionInfo repetitionInfo) {

        System.out.println("repeat:" + testInfo.getDisplayName());
        System.out.println("这是第 " + repetitionInfo.getCurrentRepetition() + "次重复");

    }

    /**
     * 源的参数
     */
    @ParameterizedTest
    @ValueSource(strings = {"python", "go"})
    void containsChar(String candidate) {
        assertTrue(candidate.contains("o"));
    }

    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithEnumSource(TimeUnit timeUnit) {
        assertNotNull(timeUnit);
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = "^(M|N).+SECONDS$")
    void testWithEnumSourceRegex(TimeUnit timeUnit) {
        String name = timeUnit.name();
        assertTrue(name.startsWith("M") || name.startsWith("N"));
        assertTrue(name.endsWith("SECONDS"));
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithSimpleMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("foo", "bar");
    }



    @Test
//    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void failsIfExecutionTimeExceeds500Milliseconds() {
//        delaySecond(3);
//         assertTimeout(Duration.ofSeconds(5), () -> delaySecond(10)); // this will fail
        assertTimeout(Duration.ofSeconds(5), () -> delaySecond(1)); // pass

    }

    void delaySecond(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
