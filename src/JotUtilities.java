import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class JotUtilities {
    public static String DIRTY = " *";
    public static String CLEAN = " ";

    public static int WAIT = 5;

    // will remove these soon..
    public static int hexToR(String h) { return Integer.parseInt(h.substring(0, 2), 16); };
    public static int hexToG(String h) { return Integer.parseInt(h.substring(2, 4), 16); };
    public static int hexToB(String h) { return Integer.parseInt(h.substring(4, 6), 16); };

    public static int hexTo_(String h, int o, int e) {
        return Integer.parseInt(h.substring(o, e), 16);
    };

    public static String getExtension(String fileName) {
        String extension = "";
        int ext = fileName.lastIndexOf(".");
        if (ext > 0) {
            extension = fileName.substring(ext+1);
        }

        return extension;
    }

    public static String applySyntax(String extension) {
        String syntax;

        switch (extension) {
            case "actionscript":
                syntax = SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT;
                JotEngine.langStrip.setText("ActionScript" + " ");
            break;
            case "asm":
                syntax = SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86;
                JotEngine.langStrip.setText("Assembly" + " ");
            break;
            case "bbcode":
                syntax = SyntaxConstants.SYNTAX_STYLE_BBCODE;
                JotEngine.langStrip.setText("BBCODE" + " ");
            break;
            case "h":
            case "c":
                syntax = SyntaxConstants.SYNTAX_STYLE_C;
                JotEngine.langStrip.setText("C" + " ");
            break;
            case "closure":
                syntax = SyntaxConstants.SYNTAX_STYLE_CLOJURE;
                JotEngine.langStrip.setText("Clojure" + " ");
            break;
            case "hpp":
            case "hxx":
            case "cxx":
            case "cpp":
            case "cc":
                syntax = SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS;
                JotEngine.langStrip.setText("C++" + " ");
            break;
            case "cs":
                syntax = SyntaxConstants.SYNTAX_STYLE_CSHARP;
                JotEngine.langStrip.setText("C#" + " ");
            break;
            case "css":
                syntax = SyntaxConstants.SYNTAX_STYLE_CSS;
                JotEngine.langStrip.setText("CSS" + " ");
            break;
            case "delphi":
                syntax = SyntaxConstants.SYNTAX_STYLE_DELPHI;
                JotEngine.langStrip.setText("Delphi" + " ");
            break;
            case "dtd":
                syntax = SyntaxConstants.SYNTAX_STYLE_DTD;
                JotEngine.langStrip.setText("DTD" + " ");
            break;
            case "fortran":
                syntax = SyntaxConstants.SYNTAX_STYLE_FORTRAN;
                JotEngine.langStrip.setText("FORTRAN" + " ");
            break;
            case "groovy":
                syntax = SyntaxConstants.SYNTAX_STYLE_GROOVY;
                JotEngine.langStrip.setText("Groovy" + " ");
            break;
            case "html":
            case "htm":
                syntax = SyntaxConstants.SYNTAX_STYLE_HTML;
                JotEngine.langStrip.setText("HTML" + " ");
            break;
            case "java":
                syntax = SyntaxConstants.SYNTAX_STYLE_JAVA;
                JotEngine.langStrip.setText("Java" + " ");
            break;
            case "js":
                syntax = SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT;
                JotEngine.langStrip.setText("Javascript" + " ");
            break;
            case "json":
                syntax = SyntaxConstants.SYNTAX_STYLE_JSON;
                JotEngine.langStrip.setText("JSON" + " ");
            break;
            case "jsp":
                syntax = SyntaxConstants.SYNTAX_STYLE_JSP;
                JotEngine.langStrip.setText("JSP" + " ");
            break;
            case "latex":
            case "tex":
                syntax = SyntaxConstants.SYNTAX_STYLE_LATEX;
                JotEngine.langStrip.setText("TeX" + " ");
            break;
            case "lisp":
                syntax = SyntaxConstants.SYNTAX_STYLE_LISP;
                JotEngine.langStrip.setText("Lisp" + " ");
            break;
            case "lua":
                syntax = SyntaxConstants.SYNTAX_STYLE_LUA;
                JotEngine.langStrip.setText("Lua" + " ");
            break;
            case "Makefile":
            case "make":
                syntax = SyntaxConstants.SYNTAX_STYLE_MAKEFILE;
                JotEngine.langStrip.setText("Makefile" + " ");
            break;
            case "mxml":
                syntax = SyntaxConstants.SYNTAX_STYLE_MXML;
                JotEngine.langStrip.setText("MXML" + " ");
            break;
            case "nsis":
                syntax = SyntaxConstants.SYNTAX_STYLE_NSIS;
                JotEngine.langStrip.setText("NSIS" + " ");
            break;
            case "pl":
                syntax = SyntaxConstants.SYNTAX_STYLE_PERL;
                JotEngine.langStrip.setText("Perl" + " ");
            break;
            case "php":
                syntax = SyntaxConstants.SYNTAX_STYLE_PHP;
                JotEngine.langStrip.setText("PHP" + " ");
            break;
            case "properties":
                syntax = SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE;
                JotEngine.langStrip.setText("Properties" + " ");
            break;
            case "py":
                syntax = SyntaxConstants.SYNTAX_STYLE_PYTHON;
                JotEngine.langStrip.setText("Python" + " ");
            break;
            case "rb":
                syntax = SyntaxConstants.SYNTAX_STYLE_RUBY;
                JotEngine.langStrip.setText("Ruby" + " ");
            break;
            case "sas":
                syntax = SyntaxConstants.SYNTAX_STYLE_SAS;
                JotEngine.langStrip.setText("SAS" + " ");
            break;
            case "scala":
                syntax = SyntaxConstants.SYNTAX_STYLE_SCALA;
                JotEngine.langStrip.setText("Scala" + " ");
            break;
            case "sql":
                syntax = SyntaxConstants.SYNTAX_STYLE_SQL;
                JotEngine.langStrip.setText("SQL" + " ");
            break;
            case "tcl":
                syntax = SyntaxConstants.SYNTAX_STYLE_TCL;
                JotEngine.langStrip.setText("TCL" + " ");
            break;
            case "unix":
                syntax = SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL;
                JotEngine.langStrip.setText("Unix Shell" + " ");
            break;
            // case "vb":
            //     syntax = SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC;
            //     JotEngine.langStrip.setText("Visual Basic" + " ");
            // break;
            case "bat":
            case "cmd":
                syntax = SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH;
                JotEngine.langStrip.setText("Batch File" + " ");
            break;
            case "xml":
                syntax = SyntaxConstants.SYNTAX_STYLE_XML;
                JotEngine.langStrip.setText("XML" + " ");
            break;
            default:
                syntax = SyntaxConstants.SYNTAX_STYLE_NONE;
                JotEngine.langStrip.setText("None" + " ");
            break;
        }

        return syntax;
    }
}
