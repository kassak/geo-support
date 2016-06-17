package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktMultiPolygon extends WktGeometryHolderImpl<WktPolygon> {
  public WktMultiPolygon(ASTNode node) {
    super(node, "multi polygon", WktPolygon.class);
  }
}
