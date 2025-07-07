/* Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA. */

package com.ohrasys.cad.gds;

import java.io.*;
import java.util.*;

/**
 * Represents a GDSII BGNSTR record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.12 $
 * @since    1.5
 */
public class GDSBgnstrRecord
extends GDSRecord {
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
   * Creates a new GDSBgnstrRecord object based on an existing record.
   *
   * @param   rec  The base record
   *
   * @throws  GDSRecordException  If the record is not a valid BGNSTR record.
   */
  public GDSBgnstrRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSBgnstrRecord object.
   *
   * @param   mod     The date the structure was last modified.
   * @param   access  The date the structure was last accessed.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSBgnstrRecord(Date mod, Date access)
    throws GDSRecordException {
    length              = (short)28;
    rectype             = BGNSTR;
    dattype             = SHORT_TYPE;
    setLastAccessedDate(access);
    setModificationDate(mod);
  }

  /**
   * Creates a new GDSBgnstrRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSBgnstrRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateShortRec(BGNSTR, 24);

    ByteArrayInputStream bais = new ByteArrayInputStream(data);
    lm_year   = (short)(GDSByteConverter.readShort(bais) + 1900);
    lm_month  = GDSByteConverter.readShort(bais);
    lm_day    = GDSByteConverter.readShort(bais);
    lm_hour   = GDSByteConverter.readShort(bais);
    lm_minute = GDSByteConverter.readShort(bais);
    lm_second = GDSByteConverter.readShort(bais);
    la_year   = (short)(GDSByteConverter.readShort(bais) + 1900);
    la_month  = GDSByteConverter.readShort(bais);
    la_day    = GDSByteConverter.readShort(bais);
    la_hour   = GDSByteConverter.readShort(bais);
    la_minute = GDSByteConverter.readShort(bais);
    la_second = GDSByteConverter.readShort(bais);
  }

  /**
   * Returns the date the structure was last accessed.
   *
   * @return  The date the structure was last accessed.
   */
  public Date getLastAccessedDate() {
    Calendar cal = GregorianCalendar.getInstance();
    cal.set(la_year, la_month - 1, la_day, la_hour, la_minute, la_second);

    return cal.getTime();
  }

  /**
   * Returns the date the structure was last modified.
   *
   * @return  The date the structure was last modified.
   */
  public Date getModificationDate() {
    Calendar cal = GregorianCalendar.getInstance();
    cal.set(lm_year, lm_month - 1, lm_day, lm_hour, lm_minute, lm_second);

    return cal.getTime();
  }

  /**
   * Sets the date the structure was last accessed.
   *
   * @param  date  The date the structure was last accessed.
   */
  public void setLastAccessedDate(Date date) {
    Calendar cal = GregorianCalendar.getInstance();
    cal.setTime(date);
    la_year   = (short)cal.get(cal.YEAR);
    la_month  = (short)(cal.get(cal.MONTH) + 1);
    la_day    = (short)cal.get(cal.DAY_OF_MONTH);
    la_hour   = (short)cal.get(cal.HOUR_OF_DAY);
    la_minute = (short)cal.get(cal.MINUTE);
    la_second = (short)cal.get(cal.SECOND);
    this.data = updateData();
  }

  /**
   * Sets the date the structure was last modified.
   *
   * @param  date  The date the structure was last modified.
   */
  public void setModificationDate(Date date) {
    Calendar cal = GregorianCalendar.getInstance();
    cal.setTime(date);
    lm_year   = (short)cal.get(cal.YEAR);
    lm_month  = (short)(cal.get(cal.MONTH) + 1);
    lm_day    = (short)cal.get(cal.DAY_OF_MONTH);
    lm_hour   = (short)cal.get(cal.HOUR_OF_DAY);
    lm_minute = (short)cal.get(cal.MINUTE);
    lm_second = (short)cal.get(cal.SECOND);
    this.data = updateData();
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(i18n.i18n_BGNSTR_TOSTRING),
        lm_month, lm_day, lm_year, lm_hour, lm_minute, lm_second,
        la_month, la_day, la_year, la_hour, la_minute, la_second);
  }

  /**
   * A method to update the byte information of the record
   *
   * @return  The byte information of the record
   */
  private byte[] updateData() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos = GDSByteConverter.fromShort((short)(lm_year - 1900), baos);
    baos = GDSByteConverter.fromShort(lm_month, baos);
    baos = GDSByteConverter.fromShort(lm_day, baos);
    baos = GDSByteConverter.fromShort(lm_hour, baos);
    baos = GDSByteConverter.fromShort(lm_minute, baos);
    baos = GDSByteConverter.fromShort(lm_second, baos);
    baos = GDSByteConverter.fromShort((short)(la_year - 1900), baos);
    baos = GDSByteConverter.fromShort(la_month, baos);
    baos = GDSByteConverter.fromShort(la_day, baos);
    baos = GDSByteConverter.fromShort(la_hour, baos);
    baos = GDSByteConverter.fromShort(la_minute, baos);
    baos = GDSByteConverter.fromShort(la_second, baos);

    return baos.toByteArray();
  }
} // end class GDSBgnstrRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
