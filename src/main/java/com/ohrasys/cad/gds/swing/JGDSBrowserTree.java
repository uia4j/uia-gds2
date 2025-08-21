/*
 * Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package com.ohrasys.cad.gds.swing;

import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.ohrasys.cad.gds.GDSAccessControl;
import com.ohrasys.cad.gds.dao.ArrayReference;
import com.ohrasys.cad.gds.dao.Boundary;
import com.ohrasys.cad.gds.dao.Database;
import com.ohrasys.cad.gds.dao.Format;
import com.ohrasys.cad.gds.dao.GeometryElement;
import com.ohrasys.cad.gds.dao.Node;
import com.ohrasys.cad.gds.dao.Path;
import com.ohrasys.cad.gds.dao.Property;
import com.ohrasys.cad.gds.dao.Structure;
import com.ohrasys.cad.gds.dao.StructureReference;
import com.ohrasys.cad.gds.dao.Text;

/**
 * A GDS specialization of the JTree swing object used to display GDS elements
 * as represented by the objects in the <code>com.ohrasys.cad.gds.dao</code>
 * package.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.4 $, $Date: 2005/05/18 18:22:38 $
 */
public class JGDSBrowserTree extends JTree {

    /**
     *
     */
    private static final long serialVersionUID = -7463327910853140795L;

    /** The resource bundle */
    private static final String bundle = "com/ohrasys/cad/gds/swing/JGDSSwingProperties" /* NOI18N */;

    /** Fonts node label */
    private static final String FONTS_LABEL = "Fonts" /*NOI18N*/;

    /** Reference libraries label */
    private static final String REF_LIBS_LABEL = "Reference Libraries" /*NOI18N*/;

    /** Access control label */
    private static final String ACCESS_CONTROLS_LABEL = "Access Controls" /*NOI18N*/;

    /** Structures label */
    private static final String STRUCTURES_LABEL = "Structures" /*NOI18N*/;

    /** Masks label */
    private static final String MASKS_LABEL = "Masks" /*NOI18N*/;

    /** Geometry element label */
    private static final String GEO_ELEMENTS_LABEL = "Geometry Elements" /*NOI18N*/;

    /** Reference element label */
    private static final String REF_ELEMENTS_LABEL = "Reference Elements" /*NOI18N*/;

    /** Properties label */
    private static final String PROPS_LABEL = "Properties" /*NOI18N*/;

    /** No design label */
    private static final String NO_DESIGN_LABEL = "No design loaded" /*NOI18N*/;

    /** The resource bundle */
    private ResourceBundle i18n;

    /**
     * Creates a new instance of JGDSBrowserTree
     */
    public JGDSBrowserTree() {
        super();
        this.i18n = ResourceBundle.getBundle(bundle);
        setCellRenderer(new GDSTreeRenderer());
        setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
    }

    /**
     * Creates a new JGDSBrowserTree object.
     *
     * @param  root  The root node of the tree
     */
    public JGDSBrowserTree(TreeNode root) {
        super(root);
        this.i18n = ResourceBundle.getBundle(bundle);
        setCellRenderer(new GDSTreeRenderer());
    }

