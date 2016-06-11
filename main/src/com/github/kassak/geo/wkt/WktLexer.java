package com.github.kassak.geo.wkt;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ExceptionUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.text.CaseInsensitiveStringHashingStrategy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

public class WktLexer extends LookAheadLexer {
  private static Map<String, WktTokenType> ourKeyWords = findKeywords();

  private static Map<String, WktTokenType> findKeywords() {
    Map<String, WktTokenType> res = ContainerUtil.newTroveMap(
      CaseInsensitiveStringHashingStrategy.INSTANCE);
    for (Field field : WktTypes.class.getDeclaredFields()) {
      int m = field.getModifiers();
      if (!Modifier.isStatic(m)
        || !Modifier.isFinal(m)
        || !Modifier.isPublic(m)) continue;
      try {
        WktTokenType token = ObjectUtils.tryCast(field.get(null), WktTokenType.class);
        if (token != null) res.put(token.toString(), token);
      }
      catch (IllegalAccessException e) {
        ExceptionUtil.rethrowAllAsUnchecked(e);
      }
    }
    return res;
  }

  public WktLexer() {
    super(new FlexAdapter(new _WktLexer(null)));
  }

  @Override
  protected void lookAhead(Lexer baseLexer) {
    IElementType type = baseLexer.getTokenType();
    if (type == WktTypes.IDENT) {
      WktTokenType newToken = ourKeyWords.get(baseLexer.getTokenText());
      if (newToken != null) type = newToken;
    }
    advanceAs(baseLexer, type);
  }
}
