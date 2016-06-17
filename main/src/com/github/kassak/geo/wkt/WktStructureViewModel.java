package com.github.kassak.geo.wkt;

import com.github.kassak.geo.wkt.psi.WktAttributes;
import com.github.kassak.geo.wkt.psi.WktElement;
import com.github.kassak.geo.wkt.psi.WktFile;
import com.github.kassak.geo.wkt.psi.WktGeometry;
import com.intellij.ide.structureView.*;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SyntaxTraverser;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

import static com.intellij.lang.parser.GeneratedParserUtilBase.DUMMY_BLOCK;

public class WktStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider, StructureViewModel.ExpandInfoProvider {
  public WktStructureViewModel(@NotNull WktFile file, @Nullable Editor editor) {
    super(file, editor, new ViewElement(file));
    withSuitableClasses(WktFile.class, WktGeometry.class);
  }

  @Override
  public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
    return false;
  }

  @Override
  public boolean isAlwaysLeaf(StructureViewTreeElement element) {
    return false;
  }

  @Override
  public boolean isAutoExpand(@NotNull StructureViewTreeElement element) {
    return element.getValue() instanceof WktFile;
  }

  @Override
  public boolean isSmartExpand() {
    return false;
  }

  public static class Factory implements PsiStructureViewFactory {
    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder(PsiFile psiFile) {
      WktFile wktFile = ObjectUtils.tryCast(psiFile, WktFile.class);
      if (wktFile == null) return null;
      return new TreeBasedStructureViewBuilder() {
        @NotNull
        @Override
        public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
          return new WktStructureViewModel(wktFile, editor);
        }
      };
    }
  }

  private static class ViewElement implements StructureViewTreeElement, ItemPresentation {
    private final WktElement myElement;

    public ViewElement(@NotNull WktElement element) {
      myElement = element;
    }

    @Override
    public WktElement getValue() {
      return myElement;
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
      return ObjectUtils.chooseNotNull(myElement.getPresentation(), this);
    }

    @NotNull
    @Override
    public TreeElement[] getChildren() {
      if (myElement instanceof WktFile) {
        return create(psiChildren(myElement).filter(WktGeometry.class));
      }
      if (myElement instanceof WktGeometry) {
        WktAttributes attrs = ((WktGeometry)myElement).getAttributes();
        if (attrs == null) return create(psiChildren(myElement).filter(WktGeometry.class));
        return create(JBIterable.of(attrs.getAttributes(WktGeometry.class)));
      }
      return EMPTY_ARRAY;
    }

    private static JBIterable<PsiElement> psiChildren(@NotNull PsiElement el) {
      return SyntaxTraverser.psiTraverser()
        .withRoots(SyntaxTraverser.psiApi().children(el))
        .expand(x -> x.getNode().getElementType() == DUMMY_BLOCK)
        .traverse();
    }

    @Override
    public void navigate(boolean requestFocus) {
      myElement.navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
      return myElement.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
      return myElement.canNavigateToSource();
    }

    @Nullable
    @Override
    public String getPresentableText() {
      return null;
    }

    @Nullable
    @Override
    public String getLocationString() {
      return null;
    }

    @Nullable
    @Override
    public Icon getIcon(boolean unused) {
      return null;
    }

    private static <T extends WktElement> StructureViewTreeElement[] create(JBIterable<T> items) {
      List<ViewElement> viewElements = items.transform(ViewElement::new).toList();
      return viewElements.toArray(new StructureViewTreeElement[0]);
    }
  }
}
