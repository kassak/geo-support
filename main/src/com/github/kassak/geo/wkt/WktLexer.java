package com.github.kassak.geo.wkt;

import com.intellij.lexer.FlexAdapter;

public class WktLexer extends FlexAdapter {
  public WktLexer() {
    super(new _WktLexer(null));
  }
}
