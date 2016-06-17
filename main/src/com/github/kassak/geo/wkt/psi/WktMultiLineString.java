package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktMultiLineString extends WktGeometryHolderImpl<WktLineString> {
  public WktMultiLineString(ASTNode node) {
    super(node, WktLineString.class);
  }
}
