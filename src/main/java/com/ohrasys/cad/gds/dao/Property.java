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

/**
 * A property data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $, $Date: 2005/05/18 07:11:09 $
 */
public class Property implements InfoProvider {

    /** The resource bundle */
    private static final String bundle = "com/ohrasys/cad/gds/dao/JGDSDAOProperties" /* NOI18N */;

    /** The resource bundle */
    private ResourceBundle i18n;

    /** Holds value of property number. */
    private int number;

    /** Holds value of property value. */
    private String value;

    /**
     * Creates a new instance of Property
     */
    public Property() {
        this(0, null);
    }

    /**
     * Creates a new Property object.
     *
     * @param  number  The property number
     * @param  value   The value of the property
     */
    public Property(int number, String value) {
        setNumber(number);
        setValue(value);
        this.i18n = ResourceBundle.getBundle(bundle);
    }

    /**
     * Returns a textual representation of the property
     *
     * @return  A textual representation of the property
     */
    @Override
    public String getInfo() {
        String result = new String();
        result += (this.i18n.getString("I18N_PROP_NUM" /*NOI18N*/) + getNumber());
        result += (this.i18n.getString("I18N_PROP_VAL" /*NOI18N*/) + getValue());

        return result;
    }

    /**
     * Getter for property number.
     *
     * @return  Value of property number.
     */
    public int getNumber() {
        return this.number;
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
     * Setter for property number.
     *
     * @param  number  New value of property number.
     */
    public void setNumber(int number) {
        this.number = number;
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
     * Returns a string representation of the property
     *
     * @return  A string representation of the property
     */
    @Override
    public String toString() {
        return getValue();
    }
} // end class Property

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
