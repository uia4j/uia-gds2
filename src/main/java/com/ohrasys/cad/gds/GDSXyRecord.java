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

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Represents a GDSII XY record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSXyRecord extends GDSRecord {

    /** The {x,y} coordinate */
    private Point xy[];

    /**
     * Creates a new GDSXyRecord object from an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid XY record.
     */
    public GDSXyRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSXyRecord object.
     *
     * @param   xy  The coordinate list.
     *
     * @throws  GDSRecordException  If the coordinate list is of zero length.
     */
    public GDSXyRecord(Point xy[]) throws GDSRecordException {
        setXy(xy);
        this.rectype = XY;
        this.dattype = INT_TYPE;
    }

    /**
     * Creates a new GDSXyRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSXyRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateIntRec(XY, MAX_REC_LEN, 8);

        if ((data.length % 8) != 0) {
            throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_XY_THROW1));
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        Point register[] = new Point[data.length / 8];

        for (int i = 0; i < register.length; i++) {
            int x = GDSByteConverter.readInt(bais);
            int y = GDSByteConverter.readInt(bais);
            register[i] = new Point(x, y);
        }

        this.xy = register;
    }

    /**
     * Returns the coordinate list.
     *
     * @return  The coordinate list.
     */
    public Point[] getXy() {
        Point result[] = new Point[this.xy.length];
        System.arraycopy(this.xy, 0, result, 0, this.xy.length);

        return result;
    }

    /**
     * Checks to see if the coordinate constraint is met for a specific element.
     *
     * @param   elementType  One of BOUNDARY_ELEM_TYPE, TEXT_ELEM_TYPE,
     *                       CONTACT_ELEM_TYPE, SREF_ELEM_TYPE, NODE_ELEM_TYPE,
     *                       BOX_ELEM_TYPE or AREF_ELEM_TYPE.
     *
     * @return  true if the coordinate constraint for the element is met.
     */
    public boolean isCoordConstraintMet(int elementType) {
        boolean result = false;

        if (elementType == PATH_ELEM_TYPE) {
            if ((this.xy.length >= 2) && (this.xy.length <= 200)) {
                result = true;
            }
        }
        else if (elementType == BOUNDARY_ELEM_TYPE) {
            if ((this.xy.length >= 4) && (this.xy.length <= 600) &&
                    (this.xy[0] == this.xy[this.xy.length - 1])) {
                result = true;
            }
        }
        else if (elementType == TEXT_ELEM_TYPE) {
            if (this.xy.length == 1) {
                result = true;
            }
        }
        else if (elementType == CONTACT_ELEM_TYPE) {
            if (this.xy.length == 1) {
                result = true;
            }
        }
        else if (elementType == SREF_ELEM_TYPE) {
            if (this.xy.length == 1) {
                result = true;
            }
        }
        else if (elementType == NODE_ELEM_TYPE) {
            if ((this.xy.length >= 1) && (this.xy.length <= 50)) {
                result = true;
            }
        }
        else if (elementType == BOX_ELEM_TYPE) {
            if ((this.xy.length == 5) && (this.xy[0] == this.xy[this.xy.length - 1])) {
                result = true;
            }
        }
        else if (elementType == AREF_ELEM_TYPE) {
            if (this.xy.length == 3) {
                result = true;
            }
        }

        return result;
    }

    /**
     * Sets the coordinate list.
     *
     * @param   xy  The coordinate list.
     *
     * @throws  GDSRecordException  If the coordinate list is of zero length.
     */
    public void setXy(Point xy[]) throws GDSRecordException {
        if (xy.length < 1) {
            throw new GDSRecordException(GDSI18NFactory.getString(GDSI18NFactory.i18n_XY_THROW2));
        }

        this.xy = new Point[xy.length];
        System.arraycopy(xy, 0, this.xy, 0, xy.length);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (int i = 0; i < xy.length; i++) {
            baos = GDSByteConverter.fromInt(xy[i].x, baos);
            baos = GDSByteConverter.fromInt(xy[i].y, baos);
        }

        this.data = baos.toByteArray();
        this.length = (short) (this.data.length + 4);
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        String result = new String(GDSI18NFactory.getString(GDSI18NFactory.i18n_XY_TOSTRING1));
        result += " " /*NOI18N*/;

        for (int i = 0; i < this.xy.length; i++) {
            result += GDSStringUtil.sprintf(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_XY_TOSTRING2), this.xy[i].x, this.xy[i].y);

            if (i < (this.xy.length - 1)) {
                result += " " /*NOI18N*/;

                if (((i + 1) % 5) == 0) {
                    result += "\n\t " /*NOI18N*/;
                }
            }
        }

        return result;
    }
} // end class GDSXyRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
