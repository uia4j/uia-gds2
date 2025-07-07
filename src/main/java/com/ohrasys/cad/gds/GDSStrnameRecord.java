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
 * Represents a GDSII STRNAME record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSStrnameRecord extends GDSRecord {

    /** The structure name */
    private String strname;

    /**
     * Creates a new GDSStrnameRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid STRNAME record.
     */
    public GDSStrnameRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSStrnameRecord object.
     *
     * @param   strname  The structure name.
     *
     * @throws  GDSRecordException  If the name doesn't consist of
     *                              [A-Z,a-z,0-9,?,_,$]
     */
    public GDSStrnameRecord(String strname) throws GDSRecordException {
        setStrname(strname);
        this.rectype = STRNAME;
        this.dattype = STRING_TYPE;
    }

    /**
     * Creates a new GDSStrnameRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSStrnameRecord(short length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateStringRec(STRNAME, MAX_REC_LEN, 2);
        this.strname = validate(GDSSpecificDataConverter.toJavaString(data));
    }

    /**
     * Returns the structure name.
     *
     * @return  The structure name.
     */
    public String getStrname() {
        return new String(this.strname);
    }

    /**
     * Sets the structure name.
     *
     * @param   strname  The structure name.
     *
     * @throws  GDSRecordException  If the name doesn't consist of
     *                              [A-Z,a-z,0-9,?,_,$]
     */
    public void setStrname(String strname) throws GDSRecordException {
        this.strname = validate(new String(strname));
        this.data = GDSSpecificDataConverter.writeJavaString(strname);
        this.length = (short) (this.data.length + 4);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(this.i18n.getString(
                this.i18n.i18n_STRNAME_TOSTRING), this.strname);
    }

    /**
     * A method to validate the strname value
     *
     * @param   strname  The strname value to validate
     *
     * @return  The strname value
     *
     * @throws  GDSRecordException  If the value is null or is not in the range
     *                              1-MAX_REC_LEN
     */
    private String validate(String strname) throws GDSRecordException {
        if ((strname == null) || (strname.length() < 1) ||
                (strname.length() > MAX_REC_LEN)) {
            throw new GDSRecordException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_STRNAME_THROW1));
        }

        if (!GDSStringUtil.consistsOf(strname.toLowerCase(), validChars)) {
            throw new GDSRecordException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_STRNAME_THROW2));
        }

        return strname;
    }
} // end class GDSStrnameRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
