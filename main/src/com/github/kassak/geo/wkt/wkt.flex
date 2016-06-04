package com.github.kassak.geo.wkt;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.kassak.geo.wkt.WktTypes;
import com.intellij.psi.TokenType;

%%

%class _WktLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

NEW_LINE = \n|\r|\r\n
WHITE_SPACE = [\ \t\f]|{NEW_LINE}

NUMBER = \d+(\.\d+)?
WORD = \w(\w|\d)*

%%

<YYINITIAL> {
  {WORD}            {return WktTypes.WORD;}
  {NUMBER}          {return WktTypes.NUMBER;}
  "("               {return WktTypes.LPAREN;}
  ")"               {return WktTypes.RPAREN;}
  ","               {return WktTypes.COMMA;}
  {WHITE_SPACE}+    {return TokenType.WHITE_SPACE;}
  .                 {return TokenType.BAD_CHARACTER;}
}
