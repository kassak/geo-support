package com.github.kassak.geo.wkt.psi;

import com.github.kassak.geo.wkt.psi.WktElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class WktEmbSrid extends ASTWrapperPsiElement implements WktElement {
  public WktEmbSrid(ASTNode node) {
    super(node);
  }
}
