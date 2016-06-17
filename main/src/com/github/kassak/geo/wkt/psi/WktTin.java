package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktTin extends WktGeometryHolderImpl<WktPolygon> {
  public WktTin(ASTNode node) {
    super(node, "tin", WktPolygon.class);
  }
}
