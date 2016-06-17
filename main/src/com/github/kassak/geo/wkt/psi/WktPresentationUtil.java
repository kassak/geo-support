package com.github.kassak.geo.wkt.psi;

import com.github.kassak.geo.GeoIcons;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class WktPresentationUtil {
  public static ItemPresentation getPresentation(@NotNull WktGeometry geom) {
    return new ItemPresentation() {
      @Nullable
      @Override
      public String getPresentableText() {
        return geom.getTag();
      }

      @Nullable
      @Override
      public String getLocationString() {
        WktAttributes attributes = geom.getAttributes();
        if (attributes == null) return null;
        String text = attributes.getText();
        return StringUtil.shortenTextWithEllipsis(text, 25, 8, true);
      }

      @Nullable
      @Override
      public Icon getIcon(boolean unused) {
        return GeoIcons.WKT_FILE;
      }
    };
  }
}
