package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktGeometryCollection extends WktGeometryHolderImpl<WktGeometry> {
  public WktGeometryCollection(ASTNode node) {
    super(node, WktGeometry.class);
  }
}
