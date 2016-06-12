package com.github.kassak.geo.wkt;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WktPairedBraceMatcher implements PairedBraceMatcher {
  private static final BracePair[] ourBracePairs = new BracePair[]{
    new BracePair(WktTypes.LPAREN, WktTypes.RPAREN, false),
    new BracePair(WktTypes.LBRACKET, WktTypes.RBRACKET, false)
  };

  @Override
  public BracePair[] getPairs() {
    return ourBracePairs;
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
