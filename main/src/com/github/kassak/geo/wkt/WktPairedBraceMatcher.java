package com.github.kassak.geo.wkt;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WktPairedBraceMatcher implements PairedBraceMatcher {
  public static final BracePair[] BRACES = new BracePair[]{
    new BracePair(WktTypes.LBRACKET, WktTypes.RBRACKET, false),
    new BracePair(WktTypes.LPAREN, WktTypes.RPAREN, false),
  };

  @Override
  public BracePair[] getPairs() {
    return BRACES;
  }

  @Override
  public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
    return true;
  }

  @Override
  public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
    return openingBraceOffset;
  }
}
