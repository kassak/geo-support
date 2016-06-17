package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktPolyhedralSurface extends WktGeometryHolderImpl<WktPolygon> {
  public WktPolyhedralSurface(ASTNode node) {
    super(node, "polyhedral surface", WktPolygon.class);
  }
}
