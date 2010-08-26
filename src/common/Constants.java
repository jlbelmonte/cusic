package common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constants {

	private static List<String> languages = Arrays.asList("abap",
			"actionscript", "ada", "adso_idsm", "ample", "asp", "asp.net",
			"assembly", "awk", "bash", "c", "c_shell", "csharp", "cpp", "c",
			"css", "cobol", "coldfusion", "css", "d", "dal", "dos_batch",
			"dtd", "expect", "focus", "fortran_77", "fortran_90", "fortran_95",
			"haskell", "html", "idl", "inc", "java", "javascript", "jcl",
			"jsp", "korn_shell", "lex", "lisp", "livelink_oscript", "lua",
			"m4", "make", "matlab", "ml", "modula3", "msbuild_scripts",
			"mumps", "nant_scripts", "nastran_dmap", "objective_c",
			"oracle_forms", "oracle_reports", "pascal",
			"patran_command_language", "perl", "python", "rexx", "ruby", "sed",
			"skill", "skillpp", "softbridge_basic", "sql", "tcl_tk",
			"teamcenter_def", "teamcenter_met", "teamcenter_mth", "vim_script",
			"visual_basic", "xml", "xsd", "xslt", "yacc", "yaml",
			"apple_script", "go", "sass", "haml", "scala", "php", "mxml",
			"wsdl", "svg", "arc", "clojure", "coffeescript", "erlang",
			"eiffel", "haxe", "io", "max", "nu", "objective_j", "ooc",
			"smalltalk", "verilog", "xaml", "gettext", "vhdl");

	public static List<String> getLanguages() {
		return languages;
	}

}