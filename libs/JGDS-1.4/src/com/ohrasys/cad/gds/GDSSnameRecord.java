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

/**
 * Represents a GDSII SNAME record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSSnameRecord
extends GDSRecord {
  /** The structure name */
  private String sname;

  /**
   * Creates a new GDSSnameRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid SNAME record.
   */
  public GDSSnameRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSSnameRecord object.
   *
   * @param   sname  The structure name.
   *
   * @throws  GDSRecordException  If the structure name doesn't consist of
   *                              [A-Z,a-z,0-9,?,_,$].
   */
  public GDSSnameRecord(String sname)
    throws GDSRecordException {
    setSname(sname);
    this.rectype = SNAME;
    this.dattype = STRING_TYPE;
  }

  /**
   * Creates a new GDSSnameRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSSnameRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateStringRec(SNAME, MAX_REC_LEN, 2);
    this.sname = validate(GDSSpecificDataConverter.toJavaString(data));
  }

  /**
   * Returns the structure name.
   *
   * @return  The structure name.
   */
  public String getSname(){return new String(sname);}

  /**
   * Sets the structure name.
   *
   * @param   sname  The structure name.
   *
   * @throws  GDSRecordException  If the structure name doesn't consist of
   *                              [A-Z,a-z,0-9,?,_,$].
   */
  public void setSname(String sname)
    throws GDSRecordException {
    this.sname  = validate(new String(sname));
    this.data   = GDSSpecificDataConverter.writeJavaString(sname);
    this.length = (short)(data.length + 4);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return (GDSStringUtil.sprintf(i18n.getString(i18n.i18n_SNAME_TOSTRING),
          sname));
  }

  /**
   * A method to validate the structure name
   *
   * @param   sname  The structure name to validate
   *
   * @return  The structure name
   *
   * @throws  GDSRecordException  If the structure name is null or the length is
   *                              not in the range of 1-MAX_REC_LENGTH or the
   *                              name contains illegal characters
   */
  private String validate(String sname)
    throws GDSRecordException {
    if((sname == null) || (sname.length() < 1) ||
        (sname.length() > MAX_REC_LEN)) {
      throw new GDSRecordException(i18n.getString(
          i18n.i18n_SNAME_THROW1));
    }

    String validChars = "abcdefghijklmnopqrstuvwxyz0123456789?_$" /*NOI18N*/;

    if(!GDSStringUtil.consistsOf(sname.toLowerCase(), validChars)) {
      throw new GDSRecordException(i18n.getString(
          i18n.i18n_SNAME_THROW2));
    }

    return sname;
  }
} // end class GDSSnameRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
