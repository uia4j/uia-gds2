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
 * Represents a GDSII LIBDIRSIZE record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSLibdirsizeRecord extends GDSRecord {

    /** The size of the library directory */
    private short libdirsize;

    /**
     * Creates a new GDSLibdirsizeRecord object.
     *
     * @param   libdirsize  The number of pages required by the library.
     *
     * @throws  GDSRecordException  If the number of pages is 0;
     */
    public GDSLibdirsizeRecord(short libdirsize) throws GDSRecordException {
        setLibdirsize(libdirsize);
        this.rectype = LIBDIRSIZE;
        this.dattype = SHORT_TYPE;
        this.length = (short) 6;
    }

    /**
     * Creates a new GDSLibdirsizeRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid LIBDIRSIZE
     *                              record.
     */
    public GDSLibdirsizeRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSLibdirsizeRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSLibdirsizeRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateShortRec(LIBDIRSIZE);
        this.libdirsize = validate(GDSByteConverter.toShort(data));
    }

    /**
     * Returns the number of pages required by the library.
     *
     * @return  The number of pages required by the library.
     */
    public short getLibdirsize() {
        return this.libdirsize;
    }

    /**
     * Sets the number of pages required by the library.
     *
     * @param   libdirsize  The number of pages required by the library.
     *
     * @throws  GDSRecordException  If the number of pages is 0.
     */
    public void setLibdirsize(short libdirsize) throws GDSRecordException {
        this.libdirsize = validate(libdirsize);
        this.data = GDSByteConverter.writeShort(libdirsize);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(
                GDSI18NFactory.i18n_LIBDIRSIZE_TOSTRING), this.libdirsize);
    }

    /**
     * A method to validate the record data
     *
     * @param   libdirsize  The library directory size to validate
     *
     * @return  The library directory size
     *
     * @throws  GDSRecordException  If the library directory size is less than or
     *                              equal to zero
     */
    private short validate(short libdirsize) throws GDSRecordException {
        if (libdirsize >= 1) {
            return libdirsize;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(
                GDSI18NFactory.i18n_LIBDIRSIZE_THROW));
    }
} // end class GDSLibdirsizeRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
