@echo off

set CP=librepos.jar

set CP=%CP%;lib/l2fprod-common-tasks.jar
set CP=%CP%;lib/jasperreports-2.0.1.jar
set CP=%CP%;lib/jcommon-1.0.0.jar
set CP=%CP%;lib/jfreechart-1.0.0.jar
set CP=%CP%;lib/jdt-compiler-3.1.1.jar
set CP=%CP%;lib/commons-beanutils-1.7.jar
set CP=%CP%;lib/commons-digester-1.7.jar
set CP=%CP%;lib/itext-1.3.1.jar
set CP=%CP%;lib/poi-3.0.1-FINAL-20070705.jar
set CP=%CP%;lib/barcode4j-light.jar
set CP=%CP%;lib/commons-codec-1.3.jar
set CP=%CP%;lib/velocity-1.5.jar
set CP=%CP%;lib/oro-2.0.8.jar
set CP=%CP%;lib/commons-collections-3.1.jar
set CP=%CP%;lib/commons-lang-2.1.jar
set CP=%CP%;lib/bsh-core-2.0b4.jar
set CP=%CP%;lib/RXTXcomm.jar
set CP=%CP%;lib/jpos111.jar

rem Apache Axis SOAP libraries.
set CP=%CP%;lib/axis.jar
set CP=%CP%;lib/jaxrpc.jar
set CP=%CP%;lib/saaj.jar
set CP=%CP%;lib/wsdl4j-1.5.1.jar
set CP=%CP%;lib/commons-discovery-0.2.jar
set CP=%CP%;lib/commons-logging-1.0.4.jar

start /B javaw -cp %CP% -Dswing.defaultlaf=com.sun.java.swing.plaf.windows.WindowsLookAndFeel -Djava.library.path=lib/Windows/i368-mingw32 net.adrianromero.tpv.forms.JFrmTPV
