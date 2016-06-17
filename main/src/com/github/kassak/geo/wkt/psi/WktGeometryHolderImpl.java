package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

public class WktGeometryHolderImpl<T extends WktGeometry> extends WktGeometry {
  private final Class<T> mySubClass;

  public WktGeometryHolderImpl(ASTNode node, String type, Class<T> subClass) {
    super(node, type);
    this.mySubClass = subClass;
  }

  @NotNull
  @SuppressWarnings("unchecked")
  public T[] getGeometry() {
    WktAttributes attributes = getAttributes();
    return attributes == null ? (T[]) Array.newInstance(mySubClass, 0) : attributes.getAttributes(mySubClass);
  }
}
