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
 * Represents a GDSII BGNEXTN record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.10 $
 * @since    1.5
 */
public class GDSBgnextnRecord extends GDSRecord {

    /** The value of the beginning extension */
    private int bgnextn;

    /**
     * Creates a new GDSBgnextnRecord object.
     *
     * @param   bgnextn  The extension of the path beyond the start coordinate.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSBgnextnRecord(int bgnextn) throws GDSRecordException {
        setBgnextn(bgnextn);
        this.rectype = BGNEXTN;
        this.dattype = INT_TYPE;
        this.length = (short) 8;
    }

    /**
     * Creates a new GDSBgnextnRecord object based on an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSBgnextnRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSBgnextnRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The data type.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSBgnextnRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateIntRec(BGNEXTN);
        this.bgnextn = GDSByteConverter.toInt(data);
    }

    /**
     * Returns the end extension.
     *
     * @return  The extension of the path beyond the path end coordinate.
     */
    public int getBgnextn() {
        return this.bgnextn;
    }

    /**
     * Sets the extension of the path beyond the path end coordinate.
     *
     * @param  bgnextn  The extension.
     */
    public void setBgnextn(int bgnextn) {
        this.bgnextn = bgnextn;
        this.data = GDSByteConverter.writeInt(bgnextn);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(
                GDSI18NFactory.i18n_BGNEXTN_TOSTRING), this.bgnextn);
    }
} // end class GDSBgnextnRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
