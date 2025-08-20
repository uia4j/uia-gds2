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
 * Represents a GDSII HEADER record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSHeaderRecord extends GDSRecord {

    /** The GDS version */
    private short version;

    /**
     * Creates a new GDSHeaderRecord object.
     *
     * @param   version  The GDS version.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSHeaderRecord(short version) throws GDSRecordException {
        setVersion(version);
        this.rectype = HEADER;
        this.dattype = SHORT_TYPE;
        this.length = (short) 6;
    }

    /**
     * Creates a new GDSHeaderRecord object.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid HEADER record.
     */
    public GDSHeaderRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSHeaderRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSHeaderRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateShortRec(HEADER);
        this.version = GDSByteConverter.toShort(data);
    }

    /**
     * Returns the GDS version.
     *
     * @return  The GDS version.
     */
    public short getVersion() {
        return this.version;
    }

    /**
     * Sets the GDS version
     *
     * @param  version  The GDS version.
     */
    public void setVersion(short version) {
        this.version = version;
        this.data = GDSByteConverter.writeShort(version);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(GDSI18NFactory.i18n_HEADER_TOSTRING),
                this.version);
    }
} // end class GDSHeaderRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
