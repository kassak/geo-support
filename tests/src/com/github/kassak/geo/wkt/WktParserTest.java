package com.github.kassak.geo.wkt;

import com.intellij.testFramework.ParsingTestCase;

public class WktParserTest extends ParsingTestCase {
  //{OVERWRITE_TESTDATA=true;}
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
}
