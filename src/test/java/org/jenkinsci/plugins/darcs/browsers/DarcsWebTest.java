/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich@weltraumschaf.de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 */

package org.jenkinsci.plugins.darcs.browsers;

import org.jenkinsci.plugins.darcs.DarcsChangeSet;

import junit.framework.TestCase;
import org.junit.Ignore;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;

/**
 *
 * @author Sven Strittmatter <ich@weltraumschaf.de>
 */
public class DarcsWebTest extends TestCase {
    
    private static final String URL  = "http://www.foobar.com/";
    private static final String REPO = "arepo";

    public class ExposingDarcsWeb extends DarcsWeb {
        public ExposingDarcsWeb() throws MalformedURLException {
            super(new URL(URL), REPO);
        }

        public DarcsQueryBuilder exposedCreateDefaultQuery() {
            return createDefaultQuery();
        }

        public DarcsQueryBuilder exposedCreateDefaultQuery(String action) {
            return createDefaultQuery(action);
        }
    }

    public DarcsWebTest(String testName) {
        super(testName);
    }

    public void testCreateDefaultQuery() {
        try {
            ExposingDarcsWeb sut = new ExposingDarcsWeb();
            assertEquals("Query",
                         "?r=" + sut.repo,
                         sut.exposedCreateDefaultQuery().toString());
        } catch (MalformedURLException e) {
            fail("Can not create SUT!");
        }
    }

    public void testCreateDefaultQueryWithAction() {
        try {
            ExposingDarcsWeb sut = new ExposingDarcsWeb();
            assertEquals("Query",
                         "?r=" + sut.repo + ";a=foobar",
                         sut.exposedCreateDefaultQuery("foobar").toString());
        } catch (MalformedURLException e) {
            fail("Can not create SUT!");
        }
    }

    @Ignore("not ready yet")
    public void testGetChangeSetLink() {
        try {
            String         hash = "1234-the-commit-hash.gz";
            DarcsChangeSet cs   = new DarcsChangeSet();
            DarcsWeb       sut  = new DarcsWeb(new URL(URL), REPO);

            cs.setHash(hash);
            assertEquals("",
                         URL + "?r=" + REPO + ";a=commit;h=" + hash,
                         sut.getChangeSetLink(cs).toString());
        } catch (MalformedURLException e) {
            fail("Can not create SUT!");
        } catch (IOException e) {
            fail("Can not create URI!");
        }
    }

    @Ignore("not ready yet")
    public void testGetFileDiffLink() {
        try {
            String         hash = "1234-the-commit-hash.gz";
            String         file = "a/file/name";
            DarcsChangeSet cs   = new DarcsChangeSet();
            DarcsWeb       sut  = new DarcsWeb(new URL(URL), REPO);

            cs.setHash(hash);
            assertEquals("",
                         URL + "?r=" + REPO + ";a=filediff;h=" + hash + ";f=" + file,
                         sut.getFileDiffLink(cs, file).toString());
        } catch (MalformedURLException e) {
            fail("Can not create SUT!");
        } catch (IOException e) {
            fail("Can not create URI!");
        }
    }
}
