package com.github.kassak.geo.wkt;

import com.github.kassak.geo.wkt.psi.WktAttributes;
import com.github.kassak.geo.wkt.psi.WktElement;
import com.github.kassak.geo.wkt.psi.WktGeometry;
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerBase;
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerFactoryBase;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class WktSubPrimitiveHighlighting extends HighlightUsagesHandlerBase<WktGeometry> {
  private final WktGeometry myTarget;

  protected WktSubPrimitiveHighlighting(@NotNull Editor editor, @NotNull PsiFile file, @NotNull WktGeometry target) {
    super(editor, file);
    this.myTarget = target;
  }

  @Override
  public List<WktGeometry> getTargets() {
    return Collections.singletonList(myTarget);
  }

  @Override
  protected void selectTargets(List<WktGeometry> targets, Consumer<List<WktGeometry>> selectionConsumer) {
    selectionConsumer.consume(targets);
  }

  @Override
  public void computeUsages(List<WktGeometry> targets) {
    for (WktGeometry target : targets) {
      WktAttributes attributes = target.getAttributes();
      if (attributes == null) continue;
      for (WktElement sub : attributes.getAttributes(WktElement.class)) {
        addOccurrence(sub);
      }
    }
  }

  public static class Factory extends HighlightUsagesHandlerFactoryBase {
    @Nullable
    @Override
    public HighlightUsagesHandlerBase createHighlightUsagesHandler(@NotNull Editor editor, @NotNull PsiFile file, @NotNull PsiElement target) {
      WktGeometry geom = PsiTreeUtil.getParentOfType(target, WktGeometry.class);
      return geom == null ? null : new WktSubPrimitiveHighlighting(editor, file, geom);
    }
  }
}
