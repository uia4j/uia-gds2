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
 * Represents a GDSII PLEX record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSPlexRecord extends GDSRecord {

    /**
     * The plex number.  Plex numbers are used to create a group of related
     * database elements
     */
    private int plex;

    /**
     * Creates a new GDSPlexRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid PLEX record.
     */
    public GDSPlexRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSPlexRecord object.
     *
     * @param   plex  The plex.
     *
     * @throws  GDSRecordException  If the plex is negative.
     */
    public GDSPlexRecord(int plex) throws GDSRecordException {
        setPlex(plex);
        this.rectype = PLEX;
        this.dattype = INT_TYPE;
        this.length = (short) 8;
    }

    /**
     * Creates a new GDSPlexRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSPlexRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateIntRec(PLEX);
        this.plex = validate(GDSByteConverter.toInt(data));
    }

    /**
     * Returns the plex.
     *
     * @return  The plex.
     */
    public int getPlex() {
        return this.plex;
    }

    /**
     * Sets the plex.
     *
     * @param   plex  The plex.
     *
     * @throws  GDSRecordException  If the plex is negative.
     */
    public void setPlex(int plex) throws GDSRecordException {
        this.plex = validate(plex);
        this.data = GDSByteConverter.writeInt(plex);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(GDSI18NFactory.i18n_PLEX_TOSTRING),
                this.plex);
    }

    /**
     * A method to validate the plex number value
     *
     * @param   plex  The plex number value to validate
     *
     * @return  The plex number
     *
     * @throws  GDSRecordException  If the plex number is negative
     */
    private int validate(int plex) throws GDSRecordException {
        if (plex >= 0) {
            return plex;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_PLEX_THROW));
    }
} // end class GDSPlexRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
