package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class WktGeometry extends WktClause {
  public WktGeometry(ASTNode node, @NotNull String type) {
    super(node, type);
  }
}
