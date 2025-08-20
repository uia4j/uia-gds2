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
 * Represents a GDSII FORMAT record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSFormatRecord extends GDSRecord {

    /** Indicates the library is a GDSII archived library */
    public static final short GDSII_ARCHIVED = 0;

    /** Indicates the library is a GDSII filtered library */
    public static final short GDSII_FILTERED = 1;

    /** Indicates the library is an EDSIII archived library */
    public static final short EDSIII_ARCHIVED = 2;

    /** Indicates the library is an EDSIII filtered library */
    public static final short EDSIII_FILTERED = 3;

    /** The record format */
    private short format;

    /**
     * Creates a new GDSFormatRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid FORMAT record.
     */
    public GDSFormatRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSFormatRecord object.
     *
     * @param   format  The library format.
     *
     * @throws  GDSRecordException  If the format is not one of GDSII_ARCHIVE,
     *                              GDSII_FILTERED, EDSIII_ARCHIVE,
     *                              EDSIII_FILTERED.
     */
    public GDSFormatRecord(short format) throws GDSRecordException {
        setFormat(format);
        this.rectype = FORMAT;
        this.dattype = SHORT_TYPE;
        this.length = (short) 6;
    }

    /**
     * Creates a new GDSFormatRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSFormatRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateShortRec(FORMAT);
        this.format = validate(GDSByteConverter.toShort(data));
    }

    /**
     * Returns the format of the library.
     *
     * @return  one of GDSII_ARCHIVE, GDSII_FILTERED, EDSIII_ARCHIVE,
     *          EDSIII_FILTERED.
     */
    public short getFormat() {
        return this.format;
    }

    /**
     * Sets the library format.
     *
     * @param   format  The format.
     *
     * @throws  GDSRecordException  If the format is not one of GDSII_ARCHIVE,
     *                              GDSII_FILTERED, EDSIII_ARCHIVE,
     *                              EDSIII_FILTERED.
     */
    public void setFormat(short format) throws GDSRecordException {
        this.format = validate(format);
        this.data = GDSByteConverter.writeShort(format);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        String result;

        switch (this.format) {
            case 0:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_FORMAT_TOSTRING1);

                break;

            case 1:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_FORMAT_TOSTRING2);

                break;

            case 2:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_FORMAT_TOSTRING3);

                break;

            case 3:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_FORMAT_TOSTRING4);

                break;

            default:
                result = GDSI18NFactory.getString(GDSI18NFactory.i18n_FORMAT_TOSTRING5);
        }

        return result;
    } // end method toString

    /**
     * A method to validate the record data
     *
     * @param   format  The record format
     *
     * @return  The record format
     *
     * @throws  GDSRecordException  If the format is not one of GDSII/EDSIII
     *                              FILTERED/ARCHIVED
     */
    private short validate(short format) throws GDSRecordException {
        if ((format >= GDSII_ARCHIVED) && (format <= EDSIII_FILTERED)) {
            return format;
        }

        throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_FORMAT_THROW));
    }
} // end class GDSFormatRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
