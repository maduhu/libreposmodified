@echo off

set CP=librepos.jar

java -cp %CP% -Dswing.defaultlaf=com.sun.java.swing.plaf.windows.WindowsLookAndFeel net.adrianromero.tpv.config.JFrmConfig
