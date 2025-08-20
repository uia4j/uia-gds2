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

package com.ohrasys.cad.gds;

/**
 * Represents a GDSII PROPVALUE record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSPropvalueRecord extends GDSRecord {

    /** The property value */
    private String propvalue;

    /**
     * Creates a new GDSPropvalueRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid PROPVALUE record.
     */
    public GDSPropvalueRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSPropvalueRecord object.
     *
     * @param   propvalue  The value.
     *
     * @throws  GDSRecordException  If the value is greater than 126 characters.
     */
    public GDSPropvalueRecord(String propvalue) throws GDSRecordException {
        setPropvalue(propvalue);
        this.rectype = PROPVALUE;
        this.dattype = STRING_TYPE;
    }

    /**
     * Creates a new GDSPropvalueRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSPropvalueRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateStringRec(PROPVALUE, 126, 2);
        this.propvalue = validate(GDSSpecificDataConverter.toJavaString(data));
    }

    /**
     * Returns the property value.
     *
     * @return  The property value.
     */
    public String getPropvalue() {
        return new String(this.propvalue);
    }

    /**
     * Sets the property value.
     *
     * @param   propvalue  The value.
     *
     * @throws  GDSRecordException  If the value is greater than 126 characters.
     */
    public void setPropvalue(String propvalue) throws GDSRecordException {
        this.propvalue = validate(new String(propvalue));
        this.data = GDSSpecificDataConverter.writeJavaString(propvalue);
        this.length = (short) (this.data.length + 4);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(
                GDSI18NFactory.i18n_PROPVALUE_TOSTRING), this.propvalue);
    }

    /**
     * A method to validate the property value
     *
     * @param   propvalue  The property value to validate
     *
     * @return  The property value
     *
     * @throws  GDSRecordException  If the length is not in the range of 1-126 or
     *                              if the property value is null
     */
    private String validate(String propvalue) throws GDSRecordException {
        if ((propvalue != null) &&
                ((propvalue.length() >= 1) && (propvalue.length() <= 126))) {
            return propvalue;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_PROPATTR_THROW));
    }
} // end class GDSPropvalueRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
