package com.github.kassak.geo.wkt.psi;


import com.intellij.lang.ASTNode;

public class WktLineString extends WktGeometryHolderImpl<WktPoint> {
  public WktLineString(ASTNode node) {
    super(node, WktPoint.class);
  }
}
