package com.github.kassak.geo.wkt.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.ContainerUtil;

import java.lang.reflect.Constructor;
import java.util.Map;

import static com.github.kassak.geo.wkt.WktTypes.*;

public class WktElementFactory {
  private static final Map<IElementType, Class<? extends PsiElement>> ourElements =
    ContainerUtil.<IElementType, Class<? extends PsiElement>>immutableMapBuilder()
      .put(ATTRIBUTES, WktAttributes.class)
      .put(EMB_SRID, WktEmbSrid.class)
      .put(INTEGER, WktNumeric.class)
      .put(NUMERIC, WktNumeric.class)
      .put(WKT_EMPTY, WktEmpty.class)
      .put(WKT_GEOMETRY_COLLECTION, WktGeometryCollection.class)
      .put(WKT_LINE_STRING, WktLineString.class)
      .put(WKT_MULTI_LINE_STRING, WktMultiLineString.class)
      .put(WKT_MULTI_POINT, WktMultiPoint.class)
      .put(WKT_MULTI_POLYGON, WktMultiPolygon.class)
      .put(WKT_POINT, WktPoint.class)
      .put(WKT_POLYGON, WktPolygon.class)
      .put(WKT_POLYHEDRAL_SURFACE, WktPolyhedralSurface.class)
      .put(WKT_TIN, WktTin.class)
      .put(WKT_TRIANGLE, WktTriangle.class)
      .put(WKT_WILDCARD, WktWildcard.class)
      .build();

  public static PsiElement createElement(ASTNode node) {
    IElementType type = node.getElementType();
    Class<? extends PsiElement> clazz = ourElements.get(type);
    if (clazz == null) throw new AssertionError("Unknown element type: " + type);
    try {
      Constructor<? extends PsiElement> ctor = clazz.getConstructor(ASTNode.class);
      return ctor.newInstance(node);
    }
    catch (ReflectiveOperationException e) {
      throw new AssertionError("Failed to create element: " + clazz.getSimpleName() + " (" + type + ")", e);
    }
  }
}
