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

package com.ohrasys.cad.gds.dao;

import java.awt.Point;
import java.util.ResourceBundle;

/**
 * An element data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $, $Date: 2005/05/18 18:22:38 $
 */
public class Element implements InfoProvider {

    /** The resource bundle */
    private static final String bundle = "com/ohrasys/cad/gds/dao/JGDSDAOProperties" /* NOI18N */;

    /** The resource bundle */
    protected ResourceBundle i18n;

    /** Holds value of property external. */
    private boolean external;

    /** Holds value of property group. */
    private int group;

    /** Holds value of property points. */
    private Point points[];

    /** Holds value of property properties. */
    private Property properties[];

    /** Holds value of property template. */
    private boolean template;

    /**
     * Creates a new instance of Element
     */
    public Element() {
        this(false, false, 0, new Point[] { new Point(0, 0) });
    }

    /**
     * Creates a new Element object.
     *
     * @param  isExternal  The element is external data
     * @param  isTemplate  The element is template data
     * @param  group       The object group the element belongs to
     * @param  points      The points defining the location of the object
     */
    public Element(boolean isExternal, boolean isTemplate, int group, Point points[]) {
        setExternal(isExternal);
        setTemplate(isTemplate);
        setGroup(group);
        setPoints(points);
        setProperties(new Property[0]);
        this.i18n = ResourceBundle.getBundle(bundle);
    }

    /**
     * Getter for property group.
     *
     * @return  Value of property group.
     */
    public int getGroup() {
        return this.group;
    }

    /**
     * Returns a textual representation of the element
     *
     * @return  A textual representation of the element
     */
    @Override
    public String getInfo() {
        String result = new String();
        result += this.i18n.getString("I18N_EL_GEN" /*NOI18N*/);
        result += (this.i18n.getString("I18N_EL_EXT" /*NOI18N*/) + isExternal() +
                "\n" /*NOI18N*/);
        result += (this.i18n.getString("I18N_EL_TEMPLATE" /*NOI18N*/) + isTemplate() +
                "\n" /*NOI18N*/);
        result += (this.i18n.getString("I18N_EL_GROUP" /*NOI18N*/) + getGroup() +
                "\n" /*NOI18N*/);
        result += (this.i18n.getString("I18N_EL_POINTS" /*NOI18N*/));
        for (int i = 0; i < this.points.length; i++) {
            result += String.format(this.i18n.getString("I18N_EL_POINT" /*NOI18N*/),
                    this.points[i].x,
                    this.points[i].y);

            if (i < (this.points.length - 1)) {
                result += " " /*NOI18N*/;

                if (((i + 1) % 5) == 0) {
                    result += "\n\t " /*NOI18N*/;
                }
            }
        }
        result += "\n" /*NOI18N*/;

        return result;
    }

    /**
     * Getter for property points.
     *
     * @return  Value of property points.
     */
    public Point[] getPoints() {
        return this.points;
    }

    /**
     * Getter for property properties.
     *
     * @return  Value of property properties.
     */
    public Property[] getProperties() {
        return this.properties;
    }

    /**
     * Getter for property external.
     *
     * @return  Value of property external.
     */
    public boolean isExternal() {
        return this.external;
    }

    /**
     * Getter for property template.
     *
     * @return  Value of property template.
     */
    public boolean isTemplate() {
        return this.template;
    }

    /**
     * Setter for property external.
     *
     * @param  external  New value of property external.
     */
    public void setExternal(boolean external) {
        this.external = external;
    }

    /**
     * Setter for property group.
     *
     * @param  group  New value of property group.
     */
    public void setGroup(int group) {
        this.group = group;
    }

    /**
     * Setter for property points.
     *
     * @param  points  New value of property points.
     */
    public void setPoints(Point points[]) {
        this.points = points;
    }

    /**
     * Setter for property properties.
     *
     * @param  properties  New value of property properties.
     */
    public void setProperties(Property properties[]) {
        this.properties = properties;
    }

    /**
     * Setter for property template.
     *
     * @param  template  New value of property template.
     */
    public void setTemplate(boolean template) {
        this.template = template;
    }

    /**
     * Returns a string representation of the element
     *
     * @return  A string representation of the element
     */
    @Override
    public String toString() {
        return this.i18n.getString("I18N_EL_ELEM" /*NOI18N*/);
    }
} // end class Element

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
