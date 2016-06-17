package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktMultiPoint extends WktGeometryHolderImpl<WktPoint> {
  public WktMultiPoint(ASTNode node) {
    super(node, WktPoint.class);
  }
}
