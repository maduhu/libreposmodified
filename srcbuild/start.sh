#!/bin/sh

CP=librepos.jar

CP=$CP:lib/l2fprod-common-tasks.jar
CP=$CP:lib/jasperreports-2.0.1.jar
CP=$CP:lib/jcommon-1.0.0.jar
CP=$CP:lib/jfreechart-1.0.0.jar
CP=$CP:lib/jdt-compiler-3.1.1.jar
CP=$CP:lib/commons-beanutils-1.7.jar
CP=$CP:lib/commons-digester-1.7.jar
CP=$CP:lib/itext-1.3.1.jar
CP=$CP:lib/poi-3.0.1-FINAL-20070705.jar
CP=$CP:lib/barcode4j-light.jar
CP=$CP:lib/commons-codec-1.3.jar
CP=$CP:lib/velocity-1.5.jar
CP=$CP:lib/oro-2.0.8.jar
CP=$CP:lib/commons-collections-3.1.jar
CP=$CP:lib/commons-lang-2.1.jar
CP=$CP:lib/bsh-core-2.0b4.jar
CP=$CP:lib/RXTXcomm.jar
CP=$CP:lib/jpos111.jar

# Apache Axis SOAP libraries.
CP=$CP:lib/axis.jar
CP=$CP:lib/jaxrpc.jar
CP=$CP:lib/saaj.jar
CP=$CP:lib/wsdl4j-1.5.1.jar
CP=$CP:lib/commons-discovery-0.2.jar
CP=$CP:lib/commons-logging-1.0.4.jar



java -cp $CP -Dswing.defaultlaf=javax.swing.plaf.metal.MetalLookAndFeel -Djava.library.path=lib/Linux/i686-unknown-linux-gnu net.adrianromero.tpv.forms.JFrmTPV
