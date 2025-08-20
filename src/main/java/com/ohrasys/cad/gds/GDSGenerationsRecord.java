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
 * Represents a GDSII GENERATIONS record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSGenerationsRecord extends GDSRecord {

    /** The record generation */
    private short generations;

    /**
     * Creates a new GDSGenerationsRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid GENERATIONS
     *                              record.
     */
    public GDSGenerationsRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSGenerationsRecord object.
     *
     * @param   generations  The number of copies of deleted or back up data
     *                       structures to retain.
     *
     * @throws  GDSRecordException  If the number of generations isn't in the
     *                              range of 2:99
     */
    public GDSGenerationsRecord(short generations) throws GDSRecordException {
        setGenerations(generations);
        this.rectype = GENERATIONS;
        this.dattype = SHORT_TYPE;
        this.length = (short) 6;
    }

    /**
     * Creates a new GDSGenerationsRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed
     */
    public GDSGenerationsRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateShortRec(GENERATIONS);
        this.generations = validate(GDSByteConverter.toShort(data));
    }

    /**
     * Returns the number of copies of deleted or back up data structures to
     * retain.
     *
     * @return  The number of copies of deleted or back up data structures to
     *          retain.
     */
    public short getGenerations() {
        return this.generations;
    }

    /**
     * Returns the number of copies of deleted or back up data structures to
     * retain.
     *
     * @param   generations  The number of copies of deleted or back up data
     *                       structures to retain.
     *
     * @throws  GDSRecordException  If the number of generations isn't in the
     *                              range of 2:99
     */
    public void setGenerations(short generations) throws GDSRecordException {
        this.generations = validate(generations);
        this.data = GDSByteConverter.writeShort(generations);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(
                GDSI18NFactory.i18n_GENERATIONS_TOSTRING), this.generations);
    }

    /**
     * A method to validate the record data
     *
     * @param   generations  The generations number
     *
     * @return  The generations number
     *
     * @throws  GDSRecordException  If the number isn't in the range 2-99
     */
    private short validate(short generations) throws GDSRecordException {
        if ((generations >= 2) && (generations <= 99)) {
            return generations;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(
                GDSI18NFactory.i18n_GENERATIONS_THROW));
    }
} // end class GDSGenerationsRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
