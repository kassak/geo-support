package com.github.kassak.geo.wkt;

import com.github.kassak.geo.GeoIcons;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class WktFileType extends LanguageFileType {
  public static final WktFileType INSTANCE = new WktFileType();

  protected WktFileType() {
    super(WktLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "WKT";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Well Known Text";
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "wkt";
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return GeoIcons.WKT_FILE;
  }

  public static class Factory extends FileTypeFactory {

    @Override
    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
      consumer.consume(WktFileType.INSTANCE, WktFileType.INSTANCE.getDefaultExtension());
    }
  }
}
