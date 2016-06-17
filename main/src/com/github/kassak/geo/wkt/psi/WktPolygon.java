package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktPolygon extends WktGeometryHolderImpl<WktLineString> {
  public WktPolygon(ASTNode node) {
    super(node, "polygon", WktLineString.class);
  }
}
