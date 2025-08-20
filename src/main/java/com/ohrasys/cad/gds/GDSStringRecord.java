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
 * Represents a GDSII STRING record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSStringRecord extends GDSRecord {

    /** The string value */
    private String string;

    /**
     * Creates a new GDSStringRecord object.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid STRING record.
     */
    public GDSStringRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSStringRecord object.
     *
     * @param   string  The string.
     *
     * @throws  GDSRecordException  If the string is greater than 512 characters.
     */
    public GDSStringRecord(String string) throws GDSRecordException {
        setString(string);
        this.rectype = STRING;
        this.dattype = STRING_TYPE;
    }

    /**
     * Creates a new GDSStringRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSStringRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateStringRec(STRING, 512, 2);
        this.string = validate(GDSSpecificDataConverter.toJavaString(data));
    }

    /**
     * Returns the string.
     *
     * @return  The string.
     */
    public String getString() {
        return new String(this.string);
    }

    /**
     * Sets the string.
     *
     * @param   string  The string.
     *
     * @throws  GDSRecordException  If the string is greater than 512 characters.
     */
    public void setString(String string) throws GDSRecordException {
        this.string = validate(new String(string));
        this.data = GDSSpecificDataConverter.writeJavaString(string);
        this.length = (short) (this.data.length + 4);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(GDSI18NFactory.i18n_STRING_TOSTRING),
                this.string);
    }

    /**
     * A method to validate the string value
     *
     * @param   string  The string value to validate
     *
     * @return  The string value
     *
     * @throws  GDSRecordException  If the string is null or the length is not in
     *                              the range 1-512
     */
    private String validate(String string) throws GDSRecordException {
        if ((string != null) && (string.length() >= 1) &&
                (string.length() <= 512)) {
            return string;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_STRING_THROW));
    }
} // end class GDSStringRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
