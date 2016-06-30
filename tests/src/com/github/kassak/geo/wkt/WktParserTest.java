package com.github.kassak.geo.wkt;

import com.intellij.lang.LanguageBraceMatching;
import com.intellij.testFramework.ParsingTestCase;

public class WktParserTest extends ParsingTestCase {
  private static final WktPairedBraceMatcher BRACE_MATCHER = new WktPairedBraceMatcher();
  //{OVERWRITE_TESTDATA=true;}

  @Override
  public void setUp() throws Exception {
    super.setUp();
    LanguageBraceMatching.INSTANCE.addExplicitExtension(WktLanguage.INSTANCE, BRACE_MATCHER);
  }

  @Override
  public void tearDown() throws Exception {
    try {
      super.tearDown();
    }
    finally {
      LanguageBraceMatching.INSTANCE.removeExplicitExtension(WktLanguage.INSTANCE, BRACE_MATCHER);
    }
  }

  public WktParserTest() {
    super("wkt/parser/", "wkt", new WktParserDefinition());
  }

  @Override
  protected String getTestDataPath() {
    return "tests/testData";
  }

  public void testSimple() { doTest(true); }
  public void testFromEsri() { doTest(true); }
  public void testFromCreof() { doTest(true); }
  public void testMany() { doTest(true); }
  public void testWiki() { doTest(true); }
  public void testCrsFromStd() { doTest(true); }
  public void testEnviPEProjcsStrings_v10() { doTest(true); }
  public void testEnviPEProjcsStrings_v10_2() { doTest(true); }
  public void testEnviPEProjcsStrings_v10_3() { doTest(true); }
}
