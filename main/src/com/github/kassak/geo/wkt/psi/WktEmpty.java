package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;

public class WktEmpty extends WktGeometry {
  public WktEmpty(ASTNode node) {
    super(node, "empty");
  }
}
