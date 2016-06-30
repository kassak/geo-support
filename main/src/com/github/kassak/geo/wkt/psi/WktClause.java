package com.github.kassak.geo.wkt.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WktClause extends ASTWrapperPsiElement implements WktElement {
  private final String myType;
  private String myTag = null;

  public WktClause(ASTNode node) {
    this(node, "clause");
  }

  public WktClause(ASTNode node, @NotNull String type) {
    super(node);
    myType = type;
  }

  @Nullable
  public WktAttributes getAttributes() {
    return findChildByClass(WktAttributes.class);
  }

  @NotNull
  public String getType() {
    return myType;
  }

  @Nullable
  public String getTag() {
    if (myTag == null) {
      WktAttributes attributes = getAttributes();
      int end = attributes == null
        ? getTextRange().getEndOffset()
        : attributes.getTextRange().getStartOffset();
      StringBuilder res = new StringBuilder();
      PsiElement el = PsiTreeUtil.getDeepestFirst(this);
      while (el != null && el.getTextRange().getEndOffset() <= end) {
        if (res.length() != 0) res.append(" ");
        res.append(el.getText().trim());
        el = PsiTreeUtil.nextVisibleLeaf(el);
      }
      myTag = res.toString();
    }
    return StringUtil.notNullize(StringUtil.nullize(myTag), myType);
  }

  @Override
  public void subtreeChanged() {
    super.subtreeChanged();
    myTag = null;
  }

  @Override
  public ItemPresentation getPresentation() {
    return WktPresentationUtil.getPresentation(this);
  }
}
