package com.github.kassak.geo.wkt;

import com.intellij.lang.BracePair;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import gnu.trove.TByteArrayList;

public class WktGeneratedParserUtil extends GeneratedParserUtilBase {
  private static final Key<String> GEOM_DIM = Key.create("GEOM_DIMENSIONS");

  public static boolean withDim(PsiBuilder builder, int level, String dim, Parser parser) {
    String prev = builder.getUserDataUnprotected(GEOM_DIM);
    builder.putUserDataUnprotected(GEOM_DIM, dim);
    try {
      return parser.parse(builder, level);
    }
    finally {
      builder.putUserDataUnprotected(GEOM_DIM, prev);
    }
  }

  public static boolean dimIs(PsiBuilder builder, int level, String dim) {
    return dim.equals(builder.getUserDataUnprotected(GEOM_DIM));
  }

  public static boolean parseUnknownIdentifier(PsiBuilder builder, int level) {
    if (builder.getTokenType() == WktTypes.IDENT
      && builder.lookAhead(1) == WktTypes.LPAREN) {
      String messageText = ErrorState.get(builder).getExpectedText(builder) + " got " + builder.getTokenText();
      builder.error(messageText);
      builder.advanceLexer();
      return true;
    }
    return false;
  }

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

  public static boolean parsePrimitives(PsiBuilder builder, int level, Parser parser) {
    return parseAsTree(ErrorState.get(builder), builder, level, DUMMY_BLOCK, false,
      (b, l) -> {
        builder.eof();
        int preIdx = builder.rawTokenIndex();
        boolean res = parser.parse(b, l);
        if (!res && !builder.eof()) {
          if (preIdx == builder.rawTokenIndex()) {
            LighterASTNode latest = builder.getLatestDoneMarker();
            PsiBuilder.Marker marker;
            if (latest != null && latest.getTokenType() == TokenType.ERROR_ELEMENT) {
              marker = ((PsiBuilder.Marker)latest).precede();
              ((PsiBuilder.Marker)latest).drop();
            }
            else {
              marker = builder.mark();
            }
            String expectedText = ErrorState.get(builder).getExpectedText(builder);
            expectedText = StringUtil.trimEnd(expectedText, ", ");
            skipCurrent(builder, level);
            marker.error(expectedText);
          }
          res = true;
        }
        return res;
      },
      TRUE_CONDITION);
  }

  private static void skipCurrent(PsiBuilder builder, int level) {
    IElementType type = builder.getTokenType();
    builder.advanceLexer();
    int id = getBraceId(type);
    if (id >= 0) return;
    TByteArrayList stack = new TByteArrayList();
    stack.add((byte)-id);
    while (!builder.eof()) {
      type = builder.getTokenType();
      builder.advanceLexer();
      id = getBraceId(type);
      if (id < 0) {
        stack.add((byte)-id);
      }
      else if (id > 0) {
        byte openId = stack.remove(stack.size() - 1);
        if (openId != id || stack.isEmpty()) break;
      }
    }
  }

  private static int getBraceId(IElementType type) {
    BracePair[] braces = WktPairedBraceMatcher.BRACES;
    for (int i = 0; i < braces.length; i++) {
      if (braces[i].getLeftBraceType() == type) return -(i + 1);
      if (braces[i].getRightBraceType() == type) return (i + 1);
    }
    return 0;
  }
}
