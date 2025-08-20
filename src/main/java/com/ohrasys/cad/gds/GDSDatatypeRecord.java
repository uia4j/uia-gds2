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
 * Represents a GDSII DATATYPE record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSDatatypeRecord extends GDSRecord {

    /** The datatype */
    private short datatype;

    /**
     * Creates a new GDSDatatypeRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid DATATYPE record.
     */
    public GDSDatatypeRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSDatatypeRecord object.
     *
     * @param   datatype  The datatype.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSDatatypeRecord(short datatype) throws GDSRecordException {
        setDatatype(datatype);
        this.rectype = DATATYPE;
        this.dattype = SHORT_TYPE;
        this.length = (short) 6;
    }

    /**
     * Creates a new GDSDatatypeRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSDatatypeRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateShortRec(DATATYPE);
        this.datatype = validate(GDSByteConverter.toShort(data));
    }

    /**
     * Returns the datatype.
     *
     * @return  The datatype.
     */
    public short getDatatype() {
        return this.datatype;
    }

    /**
     * Sets the datatype.
     *
     * @param   datatype  The datatype.
     *
     * @throws  GDSRecordException  If the datatype is not in the range of 0:255.
     */
    public void setDatatype(short datatype) throws GDSRecordException {
        this.datatype = validate(datatype);
        this.data = GDSByteConverter.writeShort(datatype);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(
                GDSI18NFactory.i18n_DATATYPE_TOSTRING), this.datatype);
    }

    /**
     * A method to validate the record
     *
     * @param   datatype  The datatype
     *
     * @return  The datatype
     *
     * @throws  GDSRecordException  If not in the range 0-255
     */
    private short validate(short datatype) throws GDSRecordException {
        if ((datatype >= 0) && (datatype <= 255)) {
            return datatype;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_DATATYPE_THROW));
    }
} // end class GDSDatatypeRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
