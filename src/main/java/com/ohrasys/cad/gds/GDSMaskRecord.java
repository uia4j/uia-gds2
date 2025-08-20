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
 * Represents a GDSII MASK record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSMaskRecord extends GDSRecord {

    /** The mask name */
    private String mask;

    /**
     * Creates a new GDSMaskRecord object.
     *
     * @param   mask  The mask descriptor.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSMaskRecord(String mask) throws GDSRecordException {
        setMask(mask);
        this.rectype = MASK;
        this.dattype = STRING_TYPE;
    }

    /**
     * Creates a new GDSMaskRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSMaskRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSMaskRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSMaskRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateStringRec(MASK, MAX_REC_LEN, 2);
        this.mask = GDSSpecificDataConverter.toJavaString(data);
    }

    /**
     * Returns the mask descriptor.
     *
     * @return  The mask descriptor.
     */
    public String getMask() {
        return new String(this.mask);
    }

    /**
     * Sets the mask descriptor.
     *
     * @param   mask  The mask descriptor.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public void setMask(String mask) throws GDSRecordException {
        this.mask = validate(new String(mask));
        this.data = GDSSpecificDataConverter.writeJavaString(mask);
        this.length = (short) (this.data.length + 4);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(GDSI18NFactory.i18n_MASK_TOSTRING),
                this.mask);
    }

    /**
     * A method to validate the mask name
     *
     * @param   mask  The mask name to validate
     *
     * @return  The mask name
     *
     * @throws  GDSRecordException  If the mask name is null, has a length outside
     *                              the range 1-MAX_REC_LEN, or has illegal
     *                              characters in it
     */
    private String validate(String mask) throws GDSRecordException {
        if ((mask != null) && (mask.length() <= MAX_REC_LEN) &&
                (mask.length() >= 1)) {
            return mask;
        }

        throw new GDSRecordException(GDSStringUtil.sprintf(
                GDSI18NFactory.getString(GDSI18NFactory.i18n_MASK_THROW), MAX_REC_LEN));
    }
} // end class GDSMaskRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
