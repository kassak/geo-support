package com.github.kassak.geo.wkt.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class WktString extends ASTWrapperPsiElement implements WktElement {
  public WktString(ASTNode node) {
    super(node);
  }
}
