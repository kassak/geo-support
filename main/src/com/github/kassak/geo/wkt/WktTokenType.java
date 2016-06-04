package com.github.kassak.geo.wkt;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class WktTokenType extends IElementType {
  public WktTokenType(@NotNull @NonNls String debugName) {
    super(debugName, WktLanguage.INSTANCE);
  }

  @Override
  public String toString() {
    return "WktToken." + super.toString();
  }
}
