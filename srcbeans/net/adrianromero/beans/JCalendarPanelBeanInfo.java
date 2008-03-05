/*
 * JCalendarPanelBeanInfo.java
 *
 * Created on 9 de diciembre de 2005, 0:14
 */

package net.adrianromero.beans;

import java.beans.*;

/**
 * @author adrian
 */
public class JCalendarPanelBeanInfo extends SimpleBeanInfo {
    
    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( net.adrianromero.beans.JCalendarPanel.class , null );//GEN-HEADEREND:BeanDescriptor
        
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
    private static final int PROPERTY_date = 20;
    private static final int PROPERTY_debugGraphicsOptions = 21;
    private static final int PROPERTY_displayable = 22;
    private static final int PROPERTY_doubleBuffered = 23;
    private static final int PROPERTY_dropTarget = 24;
    private static final int PROPERTY_enabled = 25;
    private static final int PROPERTY_focusable = 26;
    private static final int PROPERTY_focusCycleRoot = 27;
    private static final int PROPERTY_focusCycleRootAncestor = 28;
    private static final int PROPERTY_focusListeners = 29;
    private static final int PROPERTY_focusOwner = 30;
    private static final int PROPERTY_focusTraversable = 31;
    private static final int PROPERTY_focusTraversalKeys = 32;
    private static final int PROPERTY_focusTraversalKeysEnabled = 33;
    private static final int PROPERTY_focusTraversalPolicy = 34;
    private static final int PROPERTY_focusTraversalPolicyProvider = 35;
    private static final int PROPERTY_focusTraversalPolicySet = 36;
    private static final int PROPERTY_font = 37;
    private static final int PROPERTY_fontSet = 38;
    private static final int PROPERTY_foreground = 39;
    private static final int PROPERTY_foregroundSet = 40;
    private static final int PROPERTY_graphics = 41;
    private static final int PROPERTY_graphicsConfiguration = 42;
    private static final int PROPERTY_height = 43;
    private static final int PROPERTY_hierarchyBoundsListeners = 44;
    private static final int PROPERTY_hierarchyListeners = 45;
    private static final int PROPERTY_ignoreRepaint = 46;
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
    private static final int PROPERTY_maximumSize = 61;
    private static final int PROPERTY_maximumSizeSet = 62;
    private static final int PROPERTY_minimumSize = 63;
    private static final int PROPERTY_minimumSizeSet = 64;
    private static final int PROPERTY_mouseListeners = 65;
    private static final int PROPERTY_mouseMotionListeners = 66;
    private static final int PROPERTY_mousePosition = 67;
    private static final int PROPERTY_mouseWheelListeners = 68;
    private static final int PROPERTY_name = 69;
    private static final int PROPERTY_nextFocusableComponent = 70;
    private static final int PROPERTY_opaque = 71;
    private static final int PROPERTY_optimizedDrawingEnabled = 72;
    private static final int PROPERTY_paintingTile = 73;
    private static final int PROPERTY_parent = 74;
    private static final int PROPERTY_peer = 75;
    private static final int PROPERTY_preferredSize = 76;
    private static final int PROPERTY_preferredSizeSet = 77;
    private static final int PROPERTY_propertyChangeListeners = 78;
    private static final int PROPERTY_registeredKeyStrokes = 79;
    private static final int PROPERTY_requestFocusEnabled = 80;
    private static final int PROPERTY_rootPane = 81;
    private static final int PROPERTY_showing = 82;
    private static final int PROPERTY_size = 83;
    private static final int PROPERTY_toolkit = 84;
    private static final int PROPERTY_toolTipText = 85;
    private static final int PROPERTY_topLevelAncestor = 86;
    private static final int PROPERTY_transferHandler = 87;
    private static final int PROPERTY_treeLock = 88;
    private static final int PROPERTY_UI = 89;
    private static final int PROPERTY_UIClassID = 90;
    private static final int PROPERTY_valid = 91;
    private static final int PROPERTY_validateRoot = 92;
    private static final int PROPERTY_verifyInputWhenFocusTarget = 93;
    private static final int PROPERTY_vetoableChangeListeners = 94;
    private static final int PROPERTY_visible = 95;
    private static final int PROPERTY_visibleRect = 96;
    private static final int PROPERTY_width = 97;
    private static final int PROPERTY_x = 98;
    private static final int PROPERTY_y = 99;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[100];
    
