package com.github.kassak.geo.wkt;

import com.github.kassak.geo.wkt.psi.WktGeometry;
import com.intellij.lang.documentation.DocumentationProviderEx;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.UIUtil;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.legend.Drawer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class WktDocumentationProvider extends DocumentationProviderEx {
  private final GeometryFactory myGeometryFactory = JTSFactoryFinder.getGeometryFactory();

  @Override
  public String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
    PsiElement e = ObjectUtils.chooseNotNull(originalElement, element);
    if (e == null) return null;
    String text = e.getContainingFile().getText();
    Geometry geom;
    try {
      geom = parseGeometry(text);
    } catch (Exception ex) {
      return ex.getMessage();
    }
    return geom.getGeometryType() + "<br><img src='file://preview'>";
  }

  @Nullable
  @Override
  public PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file, @Nullable PsiElement contextElement) {
    return PsiTreeUtil.getParentOfType(contextElement, WktGeometry.class);
  }

  @Nullable
  @Override
  public Image getLocalImageForElement(@NotNull PsiElement element, @NotNull String imageSpec) {
    if (!imageSpec.equals("file://preview")) return null;
    String text = element.getContainingFile().getText();
    Geometry geom;
    try {
      geom = parseGeometry(text);
    }
    catch (Exception ignored) {
      return null;
    }
    return createImage(geom, 400, 400, 10, 10);
  }

  @NotNull
  private BufferedImage createImage(Geometry geom, int width, int height, int px, int py) {
    BufferedImage image = UIUtil.createImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    AffineTransform w2s = world2screen(geom, width, height, px, py);
    Drawer drawer = Drawer.create();
    drawToImage(drawer, image, w2s, geom);
    return image;
  }

  private void drawToImage(Drawer drawer, BufferedImage image, AffineTransform w2s, Geometry geom) {
    if (geom instanceof GeometryCollection) {
      GeometryCollection gc = (GeometryCollection) geom;
      for (int i = 0, e = gc.getNumGeometries(); i < e; ++i) {
        drawToImage(drawer, image, w2s, gc.getGeometryN(i));
      }
    }
    else {
      drawer.drawFeature(image, drawer.feature(geom), w2s);
    }
  }

  @NotNull
  private AffineTransform world2screen(Geometry geom, int width, int height, int px, int py) {
    Envelope e = geom.getEnvelopeInternal();
    double sx = (width - 2 * px)/e.getWidth();
    double sy = (height - 2 * px)/e.getHeight();
    double s = Math.min(sx, sy);

    double tx = -e.getMinX() * s;
    double ty = e.getMinY() * s + height - 2 * py;
    AffineTransform at = new AffineTransform(s, 0.0d, 0.0d, -s, tx, ty);
    AffineTransform originTranslation = AffineTransform.getTranslateInstance(px, py);
    originTranslation.concatenate(at);
    return originTranslation;
  }

  private Geometry parseGeometry(String text) throws Exception {
    WKTReader reader = new WKTReader(myGeometryFactory);
    return reader.read(text);
  }
}
