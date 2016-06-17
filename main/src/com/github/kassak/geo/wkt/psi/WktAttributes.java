package com.github.kassak.geo.wkt.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class WktAttributes extends ASTWrapperPsiElement implements WktElement {
  public WktAttributes(ASTNode node) {
    super(node);
  }
}