        try {
            properties[PROPERTY_accessibleContext] = new PropertyDescriptor ( "accessibleContext", net.adrianromero.beans.JCalendarPanel.class, "getAccessibleContext", null );
            properties[PROPERTY_actionMap] = new PropertyDescriptor ( "actionMap", net.adrianromero.beans.JCalendarPanel.class, "getActionMap", "setActionMap" );
            properties[PROPERTY_alignmentX] = new PropertyDescriptor ( "alignmentX", net.adrianromero.beans.JCalendarPanel.class, "getAlignmentX", "setAlignmentX" );
            properties[PROPERTY_alignmentY] = new PropertyDescriptor ( "alignmentY", net.adrianromero.beans.JCalendarPanel.class, "getAlignmentY", "setAlignmentY" );
            properties[PROPERTY_ancestorListeners] = new PropertyDescriptor ( "ancestorListeners", net.adrianromero.beans.JCalendarPanel.class, "getAncestorListeners", null );
            properties[PROPERTY_autoscrolls] = new PropertyDescriptor ( "autoscrolls", net.adrianromero.beans.JCalendarPanel.class, "getAutoscrolls", "setAutoscrolls" );
            properties[PROPERTY_background] = new PropertyDescriptor ( "background", net.adrianromero.beans.JCalendarPanel.class, "getBackground", "setBackground" );
            properties[PROPERTY_backgroundSet] = new PropertyDescriptor ( "backgroundSet", net.adrianromero.beans.JCalendarPanel.class, "isBackgroundSet", null );
            properties[PROPERTY_border] = new PropertyDescriptor ( "border", net.adrianromero.beans.JCalendarPanel.class, "getBorder", "setBorder" );
            properties[PROPERTY_bounds] = new PropertyDescriptor ( "bounds", net.adrianromero.beans.JCalendarPanel.class, "getBounds", "setBounds" );
            properties[PROPERTY_colorModel] = new PropertyDescriptor ( "colorModel", net.adrianromero.beans.JCalendarPanel.class, "getColorModel", null );
            properties[PROPERTY_component] = new IndexedPropertyDescriptor ( "component", net.adrianromero.beans.JCalendarPanel.class, null, null, "getComponent", null );
            properties[PROPERTY_componentCount] = new PropertyDescriptor ( "componentCount", net.adrianromero.beans.JCalendarPanel.class, "getComponentCount", null );
            properties[PROPERTY_componentListeners] = new PropertyDescriptor ( "componentListeners", net.adrianromero.beans.JCalendarPanel.class, "getComponentListeners", null );
            properties[PROPERTY_componentOrientation] = new PropertyDescriptor ( "componentOrientation", net.adrianromero.beans.JCalendarPanel.class, "getComponentOrientation", "setComponentOrientation" );
            properties[PROPERTY_componentPopupMenu] = new PropertyDescriptor ( "componentPopupMenu", net.adrianromero.beans.JCalendarPanel.class, "getComponentPopupMenu", "setComponentPopupMenu" );
            properties[PROPERTY_components] = new PropertyDescriptor ( "components", net.adrianromero.beans.JCalendarPanel.class, "getComponents", null );
            properties[PROPERTY_containerListeners] = new PropertyDescriptor ( "containerListeners", net.adrianromero.beans.JCalendarPanel.class, "getContainerListeners", null );
            properties[PROPERTY_cursor] = new PropertyDescriptor ( "cursor", net.adrianromero.beans.JCalendarPanel.class, "getCursor", "setCursor" );
            properties[PROPERTY_cursorSet] = new PropertyDescriptor ( "cursorSet", net.adrianromero.beans.JCalendarPanel.class, "isCursorSet", null );
            properties[PROPERTY_date] = new PropertyDescriptor ( "date", net.adrianromero.beans.JCalendarPanel.class, "getDate", "setDate" );
            properties[PROPERTY_debugGraphicsOptions] = new PropertyDescriptor ( "debugGraphicsOptions", net.adrianromero.beans.JCalendarPanel.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions" );
            properties[PROPERTY_displayable] = new PropertyDescriptor ( "displayable", net.adrianromero.beans.JCalendarPanel.class, "isDisplayable", null );
            properties[PROPERTY_doubleBuffered] = new PropertyDescriptor ( "doubleBuffered", net.adrianromero.beans.JCalendarPanel.class, "isDoubleBuffered", "setDoubleBuffered" );
            properties[PROPERTY_dropTarget] = new PropertyDescriptor ( "dropTarget", net.adrianromero.beans.JCalendarPanel.class, "getDropTarget", "setDropTarget" );
            properties[PROPERTY_enabled] = new PropertyDescriptor ( "enabled", net.adrianromero.beans.JCalendarPanel.class, "isEnabled", "setEnabled" );
            properties[PROPERTY_focusable] = new PropertyDescriptor ( "focusable", net.adrianromero.beans.JCalendarPanel.class, "isFocusable", "setFocusable" );
            properties[PROPERTY_focusCycleRoot] = new PropertyDescriptor ( "focusCycleRoot", net.adrianromero.beans.JCalendarPanel.class, "isFocusCycleRoot", "setFocusCycleRoot" );
            properties[PROPERTY_focusCycleRootAncestor] = new PropertyDescriptor ( "focusCycleRootAncestor", net.adrianromero.beans.JCalendarPanel.class, "getFocusCycleRootAncestor", null );
            properties[PROPERTY_focusListeners] = new PropertyDescriptor ( "focusListeners", net.adrianromero.beans.JCalendarPanel.class, "getFocusListeners", null );
            properties[PROPERTY_focusOwner] = new PropertyDescriptor ( "focusOwner", net.adrianromero.beans.JCalendarPanel.class, "isFocusOwner", null );
            properties[PROPERTY_focusTraversable] = new PropertyDescriptor ( "focusTraversable", net.adrianromero.beans.JCalendarPanel.class, "isFocusTraversable", null );
            properties[PROPERTY_focusTraversalKeys] = new IndexedPropertyDescriptor ( "focusTraversalKeys", net.adrianromero.beans.JCalendarPanel.class, null, null, "getFocusTraversalKeys", "setFocusTraversalKeys" );
            properties[PROPERTY_focusTraversalKeysEnabled] = new PropertyDescriptor ( "focusTraversalKeysEnabled", net.adrianromero.beans.JCalendarPanel.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled" );
            properties[PROPERTY_focusTraversalPolicy] = new PropertyDescriptor ( "focusTraversalPolicy", net.adrianromero.beans.JCalendarPanel.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy" );
            properties[PROPERTY_focusTraversalPolicyProvider] = new PropertyDescriptor ( "focusTraversalPolicyProvider", net.adrianromero.beans.JCalendarPanel.class, "isFocusTraversalPolicyProvider", "setFocusTraversalPolicyProvider" );
            properties[PROPERTY_focusTraversalPolicySet] = new PropertyDescriptor ( "focusTraversalPolicySet", net.adrianromero.beans.JCalendarPanel.class, "isFocusTraversalPolicySet", null );
            properties[PROPERTY_font] = new PropertyDescriptor ( "font", net.adrianromero.beans.JCalendarPanel.class, "getFont", "setFont" );
            properties[PROPERTY_fontSet] = new PropertyDescriptor ( "fontSet", net.adrianromero.beans.JCalendarPanel.class, "isFontSet", null );
            properties[PROPERTY_foreground] = new PropertyDescriptor ( "foreground", net.adrianromero.beans.JCalendarPanel.class, "getForeground", "setForeground" );
            properties[PROPERTY_foregroundSet] = new PropertyDescriptor ( "foregroundSet", net.adrianromero.beans.JCalendarPanel.class, "isForegroundSet", null );
            properties[PROPERTY_graphics] = new PropertyDescriptor ( "graphics", net.adrianromero.beans.JCalendarPanel.class, "getGraphics", null );
            properties[PROPERTY_graphicsConfiguration] = new PropertyDescriptor ( "graphicsConfiguration", net.adrianromero.beans.JCalendarPanel.class, "getGraphicsConfiguration", null );
            properties[PROPERTY_height] = new PropertyDescriptor ( "height", net.adrianromero.beans.JCalendarPanel.class, "getHeight", null );
            properties[PROPERTY_hierarchyBoundsListeners] = new PropertyDescriptor ( "hierarchyBoundsListeners", net.adrianromero.beans.JCalendarPanel.class, "getHierarchyBoundsListeners", null );
            properties[PROPERTY_hierarchyListeners] = new PropertyDescriptor ( "hierarchyListeners", net.adrianromero.beans.JCalendarPanel.class, "getHierarchyListeners", null );
            properties[PROPERTY_ignoreRepaint] = new PropertyDescriptor ( "ignoreRepaint", net.adrianromero.beans.JCalendarPanel.class, "getIgnoreRepaint", "setIgnoreRepaint" );
            properties[PROPERTY_inheritsPopupMenu] = new PropertyDescriptor ( "inheritsPopupMenu", net.adrianromero.beans.JCalendarPanel.class, "getInheritsPopupMenu", "setInheritsPopupMenu" );
            properties[PROPERTY_inputContext] = new PropertyDescriptor ( "inputContext", net.adrianromero.beans.JCalendarPanel.class, "getInputContext", null );
            properties[PROPERTY_inputMap] = new PropertyDescriptor ( "inputMap", net.adrianromero.beans.JCalendarPanel.class, "getInputMap", null );
            properties[PROPERTY_inputMethodListeners] = new PropertyDescriptor ( "inputMethodListeners", net.adrianromero.beans.JCalendarPanel.class, "getInputMethodListeners", null );
            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor ( "inputMethodRequests", net.adrianromero.beans.JCalendarPanel.class, "getInputMethodRequests", null );
            properties[PROPERTY_inputVerifier] = new PropertyDescriptor ( "inputVerifier", net.adrianromero.beans.JCalendarPanel.class, "getInputVerifier", "setInputVerifier" );
            properties[PROPERTY_insets] = new PropertyDescriptor ( "insets", net.adrianromero.beans.JCalendarPanel.class, "getInsets", null );
            properties[PROPERTY_keyListeners] = new PropertyDescriptor ( "keyListeners", net.adrianromero.beans.JCalendarPanel.class, "getKeyListeners", null );
            properties[PROPERTY_layout] = new PropertyDescriptor ( "layout", net.adrianromero.beans.JCalendarPanel.class, "getLayout", "setLayout" );
            properties[PROPERTY_lightweight] = new PropertyDescriptor ( "lightweight", net.adrianromero.beans.JCalendarPanel.class, "isLightweight", null );
            properties[PROPERTY_locale] = new PropertyDescriptor ( "locale", net.adrianromero.beans.JCalendarPanel.class, "getLocale", "setLocale" );
            properties[PROPERTY_location] = new PropertyDescriptor ( "location", net.adrianromero.beans.JCalendarPanel.class, "getLocation", "setLocation" );
            properties[PROPERTY_locationOnScreen] = new PropertyDescriptor ( "locationOnScreen", net.adrianromero.beans.JCalendarPanel.class, "getLocationOnScreen", null );
            properties[PROPERTY_managingFocus] = new PropertyDescriptor ( "managingFocus", net.adrianromero.beans.JCalendarPanel.class, "isManagingFocus", null );
            properties[PROPERTY_maximumSize] = new PropertyDescriptor ( "maximumSize", net.adrianromero.beans.JCalendarPanel.class, "getMaximumSize", "setMaximumSize" );
            properties[PROPERTY_maximumSizeSet] = new PropertyDescriptor ( "maximumSizeSet", net.adrianromero.beans.JCalendarPanel.class, "isMaximumSizeSet", null );
            properties[PROPERTY_minimumSize] = new PropertyDescriptor ( "minimumSize", net.adrianromero.beans.JCalendarPanel.class, "getMinimumSize", "setMinimumSize" );
            properties[PROPERTY_minimumSizeSet] = new PropertyDescriptor ( "minimumSizeSet", net.adrianromero.beans.JCalendarPanel.class, "isMinimumSizeSet", null );
            properties[PROPERTY_mouseListeners] = new PropertyDescriptor ( "mouseListeners", net.adrianromero.beans.JCalendarPanel.class, "getMouseListeners", null );
            properties[PROPERTY_mouseMotionListeners] = new PropertyDescriptor ( "mouseMotionListeners", net.adrianromero.beans.JCalendarPanel.class, "getMouseMotionListeners", null );
            properties[PROPERTY_mousePosition] = new PropertyDescriptor ( "mousePosition", net.adrianromero.beans.JCalendarPanel.class, "getMousePosition", null );
            properties[PROPERTY_mouseWheelListeners] = new PropertyDescriptor ( "mouseWheelListeners", net.adrianromero.beans.JCalendarPanel.class, "getMouseWheelListeners", null );
            properties[PROPERTY_name] = new PropertyDescriptor ( "name", net.adrianromero.beans.JCalendarPanel.class, "getName", "setName" );
            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor ( "nextFocusableComponent", net.adrianromero.beans.JCalendarPanel.class, "getNextFocusableComponent", "setNextFocusableComponent" );
            properties[PROPERTY_opaque] = new PropertyDescriptor ( "opaque", net.adrianromero.beans.JCalendarPanel.class, "isOpaque", "setOpaque" );
            properties[PROPERTY_optimizedDrawingEnabled] = new PropertyDescriptor ( "optimizedDrawingEnabled", net.adrianromero.beans.JCalendarPanel.class, "isOptimizedDrawingEnabled", null );
            properties[PROPERTY_paintingTile] = new PropertyDescriptor ( "paintingTile", net.adrianromero.beans.JCalendarPanel.class, "isPaintingTile", null );
            properties[PROPERTY_parent] = new PropertyDescriptor ( "parent", net.adrianromero.beans.JCalendarPanel.class, "getParent", null );
            properties[PROPERTY_peer] = new PropertyDescriptor ( "peer", net.adrianromero.beans.JCalendarPanel.class, "getPeer", null );
            properties[PROPERTY_preferredSize] = new PropertyDescriptor ( "preferredSize", net.adrianromero.beans.JCalendarPanel.class, "getPreferredSize", "setPreferredSize" );
            properties[PROPERTY_preferredSizeSet] = new PropertyDescriptor ( "preferredSizeSet", net.adrianromero.beans.JCalendarPanel.class, "isPreferredSizeSet", null );
            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor ( "propertyChangeListeners", net.adrianromero.beans.JCalendarPanel.class, "getPropertyChangeListeners", null );
            properties[PROPERTY_registeredKeyStrokes] = new PropertyDescriptor ( "registeredKeyStrokes", net.adrianromero.beans.JCalendarPanel.class, "getRegisteredKeyStrokes", null );
            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor ( "requestFocusEnabled", net.adrianromero.beans.JCalendarPanel.class, "isRequestFocusEnabled", "setRequestFocusEnabled" );
            properties[PROPERTY_rootPane] = new PropertyDescriptor ( "rootPane", net.adrianromero.beans.JCalendarPanel.class, "getRootPane", null );
            properties[PROPERTY_showing] = new PropertyDescriptor ( "showing", net.adrianromero.beans.JCalendarPanel.class, "isShowing", null );
            properties[PROPERTY_size] = new PropertyDescriptor ( "size", net.adrianromero.beans.JCalendarPanel.class, "getSize", "setSize" );
            properties[PROPERTY_toolkit] = new PropertyDescriptor ( "toolkit", net.adrianromero.beans.JCalendarPanel.class, "getToolkit", null );
            properties[PROPERTY_toolTipText] = new PropertyDescriptor ( "toolTipText", net.adrianromero.beans.JCalendarPanel.class, "getToolTipText", "setToolTipText" );
            properties[PROPERTY_topLevelAncestor] = new PropertyDescriptor ( "topLevelAncestor", net.adrianromero.beans.JCalendarPanel.class, "getTopLevelAncestor", null );
            properties[PROPERTY_transferHandler] = new PropertyDescriptor ( "transferHandler", net.adrianromero.beans.JCalendarPanel.class, "getTransferHandler", "setTransferHandler" );
            properties[PROPERTY_treeLock] = new PropertyDescriptor ( "treeLock", net.adrianromero.beans.JCalendarPanel.class, "getTreeLock", null );
            properties[PROPERTY_UI] = new PropertyDescriptor ( "UI", net.adrianromero.beans.JCalendarPanel.class, "getUI", "setUI" );
            properties[PROPERTY_UIClassID] = new PropertyDescriptor ( "UIClassID", net.adrianromero.beans.JCalendarPanel.class, "getUIClassID", null );
            properties[PROPERTY_valid] = new PropertyDescriptor ( "valid", net.adrianromero.beans.JCalendarPanel.class, "isValid", null );
            properties[PROPERTY_validateRoot] = new PropertyDescriptor ( "validateRoot", net.adrianromero.beans.JCalendarPanel.class, "isValidateRoot", null );
            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor ( "verifyInputWhenFocusTarget", net.adrianromero.beans.JCalendarPanel.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget" );
            properties[PROPERTY_vetoableChangeListeners] = new PropertyDescriptor ( "vetoableChangeListeners", net.adrianromero.beans.JCalendarPanel.class, "getVetoableChangeListeners", null );
            properties[PROPERTY_visible] = new PropertyDescriptor ( "visible", net.adrianromero.beans.JCalendarPanel.class, "isVisible", "setVisible" );
            properties[PROPERTY_visibleRect] = new PropertyDescriptor ( "visibleRect", net.adrianromero.beans.JCalendarPanel.class, "getVisibleRect", null );
            properties[PROPERTY_width] = new PropertyDescriptor ( "width", net.adrianromero.beans.JCalendarPanel.class, "getWidth", null );
            properties[PROPERTY_x] = new PropertyDescriptor ( "x", net.adrianromero.beans.JCalendarPanel.class, "getX", null );
            properties[PROPERTY_y] = new PropertyDescriptor ( "y", net.adrianromero.beans.JCalendarPanel.class, "getY", null );
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
            eventSets[EVENT_ancestorListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[] {"ancestorAdded", "ancestorMoved", "ancestorRemoved"}, "addAncestorListener", "removeAncestorListener" );
            eventSets[EVENT_componentListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "componentListener", java.awt.event.ComponentListener.class, new String[] {"componentHidden", "componentMoved", "componentResized", "componentShown"}, "addComponentListener", "removeComponentListener" );
            eventSets[EVENT_containerListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "containerListener", java.awt.event.ContainerListener.class, new String[] {"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener" );
            eventSets[EVENT_focusListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "focusListener", java.awt.event.FocusListener.class, new String[] {"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener" );
            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[] {"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener" );
            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[] {"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener" );
            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[] {"caretPositionChanged", "inputMethodTextChanged"}, "addInputMethodListener", "removeInputMethodListener" );
            eventSets[EVENT_keyListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "keyListener", java.awt.event.KeyListener.class, new String[] {"keyPressed", "keyReleased", "keyTyped"}, "addKeyListener", "removeKeyListener" );
            eventSets[EVENT_mouseListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "mouseListener", java.awt.event.MouseListener.class, new String[] {"mouseClicked", "mouseEntered", "mouseExited", "mousePressed", "mouseReleased"}, "addMouseListener", "removeMouseListener" );
            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[] {"mouseDragged", "mouseMoved"}, "addMouseMotionListener", "removeMouseMotionListener" );
            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[] {"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener" );
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" );
            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor ( net.adrianromero.beans.JCalendarPanel.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[] {"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener" );
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
    private static final int METHOD_deliverEvent14 = 14;
    private static final int METHOD_disable15 = 15;
    private static final int METHOD_dispatchEvent16 = 16;
    private static final int METHOD_doLayout17 = 17;
    private static final int METHOD_enable18 = 18;
    private static final int METHOD_enableInputMethods19 = 19;
    private static final int METHOD_findComponentAt20 = 20;
    private static final int METHOD_firePropertyChange21 = 21;
    private static final int METHOD_getActionForKeyStroke22 = 22;
    private static final int METHOD_getBounds23 = 23;
    private static final int METHOD_getClientProperty24 = 24;
    private static final int METHOD_getComponentAt25 = 25;
    private static final int METHOD_getComponentZOrder26 = 26;
    private static final int METHOD_getConditionForKeyStroke27 = 27;
    private static final int METHOD_getDefaultLocale28 = 28;
    private static final int METHOD_getFontMetrics29 = 29;
    private static final int METHOD_getInsets30 = 30;
    private static final int METHOD_getListeners31 = 31;
    private static final int METHOD_getLocation32 = 32;
    private static final int METHOD_getMousePosition33 = 33;
    private static final int METHOD_getPopupLocation34 = 34;
    private static final int METHOD_getPropertyChangeListeners35 = 35;
    private static final int METHOD_getSize36 = 36;
    private static final int METHOD_getToolTipLocation37 = 37;
    private static final int METHOD_getToolTipText38 = 38;
    private static final int METHOD_gotFocus39 = 39;
    private static final int METHOD_grabFocus40 = 40;
    private static final int METHOD_handleEvent41 = 41;
    private static final int METHOD_hasFocus42 = 42;
    private static final int METHOD_hide43 = 43;
    private static final int METHOD_imageUpdate44 = 44;
    private static final int METHOD_insets45 = 45;
    private static final int METHOD_inside46 = 46;
    private static final int METHOD_invalidate47 = 47;
    private static final int METHOD_isAncestorOf48 = 48;
    private static final int METHOD_isFocusCycleRoot49 = 49;
    private static final int METHOD_isLightweightComponent50 = 50;
    private static final int METHOD_keyDown51 = 51;
    private static final int METHOD_keyUp52 = 52;
    private static final int METHOD_layout53 = 53;
    private static final int METHOD_list54 = 54;
    private static final int METHOD_locate55 = 55;
    private static final int METHOD_location56 = 56;
    private static final int METHOD_lostFocus57 = 57;
    private static final int METHOD_minimumSize58 = 58;
    private static final int METHOD_mouseDown59 = 59;
    private static final int METHOD_mouseDrag60 = 60;
    private static final int METHOD_mouseEnter61 = 61;
    private static final int METHOD_mouseExit62 = 62;
    private static final int METHOD_mouseMove63 = 63;
    private static final int METHOD_mouseUp64 = 64;
    private static final int METHOD_move65 = 65;
    private static final int METHOD_nextFocus66 = 66;
    private static final int METHOD_paint67 = 67;
    private static final int METHOD_paintAll68 = 68;
    private static final int METHOD_paintComponents69 = 69;
    private static final int METHOD_paintImmediately70 = 70;
    private static final int METHOD_postEvent71 = 71;
    private static final int METHOD_preferredSize72 = 72;
    private static final int METHOD_prepareImage73 = 73;
    private static final int METHOD_print74 = 74;
    private static final int METHOD_printAll75 = 75;
    private static final int METHOD_printComponents76 = 76;
    private static final int METHOD_putClientProperty77 = 77;
    private static final int METHOD_registerKeyboardAction78 = 78;
    private static final int METHOD_remove79 = 79;
    private static final int METHOD_removeAll80 = 80;
    private static final int METHOD_removeNotify81 = 81;
    private static final int METHOD_removePropertyChangeListener82 = 82;
    private static final int METHOD_repaint83 = 83;
    private static final int METHOD_requestDefaultFocus84 = 84;
    private static final int METHOD_requestFocus85 = 85;
    private static final int METHOD_requestFocusInWindow86 = 86;
    private static final int METHOD_resetKeyboardActions87 = 87;
    private static final int METHOD_reshape88 = 88;
    private static final int METHOD_resize89 = 89;
    private static final int METHOD_revalidate90 = 90;
    private static final int METHOD_scrollRectToVisible91 = 91;
    private static final int METHOD_setBounds92 = 92;
    private static final int METHOD_setComponentZOrder93 = 93;
    private static final int METHOD_setDefaultLocale94 = 94;
    private static final int METHOD_show95 = 95;
    private static final int METHOD_size96 = 96;
    private static final int METHOD_toString97 = 97;
    private static final int METHOD_transferFocus98 = 98;
    private static final int METHOD_transferFocusBackward99 = 99;
    private static final int METHOD_transferFocusDownCycle100 = 100;
    private static final int METHOD_transferFocusUpCycle101 = 101;
    private static final int METHOD_unregisterKeyboardAction102 = 102;
    private static final int METHOD_update103 = 103;
    private static final int METHOD_updateUI104 = 104;
    private static final int METHOD_validate105 = 105;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[106];
    
        try {
            methods[METHOD_action0] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("action", new Class[] {java.awt.Event.class, java.lang.Object.class}));
            methods[METHOD_action0].setDisplayName ( "" );
            methods[METHOD_add1] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("add", new Class[] {java.awt.Component.class}));
            methods[METHOD_add1].setDisplayName ( "" );
            methods[METHOD_addNotify2] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("addNotify", new Class[] {}));
            methods[METHOD_addNotify2].setDisplayName ( "" );
            methods[METHOD_addPropertyChangeListener3] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("addPropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class}));
            methods[METHOD_addPropertyChangeListener3].setDisplayName ( "" );
            methods[METHOD_applyComponentOrientation4] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("applyComponentOrientation", new Class[] {java.awt.ComponentOrientation.class}));
            methods[METHOD_applyComponentOrientation4].setDisplayName ( "" );
            methods[METHOD_areFocusTraversalKeysSet5] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("areFocusTraversalKeysSet", new Class[] {Integer.TYPE}));
            methods[METHOD_areFocusTraversalKeysSet5].setDisplayName ( "" );
            methods[METHOD_bounds6] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("bounds", new Class[] {}));
            methods[METHOD_bounds6].setDisplayName ( "" );
            methods[METHOD_checkImage7] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("checkImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class}));
            methods[METHOD_checkImage7].setDisplayName ( "" );
            methods[METHOD_computeVisibleRect8] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("computeVisibleRect", new Class[] {java.awt.Rectangle.class}));
            methods[METHOD_computeVisibleRect8].setDisplayName ( "" );
            methods[METHOD_contains9] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("contains", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_contains9].setDisplayName ( "" );
            methods[METHOD_countComponents10] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("countComponents", new Class[] {}));
            methods[METHOD_countComponents10].setDisplayName ( "" );
            methods[METHOD_createImage11] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("createImage", new Class[] {java.awt.image.ImageProducer.class}));
            methods[METHOD_createImage11].setDisplayName ( "" );
            methods[METHOD_createToolTip12] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("createToolTip", new Class[] {}));
            methods[METHOD_createToolTip12].setDisplayName ( "" );
            methods[METHOD_createVolatileImage13] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("createVolatileImage", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_createVolatileImage13].setDisplayName ( "" );
            methods[METHOD_deliverEvent14] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("deliverEvent", new Class[] {java.awt.Event.class}));
            methods[METHOD_deliverEvent14].setDisplayName ( "" );
            methods[METHOD_disable15] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("disable", new Class[] {}));
            methods[METHOD_disable15].setDisplayName ( "" );
            methods[METHOD_dispatchEvent16] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("dispatchEvent", new Class[] {java.awt.AWTEvent.class}));
            methods[METHOD_dispatchEvent16].setDisplayName ( "" );
            methods[METHOD_doLayout17] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("doLayout", new Class[] {}));
            methods[METHOD_doLayout17].setDisplayName ( "" );
            methods[METHOD_enable18] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("enable", new Class[] {}));
            methods[METHOD_enable18].setDisplayName ( "" );
            methods[METHOD_enableInputMethods19] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("enableInputMethods", new Class[] {Boolean.TYPE}));
            methods[METHOD_enableInputMethods19].setDisplayName ( "" );
            methods[METHOD_findComponentAt20] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("findComponentAt", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_findComponentAt20].setDisplayName ( "" );
            methods[METHOD_firePropertyChange21] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, Boolean.TYPE, Boolean.TYPE}));
            methods[METHOD_firePropertyChange21].setDisplayName ( "" );
            methods[METHOD_getActionForKeyStroke22] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getActionForKeyStroke", new Class[] {javax.swing.KeyStroke.class}));
            methods[METHOD_getActionForKeyStroke22].setDisplayName ( "" );
            methods[METHOD_getBounds23] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getBounds", new Class[] {java.awt.Rectangle.class}));
            methods[METHOD_getBounds23].setDisplayName ( "" );
            methods[METHOD_getClientProperty24] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getClientProperty", new Class[] {java.lang.Object.class}));
            methods[METHOD_getClientProperty24].setDisplayName ( "" );
            methods[METHOD_getComponentAt25] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getComponentAt", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_getComponentAt25].setDisplayName ( "" );
            methods[METHOD_getComponentZOrder26] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getComponentZOrder", new Class[] {java.awt.Component.class}));
            methods[METHOD_getComponentZOrder26].setDisplayName ( "" );
            methods[METHOD_getConditionForKeyStroke27] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getConditionForKeyStroke", new Class[] {javax.swing.KeyStroke.class}));
            methods[METHOD_getConditionForKeyStroke27].setDisplayName ( "" );
            methods[METHOD_getDefaultLocale28] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getDefaultLocale", new Class[] {}));
            methods[METHOD_getDefaultLocale28].setDisplayName ( "" );
            methods[METHOD_getFontMetrics29] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getFontMetrics", new Class[] {java.awt.Font.class}));
            methods[METHOD_getFontMetrics29].setDisplayName ( "" );
            methods[METHOD_getInsets30] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getInsets", new Class[] {java.awt.Insets.class}));
            methods[METHOD_getInsets30].setDisplayName ( "" );
            methods[METHOD_getListeners31] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getListeners", new Class[] {java.lang.Class.class}));
            methods[METHOD_getListeners31].setDisplayName ( "" );
            methods[METHOD_getLocation32] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getLocation", new Class[] {java.awt.Point.class}));
            methods[METHOD_getLocation32].setDisplayName ( "" );
            methods[METHOD_getMousePosition33] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getMousePosition", new Class[] {Boolean.TYPE}));
            methods[METHOD_getMousePosition33].setDisplayName ( "" );
            methods[METHOD_getPopupLocation34] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getPopupLocation", new Class[] {java.awt.event.MouseEvent.class}));
            methods[METHOD_getPopupLocation34].setDisplayName ( "" );
            methods[METHOD_getPropertyChangeListeners35] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getPropertyChangeListeners", new Class[] {java.lang.String.class}));
            methods[METHOD_getPropertyChangeListeners35].setDisplayName ( "" );
            methods[METHOD_getSize36] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getSize", new Class[] {java.awt.Dimension.class}));
            methods[METHOD_getSize36].setDisplayName ( "" );
            methods[METHOD_getToolTipLocation37] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getToolTipLocation", new Class[] {java.awt.event.MouseEvent.class}));
            methods[METHOD_getToolTipLocation37].setDisplayName ( "" );
            methods[METHOD_getToolTipText38] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("getToolTipText", new Class[] {java.awt.event.MouseEvent.class}));
            methods[METHOD_getToolTipText38].setDisplayName ( "" );
            methods[METHOD_gotFocus39] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("gotFocus", new Class[] {java.awt.Event.class, java.lang.Object.class}));
            methods[METHOD_gotFocus39].setDisplayName ( "" );
            methods[METHOD_grabFocus40] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("grabFocus", new Class[] {}));
            methods[METHOD_grabFocus40].setDisplayName ( "" );
            methods[METHOD_handleEvent41] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("handleEvent", new Class[] {java.awt.Event.class}));
            methods[METHOD_handleEvent41].setDisplayName ( "" );
            methods[METHOD_hasFocus42] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("hasFocus", new Class[] {}));
            methods[METHOD_hasFocus42].setDisplayName ( "" );
            methods[METHOD_hide43] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("hide", new Class[] {}));
            methods[METHOD_hide43].setDisplayName ( "" );
            methods[METHOD_imageUpdate44] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("imageUpdate", new Class[] {java.awt.Image.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_imageUpdate44].setDisplayName ( "" );
            methods[METHOD_insets45] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("insets", new Class[] {}));
            methods[METHOD_insets45].setDisplayName ( "" );
            methods[METHOD_inside46] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("inside", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_inside46].setDisplayName ( "" );
            methods[METHOD_invalidate47] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("invalidate", new Class[] {}));
            methods[METHOD_invalidate47].setDisplayName ( "" );
            methods[METHOD_isAncestorOf48] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("isAncestorOf", new Class[] {java.awt.Component.class}));
            methods[METHOD_isAncestorOf48].setDisplayName ( "" );
            methods[METHOD_isFocusCycleRoot49] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("isFocusCycleRoot", new Class[] {java.awt.Container.class}));
            methods[METHOD_isFocusCycleRoot49].setDisplayName ( "" );
            methods[METHOD_isLightweightComponent50] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("isLightweightComponent", new Class[] {java.awt.Component.class}));
            methods[METHOD_isLightweightComponent50].setDisplayName ( "" );
            methods[METHOD_keyDown51] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("keyDown", new Class[] {java.awt.Event.class, Integer.TYPE}));
            methods[METHOD_keyDown51].setDisplayName ( "" );
            methods[METHOD_keyUp52] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("keyUp", new Class[] {java.awt.Event.class, Integer.TYPE}));
            methods[METHOD_keyUp52].setDisplayName ( "" );
            methods[METHOD_layout53] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("layout", new Class[] {}));
            methods[METHOD_layout53].setDisplayName ( "" );
            methods[METHOD_list54] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("list", new Class[] {java.io.PrintStream.class, Integer.TYPE}));
            methods[METHOD_list54].setDisplayName ( "" );
            methods[METHOD_locate55] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("locate", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_locate55].setDisplayName ( "" );
            methods[METHOD_location56] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("location", new Class[] {}));
            methods[METHOD_location56].setDisplayName ( "" );
            methods[METHOD_lostFocus57] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("lostFocus", new Class[] {java.awt.Event.class, java.lang.Object.class}));
            methods[METHOD_lostFocus57].setDisplayName ( "" );
            methods[METHOD_minimumSize58] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("minimumSize", new Class[] {}));
            methods[METHOD_minimumSize58].setDisplayName ( "" );
            methods[METHOD_mouseDown59] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("mouseDown", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseDown59].setDisplayName ( "" );
            methods[METHOD_mouseDrag60] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("mouseDrag", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseDrag60].setDisplayName ( "" );
            methods[METHOD_mouseEnter61] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("mouseEnter", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseEnter61].setDisplayName ( "" );
            methods[METHOD_mouseExit62] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("mouseExit", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseExit62].setDisplayName ( "" );
            methods[METHOD_mouseMove63] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("mouseMove", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseMove63].setDisplayName ( "" );
            methods[METHOD_mouseUp64] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("mouseUp", new Class[] {java.awt.Event.class, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_mouseUp64].setDisplayName ( "" );
            methods[METHOD_move65] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("move", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_move65].setDisplayName ( "" );
            methods[METHOD_nextFocus66] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("nextFocus", new Class[] {}));
            methods[METHOD_nextFocus66].setDisplayName ( "" );
            methods[METHOD_paint67] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("paint", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_paint67].setDisplayName ( "" );
            methods[METHOD_paintAll68] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("paintAll", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_paintAll68].setDisplayName ( "" );
            methods[METHOD_paintComponents69] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("paintComponents", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_paintComponents69].setDisplayName ( "" );
            methods[METHOD_paintImmediately70] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("paintImmediately", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_paintImmediately70].setDisplayName ( "" );
            methods[METHOD_postEvent71] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("postEvent", new Class[] {java.awt.Event.class}));
            methods[METHOD_postEvent71].setDisplayName ( "" );
            methods[METHOD_preferredSize72] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("preferredSize", new Class[] {}));
            methods[METHOD_preferredSize72].setDisplayName ( "" );
            methods[METHOD_prepareImage73] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class}));
            methods[METHOD_prepareImage73].setDisplayName ( "" );
            methods[METHOD_print74] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("print", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_print74].setDisplayName ( "" );
            methods[METHOD_printAll75] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("printAll", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_printAll75].setDisplayName ( "" );
            methods[METHOD_printComponents76] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("printComponents", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_printComponents76].setDisplayName ( "" );
            methods[METHOD_putClientProperty77] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("putClientProperty", new Class[] {java.lang.Object.class, java.lang.Object.class}));
            methods[METHOD_putClientProperty77].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction78] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, Integer.TYPE}));
            methods[METHOD_registerKeyboardAction78].setDisplayName ( "" );
            methods[METHOD_remove79] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("remove", new Class[] {Integer.TYPE}));
            methods[METHOD_remove79].setDisplayName ( "" );
            methods[METHOD_removeAll80] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("removeAll", new Class[] {}));
            methods[METHOD_removeAll80].setDisplayName ( "" );
            methods[METHOD_removeNotify81] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("removeNotify", new Class[] {}));
            methods[METHOD_removeNotify81].setDisplayName ( "" );
            methods[METHOD_removePropertyChangeListener82] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("removePropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class}));
            methods[METHOD_removePropertyChangeListener82].setDisplayName ( "" );
            methods[METHOD_repaint83] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("repaint", new Class[] {Long.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_repaint83].setDisplayName ( "" );
            methods[METHOD_requestDefaultFocus84] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("requestDefaultFocus", new Class[] {}));
            methods[METHOD_requestDefaultFocus84].setDisplayName ( "" );
            methods[METHOD_requestFocus85] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("requestFocus", new Class[] {}));
            methods[METHOD_requestFocus85].setDisplayName ( "" );
            methods[METHOD_requestFocusInWindow86] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("requestFocusInWindow", new Class[] {}));
            methods[METHOD_requestFocusInWindow86].setDisplayName ( "" );
            methods[METHOD_resetKeyboardActions87] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("resetKeyboardActions", new Class[] {}));
            methods[METHOD_resetKeyboardActions87].setDisplayName ( "" );
            methods[METHOD_reshape88] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("reshape", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_reshape88].setDisplayName ( "" );
            methods[METHOD_resize89] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("resize", new Class[] {Integer.TYPE, Integer.TYPE}));
            methods[METHOD_resize89].setDisplayName ( "" );
            methods[METHOD_revalidate90] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("revalidate", new Class[] {}));
            methods[METHOD_revalidate90].setDisplayName ( "" );
            methods[METHOD_scrollRectToVisible91] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("scrollRectToVisible", new Class[] {java.awt.Rectangle.class}));
            methods[METHOD_scrollRectToVisible91].setDisplayName ( "" );
            methods[METHOD_setBounds92] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("setBounds", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}));
            methods[METHOD_setBounds92].setDisplayName ( "" );
            methods[METHOD_setComponentZOrder93] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("setComponentZOrder", new Class[] {java.awt.Component.class, Integer.TYPE}));
            methods[METHOD_setComponentZOrder93].setDisplayName ( "" );
            methods[METHOD_setDefaultLocale94] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("setDefaultLocale", new Class[] {java.util.Locale.class}));
            methods[METHOD_setDefaultLocale94].setDisplayName ( "" );
            methods[METHOD_show95] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("show", new Class[] {}));
            methods[METHOD_show95].setDisplayName ( "" );
            methods[METHOD_size96] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("size", new Class[] {}));
            methods[METHOD_size96].setDisplayName ( "" );
            methods[METHOD_toString97] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("toString", new Class[] {}));
            methods[METHOD_toString97].setDisplayName ( "" );
            methods[METHOD_transferFocus98] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("transferFocus", new Class[] {}));
            methods[METHOD_transferFocus98].setDisplayName ( "" );
            methods[METHOD_transferFocusBackward99] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("transferFocusBackward", new Class[] {}));
            methods[METHOD_transferFocusBackward99].setDisplayName ( "" );
            methods[METHOD_transferFocusDownCycle100] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("transferFocusDownCycle", new Class[] {}));
            methods[METHOD_transferFocusDownCycle100].setDisplayName ( "" );
            methods[METHOD_transferFocusUpCycle101] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("transferFocusUpCycle", new Class[] {}));
            methods[METHOD_transferFocusUpCycle101].setDisplayName ( "" );
            methods[METHOD_unregisterKeyboardAction102] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("unregisterKeyboardAction", new Class[] {javax.swing.KeyStroke.class}));
            methods[METHOD_unregisterKeyboardAction102].setDisplayName ( "" );
            methods[METHOD_update103] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("update", new Class[] {java.awt.Graphics.class}));
            methods[METHOD_update103].setDisplayName ( "" );
            methods[METHOD_updateUI104] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("updateUI", new Class[] {}));
            methods[METHOD_updateUI104].setDisplayName ( "" );
            methods[METHOD_validate105] = new MethodDescriptor ( net.adrianromero.beans.JCalendarPanel.class.getMethod("validate", new Class[] {}));
            methods[METHOD_validate105].setDisplayName ( "" );
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

