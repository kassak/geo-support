package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktMultiLineString extends WktGeometryHolderImpl<WktLineString> {
  public WktMultiLineString(ASTNode node) {
    super(node, "multi line string", WktLineString.class);
  }
}
