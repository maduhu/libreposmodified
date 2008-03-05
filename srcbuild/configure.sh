#!/bin/sh

CP=librepos.jar

java -cp $CP -Dswing.defaultlaf=javax.swing.plaf.metal.MetalLookAndFeel net.adrianromero.tpv.config.JFrmConfig
