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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Represents a GDSII BGNLIB record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.12 $
 * @since    1.5
 */
public class GDSBgnlibRecord extends GDSRecord {

    /** The last accessed day */
    private short la_day;

    /** The last accessed hour */
    private short la_hour;

    /** The last accessed minute */
    private short la_minute;

    /** The last accessed month */
    private short la_month;

    /** The last accessed second */
    private short la_second;

    /** The last accessed year */
    private short la_year;

    /** The last modified day */
    private short lm_day;

    /** The last modified hour */
    private short lm_hour;

    /** The last modified minute */
    private short lm_minute;

    /** The last modified month */
    private short lm_month;

    /** The last modified second */
    private short lm_second;

    /** The last modified year */
    private short lm_year;

    /**
     * Creates a new GDSBgnlibRecord object based on an existing record.
     *
     * @param   rec  The base record.
     *
     * @throws  GDSRecordException  If the record is not a valid BGNLIB record.
     */
    public GDSBgnlibRecord(GDSRecord rec) throws GDSRecordException {
        this(rec.getLength(), rec.getRectype(), rec.getDattype(),
                rec.getData());
    }

    /**
     * Creates a new GDSBgnlibRecord object.
     *
     * @param   mod     The date the library was last modified.
     * @param   access  The data the library was last accessed.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSBgnlibRecord(Date mod, Date access) throws GDSRecordException {
        this.length = (short) 28;
        this.rectype = BGNLIB;
        this.dattype = SHORT_TYPE;
        setLastAccessedDate(access);
        setModificationDate(mod);
    }

    /**
     * Creates a new GDSBgnlibRecord object.
     *
     * @param   length   The record length.
     * @param   rectype  The record type.
     * @param   dattype  The data type.
     * @param   data     The record data.
     *
     * @throws  GDSRecordException  If the record is malformed.
     */
    public GDSBgnlibRecord(int length, byte rectype, byte dattype, byte data[]) throws GDSRecordException {
        super(length, rectype, dattype, data);
        validateShortRec(BGNLIB, 24);

        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        this.lm_year = (short) (GDSByteConverter.readShort(bais) + 1900);
        this.lm_month = GDSByteConverter.readShort(bais);
        this.lm_day = GDSByteConverter.readShort(bais);
        this.lm_hour = GDSByteConverter.readShort(bais);
        this.lm_minute = GDSByteConverter.readShort(bais);
        this.lm_second = GDSByteConverter.readShort(bais);
        this.la_year = (short) (GDSByteConverter.readShort(bais) + 1900);
        this.la_month = GDSByteConverter.readShort(bais);
        this.la_day = GDSByteConverter.readShort(bais);
        this.la_hour = GDSByteConverter.readShort(bais);
        this.la_minute = GDSByteConverter.readShort(bais);
        this.la_second = GDSByteConverter.readShort(bais);
    }

    /**
     * Returns the date the library was last accessed.
     *
     * @return  The date the library was last accessed.
     */
    public Date getLastAccessedDate() {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(this.la_year, this.la_month - 1, this.la_day, this.la_hour, this.la_minute, this.la_second);

        return cal.getTime();
    }

    /**
     * Returns the date the library was last modified.
     *
     * @return  The date the library was last modified.
     */
    public Date getModificationDate() {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(this.lm_year, this.lm_month - 1, this.lm_day, this.lm_hour, this.lm_minute, this.lm_second);

        return cal.getTime();
    }

    /**
     * Sets the date the library was last accessed.
     *
     * @param  date  The date the library was last accessed.
     */
    public void setLastAccessedDate(Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        this.la_year = (short) cal.get(Calendar.YEAR);
        this.la_month = (short) (cal.get(Calendar.MONTH) + 1);
        this.la_day = (short) cal.get(Calendar.DAY_OF_MONTH);
        this.la_hour = (short) cal.get(Calendar.HOUR_OF_DAY);
        this.la_minute = (short) cal.get(Calendar.MINUTE);
        this.la_second = (short) cal.get(Calendar.SECOND);
        this.data = updateData();
    }

    /**
     * Sets the date the library was last modified.
     *
     * @param  date  The date the library was last modified.
     */
    public void setModificationDate(Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        this.lm_year = (short) cal.get(Calendar.YEAR);
        this.lm_month = (short) (cal.get(Calendar.MONTH) + 1);
        this.lm_day = (short) cal.get(Calendar.DAY_OF_MONTH);
        this.lm_hour = (short) cal.get(Calendar.HOUR_OF_DAY);
        this.lm_minute = (short) cal.get(Calendar.MINUTE);
        this.lm_second = (short) cal.get(Calendar.SECOND);
        this.data = updateData();
    }

    /**
     * Returns a description of the record.
     *
     * @return  A string representation of the record.
     */
    @Override
    public String toString() {
        return GDSStringUtil.sprintf(GDSI18NFactory.getString(GDSI18NFactory.i18n_BGNLIB_TOSTRING),
                this.lm_month, this.lm_day, this.lm_year, this.lm_hour, this.lm_minute, this.lm_second,
                this.la_month, this.la_day, this.la_year, this.la_hour, this.la_minute, this.la_second);
    }

    /**
     * A method used to update the byte data of the record
     *
     * @return  The updated byte data
     */
    private byte[] updateData() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GDSByteConverter.fromShort((short) (this.lm_year - 1900), baos);
        GDSByteConverter.fromShort(this.lm_month, baos);
        GDSByteConverter.fromShort(this.lm_day, baos);
        GDSByteConverter.fromShort(this.lm_hour, baos);
        GDSByteConverter.fromShort(this.lm_minute, baos);
        GDSByteConverter.fromShort(this.lm_second, baos);
        GDSByteConverter.fromShort((short) (this.la_year - 1900), baos);
        GDSByteConverter.fromShort(this.la_month, baos);
        GDSByteConverter.fromShort(this.la_day, baos);
        GDSByteConverter.fromShort(this.la_hour, baos);
        GDSByteConverter.fromShort(this.la_minute, baos);
        GDSByteConverter.fromShort(this.la_second, baos);

        return baos.toByteArray();
    }
} // end class GDSBgnlibRecord

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
