package com.github.kassak.geo.wkt.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class WktDatetime extends ASTWrapperPsiElement implements WktElement {
  public WktDatetime(ASTNode node) {
    super(node);
  }
}
