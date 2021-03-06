/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich@weltraumschaf.de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 */
package org.jenkinsci.plugins.darcs;

import junit.framework.TestCase;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sven Strittmatter <ich@weltraumschaf.de>
 */
public class DarcsChangeLogParserTest extends TestCase {

    public DarcsChangeLogParserTest(String name) {
        super(name);
    }

    public void testParse() {
        DarcsChangeLogParser sut = new DarcsChangeLogParser();
        DarcsChangeSetList list = null;

        try {
            URL resource = getClass().getResource("/changes.xml");
            list = sut.parse(null, new File(resource.toURI()));
        } catch (Exception e) {
            fail(e.toString());
        }

        if (null == list) {
            fail("list must not be null!");
        }

        assertEquals(8, list.size());
        List<DarcsChangeSet> logs = list.getChangeSets();
        assertEquals(8, logs.size());

        assertPatch(logs.get(0), false, new HashMap<String, String>() {

            {
                put("plainAuthor", "ich@weltraumschaf.de");
                put("name", "Implemented toString()");
                put("date", "20110214203531");
                put("localDate", "Mon Feb 14 21:35:31 CET 2011");
                put("hash", "20110214203531-7677a-1b935a82ba6408ffa9add3642cb52f233ff4ef54.gz");
                put("comment", "Ignore-this: 7c0271b552e03728baa7d4f33cb545f9");
            }
        });
        assertSummary(logs.get(0), null, null, new ArrayList<String>() {

            {
                add("Bar.java");
                add("Baz.java");
                add("Foo.java");
            }
        });
        assertPatch(logs.get(1), false, new HashMap<String, String>() {

            {
                put("plainAuthor", "ich@weltraumschaf.de");
                put("name", "Implemented value in class Foo");
                put("date", "20110214203417");
                put("localDate", "Mon Feb 14 21:34:17 CET 2011");
                put("hash", "20110214203417-7677a-261f33e2608d68f088f15b077f7dcde2cc18a4b7.gz");
                put("comment", "Ignore-this: 79225cd08e4f7ec7dfc7a6cb4e7f5948");
            }
        });
        assertSummary(logs.get(1), null, null, new ArrayList<String>() {

            {
                add("Foo.java");
            }
        });
        assertPatch(logs.get(2), false, new HashMap<String, String>() {

            {
                put("plainAuthor", "ich@weltraumschaf.de");
                put("name", "Implemented value in class Baz");
                put("date", "20110214203402");
                put("localDate", "Mon Feb 14 21:34:02 CET 2011");
                put("hash", "20110214203402-7677a-5eb558d8bd3df5b8edfa005479d8ff1e8139abe0.gz");
                put("comment", "Ignore-this: fd9e1a81cc792fd826a128794e92ba64");
            }
        });
        assertSummary(logs.get(2), null, null, new ArrayList<String>() {

            {
                add("Baz.java");
            }
        });
        assertPatch(logs.get(3), false, new HashMap<String, String>() {

            {
                put("plainAuthor", "ich@weltraumschaf.de");
                put("name", "Implemented value in class Bar");
                put("date", "20110214203334");
                put("localDate", "Mon Feb 14 21:33:34 CET 2011");
                put("hash", "20110214203334-7677a-3f9e4a67068618fcfde454c0c097d1f8b96301df.gz");
                put("comment", "Ignore-this: 40e46e42b5023572d7e45607df47cab1");
            }
        });
        assertSummary(logs.get(3), null, null, new ArrayList() {

            {
                add("Bar.java");
            }
        });
        assertPatch(logs.get(4), false, new HashMap<String, String>() {

            {
                put("plainAuthor", "ich@weltraumschaf.de");
                put("name", "Implemented class Foo");
                put("date", "20110214201649");
                put("localDate", "Mon Feb 14 21:16:49 CET 2011");
                put("hash", "20110214201649-7677a-9c3c62c42467fe20e75a9ab62e52441ef7cdc8ba.gz");
                put("comment", "Ignore-this: 85d0cdf2679dfcab72469629d6a80945");
            }
        });
        assertSummary(logs.get(4), null, null, new ArrayList() {

            {
                add("Foo.java");
            }
        });
        assertPatch(logs.get(5), true, new HashMap<String, String>() {

            {
                put("plainAuthor", "ich@weltraumschaf.de");
                put("name", "Implemented class Baz");
                put("date", "20110214201618");
                put("localDate", "Mon Feb 14 21:16:18 CET 2011");
                put("hash", "20110214201618-7677a-631eee269c4a252d953fa2f61b1127874bbc57d4.gz");
                put("comment", "Ignore-this: 751c6c8a858104b5b15833496fce9a2");
            }
        });
        assertSummary(logs.get(5), null, null, new ArrayList() {

            {
                add("Baz.java");
            }
        });
        assertPatch(logs.get(6), false, new HashMap<String, String>() {

            {
                put("plainAuthor", "ich@weltraumschaf.de");
                put("name", "Implemented class Bar");
                put("date", "20110214201544");
                put("localDate", "Mon Feb 14 21:15:44 CET 2011");
                put("hash", "20110214201544-7677a-1cd5bbbc18b5e78240a80c9d1576faaa63c9f7d7.gz");
                put("comment", "Ignore-this: e2eb7de380585ad9e4cb9515d8b21621");
            }
        });
        assertSummary(logs.get(6), null, null, new ArrayList() {

            {
                add("Bar.java");
            }
        });
        assertPatch(logs.get(7), true, new HashMap<String, String>() {

            {
                put("plainAuthor", "ich@weltraumschaf.de");
                put("name", "inital files added");
                put("date", "20110214201356");
                put("localDate", "Mon Feb 14 21:13:56 CET 2011");
                put("hash", "20110214201356-7677a-15b1d7313611ef85de46d8daf57123a365d5b800.gz");
                put("comment", "Ignore-this: 391a1ff64b0f64546446368f2d45fbc8");
            }
        });
        assertSummary(logs.get(7), new ArrayList() {

            {
                add("Bar.java");
                add("Baz.java");
                add("Foo.java");
            }
        }, null, null);
    }

    private void assertPatch(DarcsChangeSet cs, boolean isInverted, Map<String, String> expected) {
        assertEquals(expected.get("plainAuthor"), cs.getPlainAuthor());
        assertEquals(expected.get("name"), cs.getName());
        assertEquals(expected.get("comment"), cs.getComment());
        assertEquals(expected.get("date"), cs.getDate());
        assertEquals(expected.get("localDate"), cs.getLocalDate());
        assertEquals(expected.get("hash"), cs.getHash());
        assertEquals(isInverted, cs.isInverted());
    }

    private void assertSummary(DarcsChangeSet cs, List added, List deleted, List modified) {
        if (null != added) {
            assertTrue("added files does not match", cs.getAddedPaths().equals(added));
        }

        if (null != deleted) {
            assertTrue("deleted files does not match", cs.getDeletedPaths().equals(deleted));
        }

        if (null != modified) {
            assertTrue("modified files does not match", cs.getModifiedPaths().equals(modified));
        }
    }
}
