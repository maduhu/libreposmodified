/*
 * JImageEditorBeanInfo.java
 *
 * Created on 10 de diciembre de 2005, 14:01
 */

package net.adrianromero.data.gui;

import java.beans.*;

/**
 * @author adrian
 */
public class JImageEditorBeanInfo extends SimpleBeanInfo {
    
    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( net.adrianromero.data.gui.JImageEditor.class , null );//GEN-HEADEREND:BeanDescriptor
        
        // Here you can add code for customizing the BeanDescriptor.
        
        return beanDescriptor;         }//GEN-LAST:BeanDescriptor
    
    
    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_accessibleContext = 0;
    private static final int PROPERTY_actionMap = 1;
    private static final int PROPERTY_alignmentX = 2;
    private static final int PROPERTY_alignmentY = 3;
    private static final int PROPERTY_ancestorListeners = 4;
    private static final int PROPERTY_autoscrolls = 5;
    private static final int PROPERTY_background = 6;
    private static final int PROPERTY_backgroundSet = 7;
    private static final int PROPERTY_border = 8;
    private static final int PROPERTY_bounds = 9;
    private static final int PROPERTY_colorModel = 10;
    private static final int PROPERTY_component = 11;
    private static final int PROPERTY_componentCount = 12;
    private static final int PROPERTY_componentListeners = 13;
    private static final int PROPERTY_componentOrientation = 14;
    private static final int PROPERTY_componentPopupMenu = 15;
    private static final int PROPERTY_components = 16;
    private static final int PROPERTY_containerListeners = 17;
    private static final int PROPERTY_cursor = 18;
    private static final int PROPERTY_cursorSet = 19;
    private static final int PROPERTY_debugGraphicsOptions = 20;
    private static final int PROPERTY_displayable = 21;
    private static final int PROPERTY_doubleBuffered = 22;
    private static final int PROPERTY_dropTarget = 23;
    private static final int PROPERTY_enabled = 24;
    private static final int PROPERTY_focusable = 25;
    private static final int PROPERTY_focusCycleRoot = 26;
    private static final int PROPERTY_focusCycleRootAncestor = 27;
    private static final int PROPERTY_focusListeners = 28;
    private static final int PROPERTY_focusOwner = 29;
    private static final int PROPERTY_focusTraversable = 30;
    private static final int PROPERTY_focusTraversalKeys = 31;
    private static final int PROPERTY_focusTraversalKeysEnabled = 32;
    private static final int PROPERTY_focusTraversalPolicy = 33;
    private static final int PROPERTY_focusTraversalPolicyProvider = 34;
    private static final int PROPERTY_focusTraversalPolicySet = 35;
    private static final int PROPERTY_font = 36;
    private static final int PROPERTY_fontSet = 37;
    private static final int PROPERTY_foreground = 38;
    private static final int PROPERTY_foregroundSet = 39;
    private static final int PROPERTY_graphics = 40;
    private static final int PROPERTY_graphicsConfiguration = 41;
    private static final int PROPERTY_height = 42;
    private static final int PROPERTY_hierarchyBoundsListeners = 43;
    private static final int PROPERTY_hierarchyListeners = 44;
    private static final int PROPERTY_ignoreRepaint = 45;
    private static final int PROPERTY_image = 46;
    private static final int PROPERTY_inheritsPopupMenu = 47;
    private static final int PROPERTY_inputContext = 48;
    private static final int PROPERTY_inputMap = 49;
    private static final int PROPERTY_inputMethodListeners = 50;
    private static final int PROPERTY_inputMethodRequests = 51;
    private static final int PROPERTY_inputVerifier = 52;
    private static final int PROPERTY_insets = 53;
    private static final int PROPERTY_keyListeners = 54;
    private static final int PROPERTY_layout = 55;
    private static final int PROPERTY_lightweight = 56;
    private static final int PROPERTY_locale = 57;
    private static final int PROPERTY_location = 58;
    private static final int PROPERTY_locationOnScreen = 59;
    private static final int PROPERTY_managingFocus = 60;
    private static final int PROPERTY_maxDimensions = 61;
    private static final int PROPERTY_maximumSize = 62;
    private static final int PROPERTY_maximumSizeSet = 63;
    private static final int PROPERTY_minimumSize = 64;
    private static final int PROPERTY_minimumSizeSet = 65;
    private static final int PROPERTY_mouseListeners = 66;
    private static final int PROPERTY_mouseMotionListeners = 67;
    private static final int PROPERTY_mousePosition = 68;
    private static final int PROPERTY_mouseWheelListeners = 69;
    private static final int PROPERTY_name = 70;
    private static final int PROPERTY_nextFocusableComponent = 71;
    private static final int PROPERTY_opaque = 72;
    private static final int PROPERTY_optimizedDrawingEnabled = 73;
    private static final int PROPERTY_paintingTile = 74;
    private static final int PROPERTY_parent = 75;
    private static final int PROPERTY_peer = 76;
    private static final int PROPERTY_preferredSize = 77;
    private static final int PROPERTY_preferredSizeSet = 78;
    private static final int PROPERTY_propertyChangeListeners = 79;
    private static final int PROPERTY_registeredKeyStrokes = 80;
    private static final int PROPERTY_requestFocusEnabled = 81;
    private static final int PROPERTY_rootPane = 82;
    private static final int PROPERTY_showing = 83;
    private static final int PROPERTY_size = 84;
    private static final int PROPERTY_toolkit = 85;
    private static final int PROPERTY_toolTipText = 86;
    private static final int PROPERTY_topLevelAncestor = 87;
    private static final int PROPERTY_transferHandler = 88;
    private static final int PROPERTY_treeLock = 89;
    private static final int PROPERTY_UI = 90;
    private static final int PROPERTY_UIClassID = 91;
    private static final int PROPERTY_valid = 92;
    private static final int PROPERTY_validateRoot = 93;
    private static final int PROPERTY_verifyInputWhenFocusTarget = 94;
    private static final int PROPERTY_vetoableChangeListeners = 95;
    private static final int PROPERTY_visible = 96;
    private static final int PROPERTY_visibleRect = 97;
    private static final int PROPERTY_width = 98;
    private static final int PROPERTY_x = 99;
    private static final int PROPERTY_y = 100;
    private static final int PROPERTY_zoom = 101;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[102];
    
        try {
            properties[PROPERTY_accessibleContext] = new PropertyDescriptor ( "accessibleContext", net.adrianromero.data.gui.JImageEditor.class, "getAccessibleContext", null );
            properties[PROPERTY_actionMap] = new PropertyDescriptor ( "actionMap", net.adrianromero.data.gui.JImageEditor.class, "getActionMap", "setActionMap" );
            properties[PROPERTY_alignmentX] = new PropertyDescriptor ( "alignmentX", net.adrianromero.data.gui.JImageEditor.class, "getAlignmentX", "setAlignmentX" );
            properties[PROPERTY_alignmentY] = new PropertyDescriptor ( "alignmentY", net.adrianromero.data.gui.JImageEditor.class, "getAlignmentY", "setAlignmentY" );
            properties[PROPERTY_ancestorListeners] = new PropertyDescriptor ( "ancestorListeners", net.adrianromero.data.gui.JImageEditor.class, "getAncestorListeners", null );
            properties[PROPERTY_autoscrolls] = new PropertyDescriptor ( "autoscrolls", net.adrianromero.data.gui.JImageEditor.class, "getAutoscrolls", "setAutoscrolls" );
            properties[PROPERTY_background] = new PropertyDescriptor ( "background", net.adrianromero.data.gui.JImageEditor.class, "getBackground", "setBackground" );
            properties[PROPERTY_backgroundSet] = new PropertyDescriptor ( "backgroundSet", net.adrianromero.data.gui.JImageEditor.class, "isBackgroundSet", null );
            properties[PROPERTY_border] = new PropertyDescriptor ( "border", net.adrianromero.data.gui.JImageEditor.class, "getBorder", "setBorder" );
            properties[PROPERTY_bounds] = new PropertyDescriptor ( "bounds", net.adrianromero.data.gui.JImageEditor.class, "getBounds", "setBounds" );
            properties[PROPERTY_colorModel] = new PropertyDescriptor ( "colorModel", net.adrianromero.data.gui.JImageEditor.class, "getColorModel", null );
            properties[PROPERTY_component] = new IndexedPropertyDescriptor ( "component", net.adrianromero.data.gui.JImageEditor.class, null, null, "getComponent", null );
            properties[PROPERTY_componentCount] = new PropertyDescriptor ( "componentCount", net.adrianromero.data.gui.JImageEditor.class, "getComponentCount", null );
            properties[PROPERTY_componentListeners] = new PropertyDescriptor ( "componentListeners", net.adrianromero.data.gui.JImageEditor.class, "getComponentListeners", null );
            properties[PROPERTY_componentOrientation] = new PropertyDescriptor ( "componentOrientation", net.adrianromero.data.gui.JImageEditor.class, "getComponentOrientation", "setComponentOrientation" );
            properties[PROPERTY_componentPopupMenu] = new PropertyDescriptor ( "componentPopupMenu", net.adrianromero.data.gui.JImageEditor.class, "getComponentPopupMenu", "setComponentPopupMenu" );
            properties[PROPERTY_components] = new PropertyDescriptor ( "components", net.adrianromero.data.gui.JImageEditor.class, "getComponents", null );
            properties[PROPERTY_containerListeners] = new PropertyDescriptor ( "containerListeners", net.adrianromero.data.gui.JImageEditor.class, "getContainerListeners", null );
            properties[PROPERTY_cursor] = new PropertyDescriptor ( "cursor", net.adrianromero.data.gui.JImageEditor.class, "getCursor", "setCursor" );
            properties[PROPERTY_cursorSet] = new PropertyDescriptor ( "cursorSet", net.adrianromero.data.gui.JImageEditor.class, "isCursorSet", null );
            properties[PROPERTY_debugGraphicsOptions] = new PropertyDescriptor ( "debugGraphicsOptions", net.adrianromero.data.gui.JImageEditor.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions" );
            properties[PROPERTY_displayable] = new PropertyDescriptor ( "displayable", net.adrianromero.data.gui.JImageEditor.class, "isDisplayable", null );
            properties[PROPERTY_doubleBuffered] = new PropertyDescriptor ( "doubleBuffered", net.adrianromero.data.gui.JImageEditor.class, "isDoubleBuffered", "setDoubleBuffered" );
            properties[PROPERTY_dropTarget] = new PropertyDescriptor ( "dropTarget", net.adrianromero.data.gui.JImageEditor.class, "getDropTarget", "setDropTarget" );
            properties[PROPERTY_enabled] = new PropertyDescriptor ( "enabled", net.adrianromero.data.gui.JImageEditor.class, "isEnabled", "setEnabled" );
            properties[PROPERTY_focusable] = new PropertyDescriptor ( "focusable", net.adrianromero.data.gui.JImageEditor.class, "isFocusable", "setFocusable" );
            properties[PROPERTY_focusCycleRoot] = new PropertyDescriptor ( "focusCycleRoot", net.adrianromero.data.gui.JImageEditor.class, "isFocusCycleRoot", "setFocusCycleRoot" );
            properties[PROPERTY_focusCycleRootAncestor] = new PropertyDescriptor ( "focusCycleRootAncestor", net.adrianromero.data.gui.JImageEditor.class, "getFocusCycleRootAncestor", null );
            properties[PROPERTY_focusListeners] = new PropertyDescriptor ( "focusListeners", net.adrianromero.data.gui.JImageEditor.class, "getFocusListeners", null );
            properties[PROPERTY_focusOwner] = new PropertyDescriptor ( "focusOwner", net.adrianromero.data.gui.JImageEditor.class, "isFocusOwner", null );
            properties[PROPERTY_focusTraversable] = new PropertyDescriptor ( "focusTraversable", net.adrianromero.data.gui.JImageEditor.class, "isFocusTraversable", null );
            properties[PROPERTY_focusTraversalKeys] = new IndexedPropertyDescriptor ( "focusTraversalKeys", net.adrianromero.data.gui.JImageEditor.class, null, null, "getFocusTraversalKeys", "setFocusTraversalKeys" );
            properties[PROPERTY_focusTraversalKeysEnabled] = new PropertyDescriptor ( "focusTraversalKeysEnabled", net.adrianromero.data.gui.JImageEditor.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled" );
            properties[PROPERTY_focusTraversalPolicy] = new PropertyDescriptor ( "focusTraversalPolicy", net.adrianromero.data.gui.JImageEditor.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy" );
            properties[PROPERTY_focusTraversalPolicyProvider] = new PropertyDescriptor ( "focusTraversalPolicyProvider", net.adrianromero.data.gui.JImageEditor.class, "isFocusTraversalPolicyProvider", "setFocusTraversalPolicyProvider" );
            properties[PROPERTY_focusTraversalPolicySet] = new PropertyDescriptor ( "focusTraversalPolicySet", net.adrianromero.data.gui.JImageEditor.class, "isFocusTraversalPolicySet", null );
            properties[PROPERTY_font] = new PropertyDescriptor ( "font", net.adrianromero.data.gui.JImageEditor.class, "getFont", "setFont" );
            properties[PROPERTY_fontSet] = new PropertyDescriptor ( "fontSet", net.adrianromero.data.gui.JImageEditor.class, "isFontSet", null );
            properties[PROPERTY_foreground] = new PropertyDescriptor ( "foreground", net.adrianromero.data.gui.JImageEditor.class, "getForeground", "setForeground" );
            properties[PROPERTY_foregroundSet] = new PropertyDescriptor ( "foregroundSet", net.adrianromero.data.gui.JImageEditor.class, "isForegroundSet", null );
            properties[PROPERTY_graphics] = new PropertyDescriptor ( "graphics", net.adrianromero.data.gui.JImageEditor.class, "getGraphics", null );
            properties[PROPERTY_graphicsConfiguration] = new PropertyDescriptor ( "graphicsConfiguration", net.adrianromero.data.gui.JImageEditor.class, "getGraphicsConfiguration", null );
            properties[PROPERTY_height] = new PropertyDescriptor ( "height", net.adrianromero.data.gui.JImageEditor.class, "getHeight", null );
            properties[PROPERTY_hierarchyBoundsListeners] = new PropertyDescriptor ( "hierarchyBoundsListeners", net.adrianromero.data.gui.JImageEditor.class, "getHierarchyBoundsListeners", null );
            properties[PROPERTY_hierarchyListeners] = new PropertyDescriptor ( "hierarchyListeners", net.adrianromero.data.gui.JImageEditor.class, "getHierarchyListeners", null );
            properties[PROPERTY_ignoreRepaint] = new PropertyDescriptor ( "ignoreRepaint", net.adrianromero.data.gui.JImageEditor.class, "getIgnoreRepaint", "setIgnoreRepaint" );
            properties[PROPERTY_image] = new PropertyDescriptor ( "image", net.adrianromero.data.gui.JImageEditor.class, "getImage", "setImage" );
            properties[PROPERTY_inheritsPopupMenu] = new PropertyDescriptor ( "inheritsPopupMenu", net.adrianromero.data.gui.JImageEditor.class, "getInheritsPopupMenu", "setInheritsPopupMenu" );
            properties[PROPERTY_inputContext] = new PropertyDescriptor ( "inputContext", net.adrianromero.data.gui.JImageEditor.class, "getInputContext", null );
            properties[PROPERTY_inputMap] = new PropertyDescriptor ( "inputMap", net.adrianromero.data.gui.JImageEditor.class, "getInputMap", null );
            properties[PROPERTY_inputMethodListeners] = new PropertyDescriptor ( "inputMethodListeners", net.adrianromero.data.gui.JImageEditor.class, "getInputMethodListeners", null );
            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor ( "inputMethodRequests", net.adrianromero.data.gui.JImageEditor.class, "getInputMethodRequests", null );
            properties[PROPERTY_inputVerifier] = new PropertyDescriptor ( "inputVerifier", net.adrianromero.data.gui.JImageEditor.class, "getInputVerifier", "setInputVerifier" );
            properties[PROPERTY_insets] = new PropertyDescriptor ( "insets", net.adrianromero.data.gui.JImageEditor.class, "getInsets", null );
            properties[PROPERTY_keyListeners] = new PropertyDescriptor ( "keyListeners", net.adrianromero.data.gui.JImageEditor.class, "getKeyListeners", null );
            properties[PROPERTY_layout] = new PropertyDescriptor ( "layout", net.adrianromero.data.gui.JImageEditor.class, "getLayout", "setLayout" );
            properties[PROPERTY_lightweight] = new PropertyDescriptor ( "lightweight", net.adrianromero.data.gui.JImageEditor.class, "isLightweight", null );
            properties[PROPERTY_locale] = new PropertyDescriptor ( "locale", net.adrianromero.data.gui.JImageEditor.class, "getLocale", "setLocale" );
            properties[PROPERTY_location] = new PropertyDescriptor ( "location", net.adrianromero.data.gui.JImageEditor.class, "getLocation", "setLocation" );
            properties[PROPERTY_locationOnScreen] = new PropertyDescriptor ( "locationOnScreen", net.adrianromero.data.gui.JImageEditor.class, "getLocationOnScreen", null );
            properties[PROPERTY_managingFocus] = new PropertyDescriptor ( "managingFocus", net.adrianromero.data.gui.JImageEditor.class, "isManagingFocus", null );
            properties[PROPERTY_maxDimensions] = new PropertyDescriptor ( "maxDimensions", net.adrianromero.data.gui.JImageEditor.class, "getMaxDimensions", "setMaxDimensions" );
            properties[PROPERTY_maximumSize] = new PropertyDescriptor ( "maximumSize", net.adrianromero.data.gui.JImageEditor.class, "getMaximumSize", "setMaximumSize" );
            properties[PROPERTY_maximumSizeSet] = new PropertyDescriptor ( "maximumSizeSet", net.adrianromero.data.gui.JImageEditor.class, "isMaximumSizeSet", null );
            properties[PROPERTY_minimumSize] = new PropertyDescriptor ( "minimumSize", net.adrianromero.data.gui.JImageEditor.class, "getMinimumSize", "setMinimumSize" );
            properties[PROPERTY_minimumSizeSet] = new PropertyDescriptor ( "minimumSizeSet", net.adrianromero.data.gui.JImageEditor.class, "isMinimumSizeSet", null );
            properties[PROPERTY_mouseListeners] = new PropertyDescriptor ( "mouseListeners", net.adrianromero.data.gui.JImageEditor.class, "getMouseListeners", null );
            properties[PROPERTY_mouseMotionListeners] = new PropertyDescriptor ( "mouseMotionListeners", net.adrianromero.data.gui.JImageEditor.class, "getMouseMotionListeners", null );
            properties[PROPERTY_mousePosition] = new PropertyDescriptor ( "mousePosition", net.adrianromero.data.gui.JImageEditor.class, "getMousePosition", null );
            properties[PROPERTY_mouseWheelListeners] = new PropertyDescriptor ( "mouseWheelListeners", net.adrianromero.data.gui.JImageEditor.class, "getMouseWheelListeners", null );
            properties[PROPERTY_name] = new PropertyDescriptor ( "name", net.adrianromero.data.gui.JImageEditor.class, "getName", "setName" );
            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor ( "nextFocusableComponent", net.adrianromero.data.gui.JImageEditor.class, "getNextFocusableComponent", "setNextFocusableComponent" );
            properties[PROPERTY_opaque] = new PropertyDescriptor ( "opaque", net.adrianromero.data.gui.JImageEditor.class, "isOpaque", "setOpaque" );
            properties[PROPERTY_optimizedDrawingEnabled] = new PropertyDescriptor ( "optimizedDrawingEnabled", net.adrianromero.data.gui.JImageEditor.class, "isOptimizedDrawingEnabled", null );
            properties[PROPERTY_paintingTile] = new PropertyDescriptor ( "paintingTile", net.adrianromero.data.gui.JImageEditor.class, "isPaintingTile", null );
            properties[PROPERTY_parent] = new PropertyDescriptor ( "parent", net.adrianromero.data.gui.JImageEditor.class, "getParent", null );
            properties[PROPERTY_peer] = new PropertyDescriptor ( "peer", net.adrianromero.data.gui.JImageEditor.class, "getPeer", null );
            properties[PROPERTY_preferredSize] = new PropertyDescriptor ( "preferredSize", net.adrianromero.data.gui.JImageEditor.class, "getPreferredSize", "setPreferredSize" );
            properties[PROPERTY_preferredSizeSet] = new PropertyDescriptor ( "preferredSizeSet", net.adrianromero.data.gui.JImageEditor.class, "isPreferredSizeSet", null );
            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor ( "propertyChangeListeners", net.adrianromero.data.gui.JImageEditor.class, "getPropertyChangeListeners", null );
            properties[PROPERTY_registeredKeyStrokes] = new PropertyDescriptor ( "registeredKeyStrokes", net.adrianromero.data.gui.JImageEditor.class, "getRegisteredKeyStrokes", null );
            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor ( "requestFocusEnabled", net.adrianromero.data.gui.JImageEditor.class, "isRequestFocusEnabled", "setRequestFocusEnabled" );
            properties[PROPERTY_rootPane] = new PropertyDescriptor ( "rootPane", net.adrianromero.data.gui.JImageEditor.class, "getRootPane", null );
            properties[PROPERTY_showing] = new PropertyDescriptor ( "showing", net.adrianromero.data.gui.JImageEditor.class, "isShowing", null );
            properties[PROPERTY_size] = new PropertyDescriptor ( "size", net.adrianromero.data.gui.JImageEditor.class, "getSize", "setSize" );
            properties[PROPERTY_toolkit] = new PropertyDescriptor ( "toolkit", net.adrianromero.data.gui.JImageEditor.class, "getToolkit", null );
            properties[PROPERTY_toolTipText] = new PropertyDescriptor ( "toolTipText", net.adrianromero.data.gui.JImageEditor.class, "getToolTipText", "setToolTipText" );
            properties[PROPERTY_topLevelAncestor] = new PropertyDescriptor ( "topLevelAncestor", net.adrianromero.data.gui.JImageEditor.class, "getTopLevelAncestor", null );
            properties[PROPERTY_transferHandler] = new PropertyDescriptor ( "transferHandler", net.adrianromero.data.gui.JImageEditor.class, "getTransferHandler", "setTransferHandler" );
            properties[PROPERTY_treeLock] = new PropertyDescriptor ( "treeLock", net.adrianromero.data.gui.JImageEditor.class, "getTreeLock", null );
            properties[PROPERTY_UI] = new PropertyDescriptor ( "UI", net.adrianromero.data.gui.JImageEditor.class, "getUI", "setUI" );
            properties[PROPERTY_UIClassID] = new PropertyDescriptor ( "UIClassID", net.adrianromero.data.gui.JImageEditor.class, "getUIClassID", null );
            properties[PROPERTY_valid] = new PropertyDescriptor ( "valid", net.adrianromero.data.gui.JImageEditor.class, "isValid", null );
            properties[PROPERTY_validateRoot] = new PropertyDescriptor ( "validateRoot", net.adrianromero.data.gui.JImageEditor.class, "isValidateRoot", null );
            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor ( "verifyInputWhenFocusTarget", net.adrianromero.data.gui.JImageEditor.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget" );
            properties[PROPERTY_vetoableChangeListeners] = new PropertyDescriptor ( "vetoableChangeListeners", net.adrianromero.data.gui.JImageEditor.class, "getVetoableChangeListeners", null );
            properties[PROPERTY_visible] = new PropertyDescriptor ( "visible", net.adrianromero.data.gui.JImageEditor.class, "isVisible", "setVisible" );
            properties[PROPERTY_visibleRect] = new PropertyDescriptor ( "visibleRect", net.adrianromero.data.gui.JImageEditor.class, "getVisibleRect", null );
            properties[PROPERTY_width] = new PropertyDescriptor ( "width", net.adrianromero.data.gui.JImageEditor.class, "getWidth", null );
            properties[PROPERTY_x] = new PropertyDescriptor ( "x", net.adrianromero.data.gui.JImageEditor.class, "getX", null );
            properties[PROPERTY_y] = new PropertyDescriptor ( "y", net.adrianromero.data.gui.JImageEditor.class, "getY", null );
            properties[PROPERTY_zoom] = new PropertyDescriptor ( "zoom", net.adrianromero.data.gui.JImageEditor.class, "getZoom", "setZoom" );
        }
        catch( IntrospectionException e) {}//GEN-HEADEREND:Properties
        
        // Here you can add code for customizing the properties array.
        
        return properties;         }//GEN-LAST:Properties
    
    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_ancestorListener = 0;
    private static final int EVENT_componentListener = 1;
    private static final int EVENT_containerListener = 2;
    private static final int EVENT_focusListener = 3;
    private static final int EVENT_hierarchyBoundsListener = 4;
    private static final int EVENT_hierarchyListener = 5;
    private static final int EVENT_inputMethodListener = 6;
    private static final int EVENT_keyListener = 7;
    private static final int EVENT_mouseListener = 8;
    private static final int EVENT_mouseMotionListener = 9;
    private static final int EVENT_mouseWheelListener = 10;
    private static final int EVENT_propertyChangeListener = 11;
    private static final int EVENT_vetoableChangeListener = 12;

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[13];
    
            try {
            eventSets[EVENT_ancestorListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[] {"ancestorAdded", "ancestorMoved", "ancestorRemoved"}, "addAncestorListener", "removeAncestorListener" );
            eventSets[EVENT_componentListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "componentListener", java.awt.event.ComponentListener.class, new String[] {"componentHidden", "componentMoved", "componentResized", "componentShown"}, "addComponentListener", "removeComponentListener" );
            eventSets[EVENT_containerListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "containerListener", java.awt.event.ContainerListener.class, new String[] {"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener" );
            eventSets[EVENT_focusListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "focusListener", java.awt.event.FocusListener.class, new String[] {"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener" );
            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[] {"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener" );
            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[] {"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener" );
            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[] {"caretPositionChanged", "inputMethodTextChanged"}, "addInputMethodListener", "removeInputMethodListener" );
            eventSets[EVENT_keyListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "keyListener", java.awt.event.KeyListener.class, new String[] {"keyPressed", "keyReleased", "keyTyped"}, "addKeyListener", "removeKeyListener" );
            eventSets[EVENT_mouseListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "mouseListener", java.awt.event.MouseListener.class, new String[] {"mouseClicked", "mouseEntered", "mouseExited", "mousePressed", "mouseReleased"}, "addMouseListener", "removeMouseListener" );
            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[] {"mouseDragged", "mouseMoved"}, "addMouseMotionListener", "removeMouseMotionListener" );
            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[] {"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener" );
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" );
            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor ( net.adrianromero.data.gui.JImageEditor.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[] {"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener" );
        }
        catch( IntrospectionException e) {}//GEN-HEADEREND:Events
        
        // Here you can add code for customizing the event sets array.
        
        return eventSets;         }//GEN-LAST:Events
    
    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_action0 = 0;
    private static final int METHOD_add1 = 1;
    private static final int METHOD_addNotify2 = 2;
    private static final int METHOD_addPropertyChangeListener3 = 3;
    private static final int METHOD_applyComponentOrientation4 = 4;
    private static final int METHOD_areFocusTraversalKeysSet5 = 5;
    private static final int METHOD_bounds6 = 6;
    private static final int METHOD_checkImage7 = 7;
    private static final int METHOD_computeVisibleRect8 = 8;
    private static final int METHOD_contains9 = 9;
    private static final int METHOD_countComponents10 = 10;
    private static final int METHOD_createImage11 = 11;
    private static final int METHOD_createToolTip12 = 12;
    private static final int METHOD_createVolatileImage13 = 13;
    private static final int METHOD_decZoom14 = 14;
    private static final int METHOD_deliverEvent15 = 15;
    private static final int METHOD_disable16 = 16;
    private static final int METHOD_dispatchEvent17 = 17;
    private static final int METHOD_doLayout18 = 18;
    private static final int METHOD_doLoad19 = 19;
    private static final int METHOD_enable20 = 20;
    private static final int METHOD_enableInputMethods21 = 21;
    private static final int METHOD_findComponentAt22 = 22;
    private static final int METHOD_firePropertyChange23 = 23;
    private static final int METHOD_getActionForKeyStroke24 = 24;
    private static final int METHOD_getBounds25 = 25;
    private static final int METHOD_getClientProperty26 = 26;
    private static final int METHOD_getComponentAt27 = 27;
    private static final int METHOD_getComponentZOrder28 = 28;
    private static final int METHOD_getConditionForKeyStroke29 = 29;
    private static final int METHOD_getDefaultLocale30 = 30;
    private static final int METHOD_getFontMetrics31 = 31;
    private static final int METHOD_getInsets32 = 32;
    private static final int METHOD_getListeners33 = 33;
    private static final int METHOD_getLocation34 = 34;
    private static final int METHOD_getMousePosition35 = 35;
    private static final int METHOD_getPopupLocation36 = 36;
    private static final int METHOD_getPropertyChangeListeners37 = 37;
    private static final int METHOD_getSize38 = 38;
    private static final int METHOD_getToolTipLocation39 = 39;
    private static final int METHOD_getToolTipText40 = 40;
    private static final int METHOD_gotFocus41 = 41;
    private static final int METHOD_grabFocus42 = 42;
    private static final int METHOD_handleEvent43 = 43;
    private static final int METHOD_hasFocus44 = 44;
    private static final int METHOD_hide45 = 45;
    private static final int METHOD_imageUpdate46 = 46;
    private static final int METHOD_incZoom47 = 47;
    private static final int METHOD_insets48 = 48;
    private static final int METHOD_inside49 = 49;
    private static final int METHOD_invalidate50 = 50;
    private static final int METHOD_isAncestorOf51 = 51;
    private static final int METHOD_isFocusCycleRoot52 = 52;
    private static final int METHOD_isLightweightComponent53 = 53;
    private static final int METHOD_keyDown54 = 54;
    private static final int METHOD_keyUp55 = 55;
    private static final int METHOD_layout56 = 56;
    private static final int METHOD_list57 = 57;
    private static final int METHOD_locate58 = 58;
    private static final int METHOD_location59 = 59;
    private static final int METHOD_lostFocus60 = 60;
    private static final int METHOD_minimumSize61 = 61;
    private static final int METHOD_mouseDown62 = 62;
    private static final int METHOD_mouseDrag63 = 63;
    private static final int METHOD_mouseEnter64 = 64;
    private static final int METHOD_mouseExit65 = 65;
    private static final int METHOD_mouseMove66 = 66;
    private static final int METHOD_mouseUp67 = 67;
    private static final int METHOD_move68 = 68;
    private static final int METHOD_nextFocus69 = 69;
    private static final int METHOD_paint70 = 70;
    private static final int METHOD_paintAll71 = 71;
    private static final int METHOD_paintComponents72 = 72;
    private static final int METHOD_paintImmediately73 = 73;
    private static final int METHOD_postEvent74 = 74;
    private static final int METHOD_preferredSize75 = 75;
    private static final int METHOD_prepareImage76 = 76;
    private static final int METHOD_print77 = 77;
    private static final int METHOD_printAll78 = 78;
    private static final int METHOD_printComponents79 = 79;
    private static final int METHOD_putClientProperty80 = 80;
    private static final int METHOD_registerKeyboardAction81 = 81;
    private static final int METHOD_remove82 = 82;
    private static final int METHOD_removeAll83 = 83;
    private static final int METHOD_removeNotify84 = 84;
    private static final int METHOD_removePropertyChangeListener85 = 85;
    private static final int METHOD_repaint86 = 86;
    private static final int METHOD_requestDefaultFocus87 = 87;
    private static final int METHOD_requestFocus88 = 88;
    private static final int METHOD_requestFocusInWindow89 = 89;
    private static final int METHOD_resetKeyboardActions90 = 90;
    private static final int METHOD_reshape91 = 91;
    private static final int METHOD_resize92 = 92;
    private static final int METHOD_revalidate93 = 93;
    private static final int METHOD_scrollRectToVisible94 = 94;
    private static final int METHOD_setBounds95 = 95;
    private static final int METHOD_setComponentZOrder96 = 96;
    private static final int METHOD_setDefaultLocale97 = 97;
    private static final int METHOD_show98 = 98;
    private static final int METHOD_size99 = 99;
    private static final int METHOD_toString100 = 100;
    private static final int METHOD_transferFocus101 = 101;
    private static final int METHOD_transferFocusBackward102 = 102;
    private static final int METHOD_transferFocusDownCycle103 = 103;
    private static final int METHOD_transferFocusUpCycle104 = 104;
    private static final int METHOD_unregisterKeyboardAction105 = 105;
    private static final int METHOD_update106 = 106;
    private static final int METHOD_updateUI107 = 107;
    private static final int METHOD_validate108 = 108;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[109];
    
        try {
            methods[METHOD_action0] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("action", new Class[] {java.awt.Event.class, java.lang.Object.class}));
            methods[METHOD_action0].setDisplayName ( "" );
            methods[METHOD_add1] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("add", new Class[] {java.awt.Component.class}));
            methods[METHOD_add1].setDisplayName ( "" );
            methods[METHOD_addNotify2] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("addNotify", new Class[] {}));
            methods[METHOD_addNotify2].setDisplayName ( "" );
            methods[METHOD_addPropertyChangeListener3] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("addPropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class}));
            methods[METHOD_addPropertyChangeListener3].setDisplayName ( "" );
            methods[METHOD_applyComponentOrientation4] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("applyComponentOrientation", new Class[] {java.awt.ComponentOrientation.class}));
            methods[METHOD_applyComponentOrientation4].setDisplayName ( "" );
            methods[METHOD_areFocusTraversalKeysSet5] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("areFocusTraversalKeysSet", new Class[] {Integer.TYPE}));
            methods[METHOD_areFocusTraversalKeysSet5].setDisplayName ( "" );
            methods[METHOD_bounds6] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("bounds", new Class[] {}));
            methods[METHOD_bounds6].setDisplayName ( "" );
            methods[METHOD_checkImage7] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("checkImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class}));
            methods[METHOD_checkImage7].setDisplayName ( "" );
            methods[METHOD_computeVisibleRect8] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("computeVisibleRect", new Class[] {java.awt.Rectangle.class}));
            methods[METHOD_computeVisibleRect8].setDisplayName ( "" );
            methods[METHOD_contains9] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("contains", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_contains9].setDisplayName ( "" );
            methods[METHOD_countComponents10] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("countComponents", new Class[] {}));
            methods[METHOD_countComponents10].setDisplayName ( "" );
            methods[METHOD_createImage11] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("createImage", new Class[] {java.awt.image.ImageProducer.class}));
            methods[METHOD_createImage11].setDisplayName ( "" );
            methods[METHOD_createToolTip12] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("createToolTip", new Class[] {}));
            methods[METHOD_createToolTip12].setDisplayName ( "" );
            methods[METHOD_createVolatileImage13] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("createVolatileImage", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_createVolatileImage13].setDisplayName ( "" );
            methods[METHOD_decZoom14] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("decZoom", new Class[] {}));
            methods[METHOD_decZoom14].setDisplayName ( "" );
            methods[METHOD_deliverEvent15] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("deliverEvent", new Class[] {java.awt.Event.class}));
            methods[METHOD_deliverEvent15].setDisplayName ( "" );
            methods[METHOD_disable16] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("disable", new Class[] {}));
            methods[METHOD_disable16].setDisplayName ( "" );
            methods[METHOD_dispatchEvent17] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("dispatchEvent", new Class[] {java.awt.AWTEvent.class}));
            methods[METHOD_dispatchEvent17].setDisplayName ( "" );
            methods[METHOD_doLayout18] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("doLayout", new Class[] {}));
            methods[METHOD_doLayout18].setDisplayName ( "" );
            methods[METHOD_doLoad19] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("doLoad", new Class[] {}));
            methods[METHOD_doLoad19].setDisplayName ( "" );
            methods[METHOD_enable20] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("enable", new Class[] {}));
            methods[METHOD_enable20].setDisplayName ( "" );
            methods[METHOD_enableInputMethods21] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("enableInputMethods", new Class[] {Boolean.TYPE}));
            methods[METHOD_enableInputMethods21].setDisplayName ( "" );
            methods[METHOD_findComponentAt22] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("findComponentAt", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_findComponentAt22].setDisplayName ( "" );
            methods[METHOD_firePropertyChange23] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, Boolean.TYPE, Boolean.TYPE}));
            methods[METHOD_firePropertyChange23].setDisplayName ( "" );
            methods[METHOD_getActionForKeyStroke24] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getActionForKeyStroke", new Class[] {javax.swing.KeyStroke.class}));
            methods[METHOD_getActionForKeyStroke24].setDisplayName ( "" );
            methods[METHOD_getBounds25] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getBounds", new Class[] {java.awt.Rectangle.class}));
            methods[METHOD_getBounds25].setDisplayName ( "" );
            methods[METHOD_getClientProperty26] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getClientProperty", new Class[] {java.lang.Object.class}));
            methods[METHOD_getClientProperty26].setDisplayName ( "" );
            methods[METHOD_getComponentAt27] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getComponentAt", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_getComponentAt27].setDisplayName ( "" );
            methods[METHOD_getComponentZOrder28] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getComponentZOrder", new Class[] {java.awt.Component.class}));
            methods[METHOD_getComponentZOrder28].setDisplayName ( "" );
            methods[METHOD_getConditionForKeyStroke29] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getConditionForKeyStroke", new Class[] {javax.swing.KeyStroke.class}));
            methods[METHOD_getConditionForKeyStroke29].setDisplayName ( "" );
            methods[METHOD_getDefaultLocale30] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getDefaultLocale", new Class[] {}));
            methods[METHOD_getDefaultLocale30].setDisplayName ( "" );
            methods[METHOD_getFontMetrics31] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getFontMetrics", new Class[] {java.awt.Font.class}));
            methods[METHOD_getFontMetrics31].setDisplayName ( "" );
            methods[METHOD_getInsets32] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getInsets", new Class[] {java.awt.Insets.class}));
            methods[METHOD_getInsets32].setDisplayName ( "" );
            methods[METHOD_getListeners33] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getListeners", new Class[] {java.lang.Class.class}));
            methods[METHOD_getListeners33].setDisplayName ( "" );
            methods[METHOD_getLocation34] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getLocation", new Class[] {java.awt.Point.class}));
            methods[METHOD_getLocation34].setDisplayName ( "" );
            methods[METHOD_getMousePosition35] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getMousePosition", new Class[] {Boolean.TYPE}));
            methods[METHOD_getMousePosition35].setDisplayName ( "" );
            methods[METHOD_getPopupLocation36] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getPopupLocation", new Class[] {java.awt.event.MouseEvent.class}));
            methods[METHOD_getPopupLocation36].setDisplayName ( "" );
            methods[METHOD_getPropertyChangeListeners37] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getPropertyChangeListeners", new Class[] {java.lang.String.class}));
            methods[METHOD_getPropertyChangeListeners37].setDisplayName ( "" );
            methods[METHOD_getSize38] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getSize", new Class[] {java.awt.Dimension.class}));
            methods[METHOD_getSize38].setDisplayName ( "" );
            methods[METHOD_getToolTipLocation39] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getToolTipLocation", new Class[] {java.awt.event.MouseEvent.class}));
            methods[METHOD_getToolTipLocation39].setDisplayName ( "" );
            methods[METHOD_getToolTipText40] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("getToolTipText", new Class[] {java.awt.event.MouseEvent.class}));
            methods[METHOD_getToolTipText40].setDisplayName ( "" );
            methods[METHOD_gotFocus41] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("gotFocus", new Class[] {java.awt.Event.class, java.lang.Object.class}));
            methods[METHOD_gotFocus41].setDisplayName ( "" );
            methods[METHOD_grabFocus42] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("grabFocus", new Class[] {}));
            methods[METHOD_grabFocus42].setDisplayName ( "" );
            methods[METHOD_handleEvent43] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("handleEvent", new Class[] {java.awt.Event.class}));
            methods[METHOD_handleEvent43].setDisplayName ( "" );
            methods[METHOD_hasFocus44] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("hasFocus", new Class[] {}));
            methods[METHOD_hasFocus44].setDisplayName ( "" );
            methods[METHOD_hide45] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("hide", new Class[] {}));
            methods[METHOD_hide45].setDisplayName ( "" );
            methods[METHOD_imageUpdate46] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("imageUpdate", new Class[] {java.awt.Image.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_imageUpdate46].setDisplayName ( "" );
            methods[METHOD_incZoom47] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("incZoom", new Class[] {}));
            methods[METHOD_incZoom47].setDisplayName ( "" );
            methods[METHOD_insets48] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("insets", new Class[] {}));
            methods[METHOD_insets48].setDisplayName ( "" );
            methods[METHOD_inside49] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("inside", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_inside49].setDisplayName ( "" );
            methods[METHOD_invalidate50] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("invalidate", new Class[] {}));
            methods[METHOD_invalidate50].setDisplayName ( "" );
            methods[METHOD_isAncestorOf51] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("isAncestorOf", new Class[] {java.awt.Component.class}));
            methods[METHOD_isAncestorOf51].setDisplayName ( "" );
            methods[METHOD_isFocusCycleRoot52] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("isFocusCycleRoot", new Class[] {java.awt.Container.class}));
            methods[METHOD_isFocusCycleRoot52].setDisplayName ( "" );
            methods[METHOD_isLightweightComponent53] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("isLightweightComponent", new Class[] {java.awt.Component.class}));
            methods[METHOD_isLightweightComponent53].setDisplayName ( "" );
            methods[METHOD_keyDown54] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("keyDown", new Class[] {java.awt.Event.class, Integer.TYPE}));
            methods[METHOD_keyDown54].setDisplayName ( "" );
            methods[METHOD_keyUp55] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("keyUp", new Class[] {java.awt.Event.class, Integer.TYPE}));
            methods[METHOD_keyUp55].setDisplayName ( "" );
            methods[METHOD_layout56] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("layout", new Class[] {}));
            methods[METHOD_layout56].setDisplayName ( "" );
            methods[METHOD_list57] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("list", new Class[] {java.io.PrintStream.class, Integer.TYPE}));
            methods[METHOD_list57].setDisplayName ( "" );
            methods[METHOD_locate58] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("locate", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_locate58].setDisplayName ( "" );
            methods[METHOD_location59] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("location", new Class[] {}));
            methods[METHOD_location59].setDisplayName ( "" );
            methods[METHOD_lostFocus60] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("lostFocus", new Class[] {java.awt.Event.class, java.lang.Object.class}));
            methods[METHOD_lostFocus60].setDisplayName ( "" );
            methods[METHOD_minimumSize61] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("minimumSize", new Class[] {}));
            methods[METHOD_minimumSize61].setDisplayName ( "" );
            methods[METHOD_mouseDown62] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("mouseDown", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseDown62].setDisplayName ( "" );
            methods[METHOD_mouseDrag63] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("mouseDrag", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseDrag63].setDisplayName ( "" );
            methods[METHOD_mouseEnter64] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("mouseEnter", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseEnter64].setDisplayName ( "" );
            methods[METHOD_mouseExit65] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("mouseExit", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseExit65].setDisplayName ( "" );
            methods[METHOD_mouseMove66] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("mouseMove", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseMove66].setDisplayName ( "" );
            methods[METHOD_mouseUp67] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("mouseUp", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseUp67].setDisplayName ( "" );
            methods[METHOD_move68] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("move", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_move68].setDisplayName ( "" );
            methods[METHOD_nextFocus69] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("nextFocus", new Class[] {}));
            methods[METHOD_nextFocus69].setDisplayName ( "" );
            methods[METHOD_paint70] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("paint", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_paint70].setDisplayName ( "" );
            methods[METHOD_paintAll71] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("paintAll", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_paintAll71].setDisplayName ( "" );
            methods[METHOD_paintComponents72] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("paintComponents", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_paintComponents72].setDisplayName ( "" );
            methods[METHOD_paintImmediately73] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("paintImmediately", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_paintImmediately73].setDisplayName ( "" );
            methods[METHOD_postEvent74] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("postEvent", new Class[] {java.awt.Event.class}));
            methods[METHOD_postEvent74].setDisplayName ( "" );
            methods[METHOD_preferredSize75] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("preferredSize", new Class[] {}));
            methods[METHOD_preferredSize75].setDisplayName ( "" );
            methods[METHOD_prepareImage76] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class}));
            methods[METHOD_prepareImage76].setDisplayName ( "" );
            methods[METHOD_print77] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("print", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_print77].setDisplayName ( "" );
            methods[METHOD_printAll78] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("printAll", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_printAll78].setDisplayName ( "" );
            methods[METHOD_printComponents79] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("printComponents", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_printComponents79].setDisplayName ( "" );
            methods[METHOD_putClientProperty80] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("putClientProperty", new Class[] {java.lang.Object.class, java.lang.Object.class}));
            methods[METHOD_putClientProperty80].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction81] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, Integer.TYPE}));
            methods[METHOD_registerKeyboardAction81].setDisplayName ( "" );
            methods[METHOD_remove82] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("remove", new Class[] {Integer.TYPE}));
            methods[METHOD_remove82].setDisplayName ( "" );
            methods[METHOD_removeAll83] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("removeAll", new Class[] {}));
            methods[METHOD_removeAll83].setDisplayName ( "" );
            methods[METHOD_removeNotify84] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("removeNotify", new Class[] {}));
            methods[METHOD_removeNotify84].setDisplayName ( "" );
            methods[METHOD_removePropertyChangeListener85] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("removePropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class}));
            methods[METHOD_removePropertyChangeListener85].setDisplayName ( "" );
            methods[METHOD_repaint86] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("repaint", new Class[] {Long.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_repaint86].setDisplayName ( "" );
            methods[METHOD_requestDefaultFocus87] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("requestDefaultFocus", new Class[] {}));
            methods[METHOD_requestDefaultFocus87].setDisplayName ( "" );
            methods[METHOD_requestFocus88] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("requestFocus", new Class[] {}));
            methods[METHOD_requestFocus88].setDisplayName ( "" );
            methods[METHOD_requestFocusInWindow89] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("requestFocusInWindow", new Class[] {}));
            methods[METHOD_requestFocusInWindow89].setDisplayName ( "" );
            methods[METHOD_resetKeyboardActions90] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("resetKeyboardActions", new Class[] {}));
            methods[METHOD_resetKeyboardActions90].setDisplayName ( "" );
            methods[METHOD_reshape91] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("reshape", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_reshape91].setDisplayName ( "" );
            methods[METHOD_resize92] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("resize", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_resize92].setDisplayName ( "" );
            methods[METHOD_revalidate93] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("revalidate", new Class[] {}));
            methods[METHOD_revalidate93].setDisplayName ( "" );
            methods[METHOD_scrollRectToVisible94] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("scrollRectToVisible", new Class[] {java.awt.Rectangle.class}));
            methods[METHOD_scrollRectToVisible94].setDisplayName ( "" );
            methods[METHOD_setBounds95] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("setBounds", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_setBounds95].setDisplayName ( "" );
            methods[METHOD_setComponentZOrder96] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("setComponentZOrder", new Class[] {java.awt.Component.class, Integer.TYPE}));
            methods[METHOD_setComponentZOrder96].setDisplayName ( "" );
            methods[METHOD_setDefaultLocale97] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("setDefaultLocale", new Class[] {java.util.Locale.class}));
            methods[METHOD_setDefaultLocale97].setDisplayName ( "" );
            methods[METHOD_show98] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("show", new Class[] {}));
            methods[METHOD_show98].setDisplayName ( "" );
            methods[METHOD_size99] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("size", new Class[] {}));
            methods[METHOD_size99].setDisplayName ( "" );
            methods[METHOD_toString100] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("toString", new Class[] {}));
            methods[METHOD_toString100].setDisplayName ( "" );
            methods[METHOD_transferFocus101] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("transferFocus", new Class[] {}));
            methods[METHOD_transferFocus101].setDisplayName ( "" );
            methods[METHOD_transferFocusBackward102] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("transferFocusBackward", new Class[] {}));
            methods[METHOD_transferFocusBackward102].setDisplayName ( "" );
            methods[METHOD_transferFocusDownCycle103] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("transferFocusDownCycle", new Class[] {}));
            methods[METHOD_transferFocusDownCycle103].setDisplayName ( "" );
            methods[METHOD_transferFocusUpCycle104] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("transferFocusUpCycle", new Class[] {}));
            methods[METHOD_transferFocusUpCycle104].setDisplayName ( "" );
            methods[METHOD_unregisterKeyboardAction105] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("unregisterKeyboardAction", new Class[] {javax.swing.KeyStroke.class}));
            methods[METHOD_unregisterKeyboardAction105].setDisplayName ( "" );
            methods[METHOD_update106] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("update", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_update106].setDisplayName ( "" );
            methods[METHOD_updateUI107] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("updateUI", new Class[] {}));
            methods[METHOD_updateUI107].setDisplayName ( "" );
            methods[METHOD_validate108] = new MethodDescriptor ( net.adrianromero.data.gui.JImageEditor.class.getMethod("validate", new Class[] {}));
            methods[METHOD_validate108].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods
        
        // Here you can add code for customizing the methods array.
        
        return methods;         }//GEN-LAST:Methods
    
    
    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx
    
    
//GEN-FIRST:Superclass
    
    // Here you can add code for customizing the Superclass BeanInfo.
    
//GEN-LAST:Superclass
    
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     *
     * @return BeanDescriptor describing the editable
     * properties of this bean.  May return null if the
     * information should be obtained by automatic analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
        return getBdescriptor();
    }
    
    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     *
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean.  May return null if the
     * information should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will
     * belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     * A client of getPropertyDescriptors can use "instanceof" to check
     * if a given PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getPdescriptor();
    }
    
    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     *
     * @return  An array of EventSetDescriptors describing the kinds of
     * events fired by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
        return getEdescriptor();
    }
    
    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     *
     * @return  An array of MethodDescriptors describing the methods
     * implemented by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public MethodDescriptor[] getMethodDescriptors() {
        return getMdescriptor();
    }
    
    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are
     * customizing the bean.
     * @return  Index of default property in the PropertyDescriptor array
     * 		returned by getPropertyDescriptors.
     * <P>	Returns -1 if there is no default property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }
    
    /**
     * A bean may have a "default" event that is the event that will
     * mostly commonly be used by human's when using the bean.
     * @return Index of default event in the EventSetDescriptor array
     *		returned by getEventSetDescriptors.
     * <P>	Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }
}