    /**
     * Builds the tree for a given Database DAO object
     *
     * @param  db  The Database object to build the tree for
     *
     * @see    Database
     */
    public void buildTree(Database db) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode();
        top = buildTree(top, db);
        setModel(new DefaultTreeModel(top));
    }

    /**
     * Returns a string representation of the tree
     *
     * @return  A string representation of the tree
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Helper method used to build the portion of the GDS tree specific to fonts.
     *
     * @param   top    The node to build off of
     * @param   fonts  The list of font names
     *
     * @return  The root node of the fonts tree
     */
    private DefaultMutableTreeNode buildFontsTree(
            DefaultMutableTreeNode top,
            String fonts[]) {
        if ((fonts == null) || (fonts.length == 0)) {
            return top;
        }
        DefaultMutableTreeNode fontsNode = new DefaultMutableTreeNode(FONTS_LABEL);
        for (int i = 0; i < fonts.length; i++) {
            fontsNode.add(new DefaultMutableTreeNode(fonts[i]));
        }
        top.add(fontsNode);

        return top;
    }

    /**
     * Helper method used to build the portion of the GDS tree specific to
     * reference libraries
     *
     * @param   top      The node to build off of
     * @param   refLibs  The list of reference library names
     *
     * @return  The root node of the reference library tree
     */
    private DefaultMutableTreeNode buildReflibsTree(
            DefaultMutableTreeNode top,
            String refLibs[]) {
        if ((refLibs == null) || (refLibs.length == 0)) {
            return top;
        }
        DefaultMutableTreeNode libsNode = new DefaultMutableTreeNode(
                REF_LIBS_LABEL);
        for (int i = 0; i < refLibs.length; i++) {
            libsNode.add(new DefaultMutableTreeNode(refLibs[i]));
        }
        top.add(libsNode);

        return top;
    }

    /**
     * Recursive method of building the GDS browser tree
     *
     * @param   top  The root node of the tree
     * @param   obj  The object to add to the tree
     *
     * @return  The root node of the tree to which the object was added
     */
    private DefaultMutableTreeNode buildTree(
            DefaultMutableTreeNode top,
            Object obj) {
        if (obj == null) {
            return top;
        }

        if (obj instanceof Database) {
            Database db = (Database) obj;
            top.setUserObject(db);
            top = buildTree(top, db.getAccessControls());
            top = buildTree(top, db.getFormat());
            top = buildFontsTree(top, db.getFonts());
            top = buildReflibsTree(top, db.getRefLibs());
            top = buildTree(top, db.getStructures());
        }
        else if (obj instanceof GDSAccessControl[]) {
            GDSAccessControl acl[] = (GDSAccessControl[]) obj;
            if ((acl == null) || (acl.length == 0)) {
                return top;
            }
            DefaultMutableTreeNode aclNode = new DefaultMutableTreeNode(
                    ACCESS_CONTROLS_LABEL);
            for (int i = 0; i < acl.length; i++) {
                aclNode.add(new DefaultMutableTreeNode(acl[i]));
            }
            top.add(aclNode);
        }
        else if (obj instanceof Structure[]) {
            Structure structs[] = (Structure[]) obj;
            if ((structs == null) || (structs.length == 0)) {
                return top;
            }
            DefaultMutableTreeNode strNode = new DefaultMutableTreeNode(
                    STRUCTURES_LABEL);
            for (int i = 0; i < structs.length; i++) {
                strNode = buildTree(strNode, structs[i]);
            }
            top.add(strNode);
        }
        else if (obj instanceof Structure) {
            DefaultMutableTreeNode strNode = new DefaultMutableTreeNode(obj);
            strNode = buildTree(strNode, ((Structure) obj).getElements());
            top.add(strNode);
        }
        else if (obj instanceof Format) {
            Format fmt = (Format) obj;
            if ((fmt.getMasks() == null) || (fmt.getMasks().length == 0)) {
                return top;
            }
            DefaultMutableTreeNode masksNode = new DefaultMutableTreeNode(
                    MASKS_LABEL);
            String masks[] = fmt.getMasks();
            for (int i = 0; i < masks.length; i++) {
                masksNode.add(new DefaultMutableTreeNode(masks[i]));
            }
            top.add(masksNode);
        }
        else if (obj instanceof com.ohrasys.cad.gds.dao.Element[]) {
            com.ohrasys.cad.gds.dao.Element elements[] = (com.ohrasys.cad.gds.dao.Element[]) obj;
            if ((elements == null) || (elements.length == 0)) {
                return top;
            }
            DefaultMutableTreeNode geoElemNode = null;
            DefaultMutableTreeNode refElemNode = null;
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] instanceof GeometryElement) {
                    if (geoElemNode == null) {
                        geoElemNode = new DefaultMutableTreeNode(GEO_ELEMENTS_LABEL);
                    }
                    geoElemNode = buildTree(geoElemNode, elements[i]);
                }
                else {
                    if (refElemNode == null) {
                        refElemNode = new DefaultMutableTreeNode(REF_ELEMENTS_LABEL);
                    }
                    refElemNode = buildTree(refElemNode, elements[i]);
                }
            }
            if (geoElemNode != null) {
                top.add(geoElemNode);
            }
            if (refElemNode != null) {
                top.add(refElemNode);
            }
        }
        else if (obj instanceof com.ohrasys.cad.gds.dao.Element) {
            DefaultMutableTreeNode elemNode = new DefaultMutableTreeNode(obj);
            elemNode = buildTree(elemNode,
                    ((com.ohrasys.cad.gds.dao.Element) obj).getProperties());
            top.add(elemNode);
        }
        else if (obj instanceof Property[]) {
            Property props[] = (Property[]) obj;
            if ((props == null) || (props.length == 0)) {
                return top;
            }
            DefaultMutableTreeNode propsNode = new DefaultMutableTreeNode(
                    PROPS_LABEL);
            for (int i = 0; i < props.length; i++) {
                propsNode.add(new DefaultMutableTreeNode(props[i]));
            }
            top.add(propsNode);
        }

        return top;
    } // end method buildTree

    /**
     * A custom tree cell renderer for GDS tree nodes
     *
     * @author  $Author: tvaline $
     */
    private final class GDSTreeRenderer extends DefaultTreeCellRenderer {

        /**
         *
         */
        private static final long serialVersionUID = -6612597096812546434L;

        /** Icons for various nodes of the GDS browser tree */
        private Icon dbIcon, aclIcon, fontsIcon, libsIcon, structsIcon, acIcon,
                structIcon, fontIcon, libIcon, refIcon, aryRefIcon, nodeIcon,
                pathIcon, bdyIcon, boxIcon, masksIcon, maskIcon, geoElemIcon,
                refElemIcon, propIcon, propsIcon;

        /**
         * Creates a new GDSTreeRenderer object.
         */
        public GDSTreeRenderer() {
            this.dbIcon = new ImageIcon(getClass().getResource(
                    "images/db.gif" /*NOI18N*/));
            this.aclIcon = new ImageIcon(getClass().getResource(
                    "images/acl.gif" /*NOI18N*/));
            this.acIcon = new ImageIcon(getClass().getResource(
                    "images/ac.gif" /*NOI18N*/));
            this.fontsIcon = new ImageIcon(getClass().getResource(
                    "images/fonts.gif" /*NOI18N*/));
            this.fontIcon = new ImageIcon(getClass().getResource(
                    "images/font.gif" /*NOI18N*/));
            this.geoElemIcon = new ImageIcon(getClass().getResource(
                    "images/geo.gif" /*NOI18N*/));
            this.libsIcon = new ImageIcon(getClass().getResource(
                    "images/libs.gif" /*NOI18N*/));
            this.libIcon = new ImageIcon(getClass().getResource(
                    "images/lib.gif" /*NOI18N*/));
            this.masksIcon = new ImageIcon(getClass().getResource(
                    "images/masks.gif" /*NOI18N*/));
            this.maskIcon = new ImageIcon(getClass().getResource(
                    "images/mask.gif" /*NOI18N*/));
            this.propsIcon = new ImageIcon(getClass().getResource(
                    "images/props.gif" /*NOI18N*/));
            this.propIcon = new ImageIcon(getClass().getResource(
                    "images/prop.gif" /*NOI18N*/));
            this.refElemIcon = new ImageIcon(getClass().getResource(
                    "images/ref.gif" /*NOI18N*/));
            this.refIcon = new ImageIcon(getClass().getResource(
                    "images/refs.gif" /*NOI18N*/));
            this.aryRefIcon = new ImageIcon(getClass().getResource(
                    "images/ary.gif" /*NOI18N*/));
            this.structsIcon = new ImageIcon(getClass().getResource(
                    "images/structs.gif" /*NOI18N*/));
            this.structIcon = new ImageIcon(getClass().getResource(
                    "images/struct.gif" /*NOI18N*/));
            this.boxIcon = new ImageIcon(getClass().getResource(
                    "images/box.gif" /*NOI18N*/));
            this.bdyIcon = new ImageIcon(getClass().getResource(
                    "images/bdy.gif" /*NOI18N*/));
            this.pathIcon = new ImageIcon(getClass().getResource(
                    "images/path.gif" /*NOI18N*/));
            this.nodeIcon = new ImageIcon(getClass().getResource(
                    "images/node.gif" /*NOI18N*/));
        } // end ctor GDSTreeRenderer

        /**
         * GDS tree renderer class
         *
         * @param   tree      The tree to render
         * @param   value     The value of the node to render
         * @param   sel       Indicates the node is selected
         * @param   expanded  Indicates the node is expanded
         * @param   leaf      Indicates the node has no children
         * @param   row       The corresponding row of the node
         * @param   hasFocus  Indicates the node has the input focus
         *
         * @return  The rendered component
         */
        @Override
        public Component getTreeCellRendererComponent(
                JTree tree,
                Object value,
                boolean sel,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row,
                    hasFocus);
            if (((DefaultMutableTreeNode) value).equals(tree.getModel().getRoot())) {
                setIcon(this.dbIcon);
                setToolTipText(JGDSBrowserTree.this.i18n.getString("JGDSPARSER_RIGHT_CLICK" /* NOI18N */));
                if (((DefaultMutableTreeNode) value).getUserObject() == null) {
                    setText(NO_DESIGN_LABEL);
                }
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof String) {
                String string = (String) ((DefaultMutableTreeNode) value)
                        .getUserObject();
                if (string.equals(ACCESS_CONTROLS_LABEL)) {
                    setIcon(this.aclIcon);
                }
                else if (string.equals(STRUCTURES_LABEL)) {
                    setIcon(this.structsIcon);
                }
                else if (string.equals(GEO_ELEMENTS_LABEL)) {
                    setIcon(this.geoElemIcon);
                }
                else if (string.equals(REF_ELEMENTS_LABEL)) {
                    setIcon(this.refElemIcon);
                }
                else if (string.equals(REF_LIBS_LABEL)) {
                    setIcon(this.libsIcon);
                }
                else if (string.equals(MASKS_LABEL)) {
                    setIcon(this.masksIcon);
                }
                else if (string.equals(PROPS_LABEL)) {
                    setIcon(this.propsIcon);
                }
                else if (string.equals(FONTS_LABEL)) {
                    setIcon(this.fontsIcon);
                }
                if (((DefaultMutableTreeNode) ((DefaultMutableTreeNode) value).getParent()).getUserObject()
                        .equals(FONTS_LABEL)) {
                    setIcon(this.fontIcon);
                }
                if (((DefaultMutableTreeNode) ((DefaultMutableTreeNode) value).getParent()).getUserObject()
                        .equals(MASKS_LABEL)) {
                    setIcon(this.maskIcon);
                }
                if (((DefaultMutableTreeNode) ((DefaultMutableTreeNode) value).getParent()).getUserObject()
                        .equals(REF_LIBS_LABEL)) {
                    setIcon(this.libIcon);
                }
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof Structure) {
                setIcon(this.structIcon);
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof GDSAccessControl) {
                setIcon(this.acIcon);
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof StructureReference) {
                setIcon(this.refIcon);
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof ArrayReference) {
                setIcon(this.aryRefIcon);
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof Text) {
                setIcon(this.fontIcon);
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof Property) {
                setIcon(this.propIcon);
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof com.ohrasys.cad.gds.dao.Box) {
                setIcon(this.boxIcon);
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof Boundary) {
                setIcon(this.bdyIcon);
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof Node) {
                setIcon(this.nodeIcon);
            }
            else if (((DefaultMutableTreeNode) value).getUserObject() instanceof Path) {
                setIcon(this.pathIcon);
            }

            return this;
        } // end method getTreeCellRendererComponent

        /**
         * Returns a string representation of the cell renderer
         *
         * @return  A string representation of the cell renderer
         */
        @Override
        public String toString() {
            return super.toString();
        }
    } // end class GDSTreeRenderer
} // end class JGDSBrowserTree

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
