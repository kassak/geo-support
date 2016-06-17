package com.github.kassak.geo.wkt.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class WktAttributes extends ASTWrapperPsiElement implements WktElement {
  public WktAttributes(ASTNode node) {
    super(node);
  }

  @NotNull
  public <A extends PsiElement> A[] getAttributes(@NotNull Class<A> clazz) {
    return findChildrenByClass(clazz);
  }
}
