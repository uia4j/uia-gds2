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
 * Represents a GDSII PATHTYPE record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSPathtypeRecord extends GDSRecord {

    /** Indicates the path extensions are flush. */
    public static final short FLUSH = 0;

    /** Indicates the path extensions are round. */
    public static final short ROUND = 1;

    /** Indicates the path extensions are square. */
    public static final short SQUARE = 2;

    /** Indicates the path extensions are variable. */
    public static final short VARIABLE = 4;

    /** The pathtype value.  Must be one of FLUSH, ROUND, SQUARE or VARIABLE */
    private short pathtype;

    /**
     * Creates a new GDSPathtypeRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid PATHTYPE record.
     */
    public GDSPathtypeRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSPathtypeRecord object.
     *
     * @param   pathtype  The path type.
     *
     * @throws  GDSRecordException  If the type is not one of ROUND, FLUSH,
     *                              SQUARE, VARIABLE.
     */
    public GDSPathtypeRecord(short pathtype) throws GDSRecordException {
        setPathtype(pathtype);
        this.rectype = PATHTYPE;
        this.dattype = SHORT_TYPE;
        this.length = (short) 6;
    }

    /**
     * Creates a new GDSPathtypeRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSPathtypeRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateShortRec(PATHTYPE);
        this.pathtype = validate(GDSByteConverter.toShort(data));
    }

    /**
     * Returns the path type.
     *
     * @return  One of ROUND, FLUSH, SQUARE, VARIABLE.
     */
    public short getPathtype() {
        return this.pathtype;
    }

    /**
     * Sets the path type.
     *
     * @param   pathtype  The path type.
     *
     * @throws  GDSRecordException  If the type is not one of ROUND, FLUSH,
     *                              SQUARE, VARIABLE.
     */
    public void setPathtype(short pathtype) throws GDSRecordException {
        this.pathtype = validate(pathtype);
        this.data = GDSByteConverter.writeShort(pathtype);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        String result;

        switch (this.pathtype) {
            case 0:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_PATHTYPE_TOSTRING1);

                break;

            case 1:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_PATHTYPE_TOSTRING2);

                break;

            case 2:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_PATHTYPE_TOSTRING3);

                break;

            case 4:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_PATHTYPE_TOSTRING4);

                break;

            default:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_PATHTYPE_TOSTRING5);
        }

        return result;
    } // end method toString

    /**
     * A method to validate the pathtype value
     *
     * @param   pathtype  The pathtype value to validate
     *
     * @return  The pathtype value
     *
     * @throws  GDSRecordException  If the pathtype value is not one of ROUND,
     *                              SQUARE, FLUSH or VARIABLE
     */
    private short validate(short pathtype) throws GDSRecordException {
        if ((pathtype == FLUSH) || (pathtype == ROUND) ||
                (pathtype == SQUARE) || (pathtype == VARIABLE)) {
            return pathtype;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_PATHTYPE_THROW));
    }
} // end class GDSPathtypeRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
