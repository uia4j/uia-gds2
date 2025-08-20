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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Represents a GDSII REFLIBS record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.9 $
 * @since    1.5
 */
public class GDSReflibsRecord extends GDSRecord {

    /** A list of reference library names */
    private String reflibs[];

    /**
     * Creates a new GDSReflibsRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid REFLIBS record.
     */
    public GDSReflibsRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSReflibsRecord object.
     *
     * @param   reflibs  The refernce libraries attached to the database.
     *
     * @throws  GDSRecordException  If at least on reference library isn't
     *                              specified.
     */
    public GDSReflibsRecord(String reflibs[]) throws GDSRecordException {
        setReflibs(reflibs);
        this.rectype = REFLIBS;
        this.dattype = STRING_TYPE;
    }

    /**
     * Creates a new GDSReflibsRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSReflibsRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateStringRec(REFLIBS, 660, 88);

        if ((data.length % 44) != 0) {
            throw new GDSRecordException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_REFLIBS_THROW1));
        }

        int numlibs = data.length / 44;
        String reflibs[] = new String[numlibs];
        byte register[] = new byte[44];

        for (int i = 0; i < numlibs; i++) {
            System.arraycopy(data, i * 44, register, 0, 44);

            String lib = GDSSpecificDataConverter.toJavaString(register);
            reflibs[i] = lib;
        }

        this.reflibs = validate(reflibs);
    }

    /**
     * Returns the list of reference libraries attached to the database.
     *
     * @return  The list of reference libraries attached to the database.
     */
    public String[] getReflibs() {
        String result[] = new String[this.reflibs.length];
        System.arraycopy(this.reflibs, 0, result, 0, this.reflibs.length);

        return result;
    }

    /**
     * Sets the refernce libraries attached to the database.
     *
     * @param   reflibs  The refernce libraries attached to the database.
     *
     * @throws  GDSRecordException  If at least on reference library isn't
     *                              specified.
     */
    public void setReflibs(String reflibs[]) throws GDSRecordException {
        this.reflibs = new String[validate(reflibs).length];
        System.arraycopy(reflibs, 0, this.reflibs, 0, reflibs.length);
        this.data = updateData();
        this.length = (short) (this.data.length + 4);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        String result = GDSI18NFactory.getString(GDSI18NFactory.i18n_REFLIBS_TOSTRING1);

        for (int i = 0; i < this.reflibs.length; i++) {
            result += GDSStringUtil.sprintf(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_REFLIBS_TOSTRING2), i, this.reflibs[i]);
        }

        return result;
    }

    /**
     * A method to update the byte data of the record
     *
     * @return  The updated byte data
     *
     * @throws  GDSRecordException  If an I/O exception occurs
     */
    private byte[] updateData() throws GDSRecordException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (int i = 0; i < this.reflibs.length; i++) {
            try {
                baos.write(GDSSpecificDataConverter.writeJavaString(this.reflibs[i],
                        44));
            }
            catch (IOException e) {
                throw new GDSRecordException(e.getMessage());
            }
        }

        return baos.toByteArray();
    }

    /**
     * A method to validate the reference library name list
     *
     * @param   reflibs  The list of reference library names to validate
     *
     * @return  The list of library names
     *
     * @throws  GDSRecordException  If the length of the list is not in the range
     *                              of 2-15, if the length of the first library
     *                              name is 0, or any of the library names exceeds
     *                              44 characters
     */
    private String[] validate(String reflibs[]) throws GDSRecordException {
        if ((reflibs.length < 2) || (reflibs.length > 15)) {
            throw new GDSRecordException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_REFLIBS_THROW2));
        }

        if (reflibs[0].length() < 1) {
            throw new GDSRecordException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_REFLIBS_THROW3));
        }

        for (int i = 0; i < reflibs.length; i++) {
            if (reflibs[i].length() > 44) {
                throw new GDSRecordException(GDSI18NFactory.getString(
                        GDSI18NFactory.i18n_REFLIBS_THROW4));
            }
        }

        return reflibs;
    }
} // end class GDSReflibsRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
