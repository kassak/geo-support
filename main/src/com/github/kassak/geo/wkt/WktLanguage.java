package com.github.kassak.geo.wkt;

import com.intellij.lang.Language;

public class WktLanguage extends Language {
  public static final WktLanguage INSTANCE = new WktLanguage();

  public WktLanguage() {
    super("WKT");
  }
}
