package com.github.kassak.geo.wkt.psi;

import com.github.kassak.geo.wkt.WktFileType;
import com.github.kassak.geo.wkt.WktLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class WktFile extends PsiFileBase {
  public WktFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, WktLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return WktFileType.INSTANCE;
  }
}
