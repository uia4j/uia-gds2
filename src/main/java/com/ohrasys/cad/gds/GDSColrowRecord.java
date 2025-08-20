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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Represents a GDSII COLROW record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSColrowRecord extends GDSRecord {

    /** The number of columns in the array */
    private short numcol;

    /** The number of rows in the array */
    private short numrow;

    /**
     * Creates a new GDSColrowRecord object based on an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid COLROW record.
     */
    public GDSColrowRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSColrowRecord object.
     *
     * @param   col  The number of columns.
     * @param   row  The number of rows.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSColrowRecord(short col, short row) throws GDSRecordException {
        this.numcol = validate(col);
        this.numrow = validate(row);
        this.rectype = COLROW;
        this.dattype = SHORT_TYPE;
        this.length = (short) 8;
        this.data = updateData();
    }

    /**
     * Creates a new GDSColrowRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSColrowRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateShortRec(COLROW, 4);

        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        this.numcol = validate(GDSByteConverter.readShort(bais));
        this.numrow = validate(GDSByteConverter.readShort(bais));
    }

    /**
     * Returns the number of columns.
     *
     * @return  The nubmer of columns.
     */
    public short getNumcol() {
        return this.numcol;
    }

    /**
     * Returns the number of rows.
     *
     * @return  The nubmer of rows.
     */
    public short getNumrow() {
        return this.numrow;
    }

    /**
     * Sets the number of columns.
     *
     * @param   ncol  The number of columns.
     *
     * @throws  GDSRecordException  If the number of columns is not in the range
     *                              0:32767.
     */
    public void setNumcol(short ncol) throws GDSRecordException {
        this.numcol = validate(ncol);
        this.data = updateData();
    }

    /**
     * Sets the number of rows.
     *
     * @param   nrow  The number of rows.
     *
     * @throws  GDSRecordException  If the number of rows is not in the range
     *                              0:32767.
     */
    public void setNumrow(short nrow) throws GDSRecordException {
        this.numrow = validate(nrow);
        this.data = updateData();
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(GDSI18NFactory.i18n_COLROW_TOSTRING),
                this.numrow, this.numcol);
    }

    /**
     * A method to update the byte data of the record
     *
     * @return  The updated byte data of the record
     */
    private byte[] updateData() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = GDSByteConverter.fromShort(this.numcol, baos);
        baos = GDSByteConverter.fromShort(this.numrow, baos);

        return baos.toByteArray();
    }

    /**
     * A method to validate the record data
     *
     * @param   num  The number of rows/columns
     *
     * @return  The number of rows/columns
     *
     * @throws  GDSRecordException  If the number is not in the range 0-32767
     */
    private short validate(short num) throws GDSRecordException {
        if ((num >= 0) && (num <= 32767)) {
            return num;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_COLROW_THROW));
    }
} // end class GDSColrowRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
