package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class WktPoint extends WktGeometry {
  public WktPoint(ASTNode node) {
    super(node);
  }

  @NotNull
  public WktNumeric[] getCoords() {
    return findChildrenByClass(WktNumeric.class);
  }
}
