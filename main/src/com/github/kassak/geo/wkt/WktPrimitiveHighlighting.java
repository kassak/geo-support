package com.github.kassak.geo.wkt;

import com.github.kassak.geo.wkt.psi.WktGeometry;
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerBase;
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerFactoryBase;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class WktPrimitiveHighlighting extends HighlightUsagesHandlerBase<WktGeometry> {
  private final WktGeometry myTarget;

  protected WktPrimitiveHighlighting(@NotNull Editor editor, @NotNull PsiFile file, @NotNull WktGeometry target) {
    super(editor, file);
    this.myTarget = target;
  }

  @Override
  public List<WktGeometry> getTargets() {
    return Collections.singletonList(myTarget);
  }

  @Override
  protected void selectTargets(List<WktGeometry> targets, Consumer<List<WktGeometry>> selectionConsumer) {

  }

  @Override
  public void computeUsages(List<WktGeometry> targets) {

  }

  public static class Factory extends HighlightUsagesHandlerFactoryBase {
    @Nullable
    @Override
    public HighlightUsagesHandlerBase createHighlightUsagesHandler(@NotNull Editor editor, @NotNull PsiFile file, @NotNull PsiElement target) {
      return null;
    }
  }
}
