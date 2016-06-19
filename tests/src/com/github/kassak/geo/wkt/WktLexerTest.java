package com.github.kassak.geo.wkt;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.testFramework.LexerTestCase;

import java.io.File;
import java.io.IOException;

public class WktLexerTest extends LexerTestCase {
  //{OVERWRITE_TESTDATA=true;}
  public void testDates() {
    doTest();
  }

  @Override
  protected Lexer createLexer() {
    return new WktLexer();
  }

  @Override
  protected String getDirPath() {
    return "tests/testData/wkt/lexer";
  }

  protected void doTest() {
    String fileName = getDirPath() + "/" + getTestName(true) + ".wkt";
    String text = "";
    try {
      String fileText = FileUtil.loadFile(new File(fileName));
      text = StringUtil.convertLineSeparators(shouldTrim() ? fileText.trim() : fileText);
    }
    catch (IOException e) {
      fail("can't load file " + fileName + ": " + e.getMessage());
    }
    String res = printTokens(text, 0);
    assertSameLinesWithFile(getDirPath() + "/" + getTestName(true) + ".txt", res);
  }
}
