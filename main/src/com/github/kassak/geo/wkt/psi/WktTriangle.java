package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktTriangle extends WktGeometryHolderImpl<WktLineString> {
  public WktTriangle(ASTNode node) {
    super(node, WktLineString.class);
  }
}
