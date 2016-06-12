package com.github.kassak.geo.wkt;

import com.intellij.testFramework.ParsingTestCase;

public class WktParserTest extends ParsingTestCase {
  public WktParserTest() {
    super("wkt/parser/", "wkt", new WktParserDefinition());
  }

  @Override
  protected String getTestDataPath() {
    return "testData";
  }

  public void testSimple() { doTest(true); }
  public void testFromEsri() { doTest(true); }
  public void testFromCreof() { doTest(true); }
}
