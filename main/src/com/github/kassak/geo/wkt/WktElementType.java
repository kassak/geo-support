package com.github.kassak.geo.wkt;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class WktElementType extends IElementType {
  public WktElementType(@NotNull @NonNls String debugName) {
    super(debugName, WktLanguage.INSTANCE);
  }
}
