import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Zheyuan Gao
 * @author Cedric Fausey
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /*
     * Test cases for constructor
     */
    /**
     * Test for constructor small case.
     */
    @Test
    public void testConstructorSmall() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();

        assertEquals(sExpected, s);
    }

    /**
     * Test for constructor routine case.
     */
    @Test
    public void testConstructorRoutine1() {
        Set<String> s = this.createFromArgsTest("1");
        Set<String> sExpected = this.createFromArgsRef("1");

        assertEquals(sExpected, s);
    }

    /**
     * Test for constructor routine case.
     */
    @Test
    public void testConstructorRoutine2() {
        Set<String> s = this.createFromArgsTest("1", "A", "a",
                "abstract class");
        Set<String> sExpected = this.createFromArgsRef("1", "A", "a",
                "abstract class");

        assertEquals(sExpected, s);
    }

    /**
     * Test for constructor challenging case (Upper case letter sentence and
     * lower case letter sentence).
     */
    @Test
    public void testConstructorChanllenging() {
        Set<String> s = this.createFromArgsTest("I major in Computer Science.",
                "I MAJOR IN COMPUTER SCIENCE");
        Set<String> sExpected = this.createFromArgsRef(
                "I major in Computer Science.", "I MAJOR IN COMPUTER SCIENCE");

        assertEquals(sExpected, s);
    }

    /**
     * Test for constructor large case.
     */
    @Test
    public void testConstructorLarge() {
        Set<String> s = this.createFromArgsTest("apple", "beer", "chair",
                "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True");
        Set<String> sExpected = this.createFromArgsRef("apple", "beer", "chair",
                "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True");

        assertEquals(sExpected, s);
    }

    /*
     * Test cases for method add
     */
    /**
     * Test for Set method add small edge case.
     */
    @Test
    public void testAddSamll() {
        Set<String> s = this.createFromArgsTest();
        s.add("apple");
        Set<String> sExpected = this.createFromArgsRef();
        sExpected.add("apple");
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method add routine case.
     */
    @Test
    public void testAddRoutine() {
        Set<String> s = this.createFromArgsTest("beer");
        s.add("apple");
        Set<String> sExpected = this.createFromArgsRef("beer");
        sExpected.add("apple");
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method add routine case2.
     */
    @Test
    public void testAddRoutine2() {
        Set<String> s = this.createFromArgsTest("beer", "good");
        s.add("apple");
        Set<String> sExpected = this.createFromArgsRef("beer", "good");
        sExpected.add("apple");
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method add challenge case by calling add twice.
     */
    @Test
    public void testAddChallenge() {
        Set<String> s = this.createFromArgsTest("beer");
        s.add("apple");
        s.add("peach");
        Set<String> sExpected = this.createFromArgsRef("beer");
        sExpected.add("apple");
        sExpected.add("peach");
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method add large case.
     */
    @Test
    public void testAddLarge() {
        Set<String> s = this.createFromArgsTest("beer", "simple", "hard",
                "CSE 2231", "sad", "assignments", "projects", "The Boys",
                "Test", "1", "A", "-", "a");
        s.add("apple");
        Set<String> sExpected = this.createFromArgsRef("beer", "simple", "hard",
                "CSE 2231", "sad", "assignments", "projects", "The Boys",
                "Test", "1", "A", "-", "a");
        sExpected.add("apple");
        assertEquals(sExpected, s);

    }

    /*
     * Test cases for set method remove
     */
    /**
     * Test for Set method remove small edge case.
     */
    @Test
    public void testRemoveSamll() {
        Set<String> s = this.createFromArgsTest("apple");
        String str = s.remove("apple");
        Set<String> sExpected = this.createFromArgsRef("apple");
        String strExpected = sExpected.remove("apple");
        assertEquals(strExpected, str);
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method remove routine case.
     */
    @Test
    public void testRemoveRoutine() {
        Set<String> s = this.createFromArgsTest("apple", "why");
        String str = s.remove("apple");
        Set<String> sExpected = this.createFromArgsRef("apple", "why");
        String strExpected = sExpected.remove("apple");
        assertEquals(strExpected, str);
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method remove second routine case.
     */
    @Test
    public void testRemoveRoutine2() {
        Set<String> s = this.createFromArgsTest("apple", "exciting", "amazing");
        String str = s.remove("apple");
        Set<String> sExpected = this.createFromArgsRef("apple", "exciting",
                "amazing");
        String strExpected = sExpected.remove("apple");
        assertEquals(strExpected, str);
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method remove challenging case.
     */
    @Test
    public void testRemoveChallenge() {
        Set<String> s = this.createFromArgsTest("apple", "exciting", "amazing");
        String str1 = s.remove("apple");
        String str2 = s.remove("exciting");
        Set<String> sExpected = this.createFromArgsRef("apple", "exciting",
                "amazing");
        String str1Expected = sExpected.remove("apple");
        String str2Expected = sExpected.remove("exciting");
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method remove large case.
     */
    @Test
    public void testRemoveLarge() {
        Set<String> s = this.createFromArgsTest("apple", "exciting", "amazing",
                "good", "fine", "alraight", "implication", "The Boys", "Test",
                "1", "A", "-", "a");
        String str1 = s.remove("apple");
        String str2 = s.remove("implication");
        Set<String> sExpected = this.createFromArgsRef("apple", "exciting",
                "amazing", "good", "fine", "alraight", "implication",
                "The Boys", "Test", "1", "A", "-", "a");
        String str1Expected = sExpected.remove("apple");
        String str2Expected = sExpected.remove("implication");
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(sExpected, s);

    }

    /*
     * Test cases for set method removeAny
     */
    /**
     * Test for removeAny small case.
     */
    @Test
    public void testRemoveAnySmall() {
        Set<String> s = this.createFromArgsTest("apple");
        s.removeAny();
        Set<String> sExpected = this.createFromArgsRef();
        assertEquals(sExpected, s);
    }

    /**
     * Test for removeAny routine case.
     */
    @Test
    public void testRemoveAnyRoutine() {
        Set<String> s = this.createFromArgsTest("apple", "1");
        String remove = s.removeAny();
        Set<String> sExpected = this.createFromArgsRef("apple", "1");
        sExpected.remove(remove);
        assertEquals(sExpected, s);
    }

    /**
     * Test for removeAny routine case.
     */
    @Test
    public void testRemoveAnyRoutine2() {
        Set<String> s = this.createFromArgsTest("apple", "1", "?",
                "interesting");
        String remove = s.removeAny();
        Set<String> sExpected = this.createFromArgsRef("apple", "1", "?",
                "interesting");
        sExpected.remove(remove);
        assertEquals(sExpected, s);
    }

    /**
     * Test for removeAny challenge case.
     */
    @Test
    public void testRemoveAnyChallenge() {
        Set<String> s = this.createFromArgsTest("apple", "1", "?",
                "interesting");
        String remove1 = s.removeAny();
        String remove2 = s.removeAny();
        Set<String> sExpected = this.createFromArgsRef("apple", "1", "?",
                "interesting");
        sExpected.remove(remove1);
        sExpected.remove(remove2);
        assertEquals(sExpected, s);
    }

    /**
     * Test for removeAny large case.
     */
    @Test
    public void testRemoveAnyLarge() {
        Set<String> s = this.createFromArgsTest("apple", "1", "?",
                "interesting", "3", "5", "ah", "emmmmmm", "OHIO", "diamond");
        String remove = s.removeAny();
        Set<String> sExpected = this.createFromArgsRef("apple", "1", "?",
                "interesting", "3", "5", "ah", "emmmmmm", "OHIO", "diamond");
        sExpected.remove(remove);
        assertEquals(sExpected, s);
    }

    /*
     * Test cases for set method contains
     */

    /**
     * Test for Set method contains small case.
     */
    @Test
    public void testContainsSmall() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        Boolean c = s.contains("a");
        Boolean cExpected = sExpected.contains("a");
        assertEquals(cExpected, c);
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method contains routine case.
     */
    @Test
    public void testContainsRoutine1() {
        Set<String> s = this.createFromArgsTest("apple");
        boolean c = s.contains("apple");
        Set<String> sExpected = this.createFromArgsRef("apple");
        boolean cExpected = sExpected.contains("apple");
        assertEquals(cExpected, c);
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method contains routine case.
     */
    @Test
    public void testContainsRoutine2() {
        Set<String> s = this.createFromArgsTest("pear");
        boolean c = s.contains("apple");
        Set<String> sExpected = this.createFromArgsRef("pear");
        boolean cExpected = sExpected.contains("apple");
        assertEquals(cExpected, c);
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method contains routine case.
     */
    @Test
    public void testContainsRoutine3() {
        Set<String> s = this.createFromArgsTest("apple", "beer", "chair",
                "door", "electron", "friend");
        boolean c = s.contains("electron");
        Set<String> sExpected = this.createFromArgsRef("apple", "beer", "chair",
                "door", "electron", "friend");
        boolean cExpected = sExpected.contains("electron");
        assertEquals(cExpected, c);
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method contains second routine case.
     */
    @Test
    public void testContainsRoutine4() {
        Set<String> s = this.createFromArgsTest("apple", "beer", "chair",
                "door", "electron", "friend");
        boolean c = s.contains("Ultimate answer of the universe");
        Set<String> sExpected = this.createFromArgsRef("apple", "beer", "chair",
                "door", "electron", "friend");
        boolean cExpected = sExpected
                .contains("Ultimate answer of the universe");
        assertEquals(cExpected, c);
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method contains challenge case.
     */
    @Test
    public void testContainsChallenge() {
        Set<String> s = this.createFromArgsTest("apple", "beer", "chair",
                "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True");
        boolean c = s.contains("Ultimate answer of the universe");
        Set<String> sExpected = this.createFromArgsRef("apple", "beer", "chair",
                "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True");
        boolean cExpected = sExpected
                .contains("Ultimate answer of the universe");
        assertEquals(cExpected, c);
        assertEquals(sExpected, s);

    }

    /*
     * Test cases for set methods size
     */
    /**
     * Test for Set method size small edge case.
     */
    @Test
    public void testSizeSmall() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method size routine case.
     */
    @Test
    public void testSizeRoutine1() {
        Set<String> s = this.createFromArgsTest("apple");
        Set<String> sExpected = this.createFromArgsRef("apple");
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method size routine case.
     */
    @Test
    public void testSizeRoutine2() {
        Set<String> s = this.createFromArgsTest("apple", "beer", "chair");
        Set<String> sExpected = this.createFromArgsRef("apple", "beer",
                "chair");
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method size routine case.
     */
    @Test
    public void testSizeRoutine3() {
        Set<String> s = this.createFromArgsTest("apple", "beer", "chair",
                "door", "electron", "friend");
        Set<String> sExpected = this.createFromArgsRef("apple", "beer", "chair",
                "door", "electron", "friend");
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method size challenge case.
     */
    @Test
    public void testSizeChallenge() {
        Set<String> s = this.createFromArgsTest("1", "2", "4,", "34", "game",
                "video", "animation", "comic", "best");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "4,", "34",
                "game", "video", "animation", "comic", "best");
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);

    }

    /**
     * Test for Set method size large case.
     */
    @Test
    public void testSizeLarge() {
        Set<String> s = this.createFromArgsTest("apple", "beer", "chair",
                "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True");
        Set<String> sExpected = this.createFromArgsRef("apple", "beer", "chair",
                "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True");
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);

    }

}
