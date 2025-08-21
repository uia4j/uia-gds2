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

import java.util.ResourceBundle;

import com.ohrasys.cad.gds.GDSFormatRecord;

/**
 * A format data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.5 $, $Date: 2005/05/18 07:11:09 $
 */
public class Format implements InfoProvider {

    /** The resource bundle */
    private static final String bundle = "com/ohrasys/cad/gds/dao/JGDSDAOProperties" /* NOI18N */;

    /** The resource bundle */
    private ResourceBundle i18n;

    /** Holds value of property masks. */
    private String masks[];

    /** Holds value of property type. */
    private int type;

    /**
     * Creates a new Format object.
     */
    public Format() {
        this(GDSFormatRecord.GDSII_ARCHIVED);
    }

    /**
     * Creates a new Format object.
     *
     * @param  type  The format of the database
     */
    public Format(int type) {
        this(type, new String[0]);
    }

    /**
     * Creates a new Format object.
     *
     * @param  type   The format of the database
     * @param  masks  The masks associated with this database
     */
    public Format(int type, String masks[]) {
        setType(type);
        setMasks(masks);
        this.i18n = ResourceBundle.getBundle(bundle);
    }

    /**
     * Returns a textual representation of the database
     *
     * @return  A textual representation of the database
     */
    @Override
    public String getInfo() {
        String result = new String();

        return result;
    }

    /**
     * Getter for property masks.
     *
     * @return  Value of property masks.
     */
    public String[] getMasks() {
        return this.masks;
    }

    /**
     * Getter for property type.
     *
     * @return  Value of property type.
     */
    public int getType() {
        return this.type;
    }

    /**
     * Setter for property masks.
     *
     * @param  masks  New value of property masks.
     */
    public void setMasks(String masks[]) {
        this.masks = masks;
    }

    /**
     * Setter for property type.
     *
     * @param  type  New value of property type.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Returns a string representation of the format object
     *
     * @return  A string representation of the format object
     */
    @Override
    public String toString() {
        return this.i18n.getString("I18N_FMT" /*NOI18N*/);
    }
} // end class Format

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
