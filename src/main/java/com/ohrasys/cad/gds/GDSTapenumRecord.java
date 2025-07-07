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
 * Represents a GDSII TAPENUM record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSTapenumRecord
extends GDSRecord {
  /** The tape number */
  private short tapenum;

  /**
   * Creates a new GDSTapenumRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid TAPENUM record.
   */
  public GDSTapenumRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSTapenumRecord object.
   *
   * @param   tapenum  The tape number.
   *
   * @throws  GDSRecordException  If the tapenumber is less than 1.
   */
  public GDSTapenumRecord(short tapenum)
    throws GDSRecordException {
    setTapenum(tapenum);
    this.rectype = TAPENUM;
    this.dattype = SHORT_TYPE;
    this.length  = (short)6;
  }

  /**
   * Creates a new GDSTapenumRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSTapenumRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateShortRec(TAPENUM);
    this.tapenum = validate(GDSByteConverter.toShort(data));
  }

  /**
   * Returns the tape number.
   *
   * @return  The tape number.
   */
  public short getTapenum(){return this.tapenum;}

  /**
   * Sets the tape number.
   *
   * @param   tapenum  The tape number.
   *
   * @throws  GDSRecordException  If the tapenumber is less than 1.
   */
  public void setTapenum(short tapenum)
    throws GDSRecordException {
    this.tapenum = validate(tapenum);
    this.data    = GDSByteConverter.writeShort(tapenum);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_TAPENUM_TOSTRING), tapenum);
  }

  /**
   * A method to validate the tape number
   *
   * @param   tapenum  The tape number to validate
   *
   * @return  The tape number
   *
   * @throws  GDSRecordException  If the tape number is less than 1
   */
  private short validate(short tapenum)
    throws GDSRecordException {
    if(tapenum < 1) {
      throw new GDSRecordException(i18n.getString(
          i18n.i18n_TAPENUM_THROW));
    }

    return tapenum;
  }
} // end class GDSTapenumRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
