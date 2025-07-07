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
 * Represents a GDSII TEXTTYPE record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSTexttypeRecord
extends GDSRecord {
  /** The text type */
  private short texttype;

  /**
   * Creates a new GDSTexttypeRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid TEXTTYPE record.
   */
  public GDSTexttypeRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSTexttypeRecord object.
   *
   * @param   texttype  The type.
   *
   * @throws  GDSRecordException  If the type is not in the range 0:255.
   */
  public GDSTexttypeRecord(short texttype)
    throws GDSRecordException {
    setTexttype(texttype);
    this.rectype = TEXTTYPE;
    this.dattype = SHORT_TYPE;
    this.length  = (short)6;
  }

  /**
   * Creates a new GDSTexttypeRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSTexttypeRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateShortRec(TEXTTYPE);
    this.texttype = validate(GDSByteConverter.toShort(data));
  }

  /**
   * Returns the text type.
   *
   * @return  The text type.
   */
  public short getTexttype(){return this.texttype;}

  /**
   * Sets the text type.
   *
   * @param   texttype  The type.
   *
   * @throws  GDSRecordException  If the type is not in the range 0:255.
   */
  public void setTexttype(short texttype)
    throws GDSRecordException {
    this.texttype = validate(texttype);
    this.data     = GDSByteConverter.writeShort(texttype);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_TEXTTYPE_TOSTRING), texttype);
  }

  /**
   * A method to validate the text type
   *
   * @param   texttype  The text type to validate
   *
   * @return  The text type
   *
   * @throws  GDSRecordException  If the text type number is not in the range
   *                              0-255
   */
  private short validate(short texttype)
    throws GDSRecordException {
    if((texttype >= 0) && (texttype <= 255)){return texttype;}

    throw new GDSRecordException(i18n.getString(i18n.i18n_TEXTTYPE_THROW));
  }
} // end class GDSTexttypeRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
