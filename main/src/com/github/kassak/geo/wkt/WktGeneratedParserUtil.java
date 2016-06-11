package com.github.kassak.geo.wkt;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.psi.tree.IElementType;

public class WktGeneratedParserUtil extends GeneratedParserUtilBase {
  public static boolean parseFloatTerm(PsiBuilder builder, int level) {
    IElementType type = builder.getTokenType();
    if (type == WktTypes.SCIENTIFIC) {
      builder.advanceLexer();
      return true;
    }
    boolean numberParsed = false;
    if (type == WktTypes.NUMBER) {
      numberParsed = true;
      IElementType next = builder.rawLookup(1);
      builder.advanceLexer();
      if (next != WktTypes.DOT) return true;
      type = next;
    }
    if (type == WktTypes.DOT && builder.rawLookup(1) == WktTypes.NUMBER) {
      builder.advanceLexer();
      builder.advanceLexer();
      return true;
    }
    return numberParsed;
  }
}
