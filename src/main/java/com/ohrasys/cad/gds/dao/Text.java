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

/**
 * A text data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.9 $, $Date: 2005/05/18 07:11:09 $
 */
public class Text extends GeometryElement implements InfoProvider {

    /** Holds value of property font. */
    private int font;

    /** Holds value of property hJustify. */
    private int hJustify;

    /** Holds value of property pathStyle. */
    private int pathStyle;

    /** Holds value of property transform. */
    private Transform transform;

    /** Holds value of property value. */
    private String value;

    /** Holds value of property vJustify. */
    private int vJustify;

    /** Holds value of property width. */
    private int width;

    /**
     * Creates a new Text object.
     */
    public Text() {
        this(0, new Transform(), new String(), new Point[0]);
    }

    /**
     * Creates a new Text object.
     *
     * @param  layer      The layer of the text object
     * @param  transform  The transform of the text object
     * @param  value      The value of the text object
     * @param  points     The points defining the text object
     */
    public Text(int layer, Transform transform, String value, Point points[]) {
        this(false, false, 0, layer, 0, 0, 0, 0, 0, 1, transform, points,
                value);
    }

    /**
     * Creates a new Text object.
     *
     * @param  external   The text object is external data
     * @param  template   The text object is template data
     * @param  group      The object group the text object belongs to
     * @param  layer      The layer of the text object
     * @param  datatype   The datatyp of the text object
     * @param  vJustify   The vertical justification of the text object
     * @param  hJustify   The horizontal justification of the text object
     * @param  font       The font
     * @param  pathStyle  The path style
     * @param  width      The width of the text object
     * @param  transform  The transform of the text object
     * @param  points     The points defining the text object
     * @param  value      The string value of the text object
     */
    public Text(boolean external, boolean template, int group, int layer, int datatype, int vJustify, int hJustify, int font, int pathStyle, int width, Transform transform, Point points[], String value) {
        super(external, template, group, layer, datatype, points);
        setVJustify(vJustify);
        setHJustify(hJustify);
        setPathStyle(pathStyle);
        setWidth(width);
        setTransform(transform);
        setValue(value);
        setFont(font);
    }

    /**
     * Getter for property font.
     *
     * @return  Value of property font.
     */
    public int getFont() {
        return this.font;
    }

    /**
     * Getter for property hJustify.
     *
     * @return  Value of property hJustify.
     */
    public int getHJustify() {
        return this.hJustify;
    }

    /**
     * Returns a textual representation of the text object
     *
     * @return  A textual representation of the text object
     */
    @Override
    public String getInfo() {
        String result = super.getInfo();
        result += String.format(this.i18n.getString("I18N_TEXT_FONT" /*NOI18N*/),
                getFont());
        result += String.format(this.i18n.getString("I18N_TEXT_HJUST" /*NOI18N*/),
                getHJustify());
        result += String.format(this.i18n.getString("I18N_TEXT_VJUST" /*NOI18N*/),
                getVJustify());
        result += String.format(this.i18n.getString("I18N_TEXT_PSTYLE" /*NOI18N*/),
                getPathStyle());
        result += String.format(this.i18n.getString("I18N_TEXT_STRING" /*NOI18N*/),
                getValue());
        result += String.format(this.i18n.getString("I18N_TEXT_WIDTH" /*NOI18N*/),
                getWidth());
        if (getTransform() != null) {
            result += getTransform().getInfo();
        }

        return result;
    }

    /**
     * Getter for property pathStyle.
     *
     * @return  Value of property pathStyle.
     */
    public int getPathStyle() {
        return this.pathStyle;
    }

    /**
     * Getter for property transform.
     *
     * @return  Value of property transform.
     */
    public Transform getTransform() {
        return this.transform;
    }

    /**
     * Getter for property value.
     *
     * @return  Value of property value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Getter for property vJustify.
     *
     * @return  Value of property vJustify.
     */
    public int getVJustify() {
        return this.vJustify;
    }

    /**
     * Getter for property width.
     *
     * @return  Value of property width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Setter for property font.
     *
     * @param  font  New value of property font.
     */
    public void setFont(int font) {
        this.font = font;
    }

    /**
     * Setter for property hJustify.
     *
     * @param  hJustify  New value of property hJustify.
     */
    public void setHJustify(int hJustify) {
        this.hJustify = hJustify;
    }

    /**
     * Setter for property pathStyle.
     *
     * @param  pathStyle  New value of property pathStyle.
     */
    public void setPathStyle(int pathStyle) {
        this.pathStyle = pathStyle;
    }

    /**
     * Setter for property transform.
     *
     * @param  transform  New value of property transform.
     */
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    /**
     * Setter for property value.
     *
     * @param  value  New value of property value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Setter for property vJustify.
     *
     * @param  vJustify  New value of property vJustify.
     */
    public void setVJustify(int vJustify) {
        this.vJustify = vJustify;
    }

    /**
     * Setter for property width.
     *
     * @param  width  New value of property width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns a string representation of the text object
     *
     * @return  A string representation of the text object
     */
    @Override
    public String toString() {
        return this.i18n.getString("I18N_TEXT_TXT" /*NOI18N*/) + getValue() +
                this.i18n.getString("I18N_TEXT_CPAREN" /*NOI18N*/);
    }
} // end class Text

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
