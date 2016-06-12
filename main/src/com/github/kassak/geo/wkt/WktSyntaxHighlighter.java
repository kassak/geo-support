package com.github.kassak.geo.wkt;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WktSyntaxHighlighter extends SyntaxHighlighterBase {
  private final static Map<IElementType, TextAttributesKey> ourMap =
    ContainerUtil.<IElementType, TextAttributesKey>immutableMapBuilder()
      .put(WktTypes.SCIENTIFIC, DefaultLanguageHighlighterColors.NUMBER)
      .put(WktTypes.NUMBER, DefaultLanguageHighlighterColors.NUMBER)
      .put(WktTypes.DOT, DefaultLanguageHighlighterColors.DOT)
      .put(WktTypes.COMMA, DefaultLanguageHighlighterColors.COMMA)
      .put(WktTypes.STRING, DefaultLanguageHighlighterColors.STRING)
      .put(WktTypes.LPAREN, DefaultLanguageHighlighterColors.PARENTHESES)
      .put(WktTypes.RPAREN, DefaultLanguageHighlighterColors.PARENTHESES)
      .put(WktTypes.LBRACKET, DefaultLanguageHighlighterColors.BRACKETS)
      .put(WktTypes.RBRACKET, DefaultLanguageHighlighterColors.BRACKETS)
      .build();
  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new WktLexer();
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(ContainerUtil.getOrElse(ourMap, tokenType, DefaultLanguageHighlighterColors.KEYWORD));
  }

  public static class Factory extends SyntaxHighlighterFactory {
    @NotNull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile) {
      return new WktSyntaxHighlighter();
    }
  }
}
