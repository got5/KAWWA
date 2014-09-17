@ECHO OFF
IF NOT "%~f0" == "~f0" GOTO :WinNT
@"jruby-complete-1.7.3.jar" "D:/workspaces/KawwaLib/Portal/.jruby/gems/bin/sass" %1 %2 %3 %4 %5 %6 %7 %8 %9
GOTO :EOF
:WinNT
@"jruby-complete-1.7.3.jar" "%~dpn0" %*
