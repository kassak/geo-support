package com.github.kassak.geo.wkt.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;

public class WktGeometry extends ASTWrapperPsiElement implements WktElement {
  public WktGeometry(ASTNode node) {
    super(node);
  }

  @Nullable
  public WktAttributes getAttributes() {
    return findChildByClass(WktAttributes.class);
  }
}
