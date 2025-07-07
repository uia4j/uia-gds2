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
 * Represents a GDSII TAPECODE record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSTapecodeRecord
extends GDSRecord {
  /** The list of tapecodes */
  private short tapecode[];

  /**
   * Creates a new GDSTapecodeRecord object from and existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid TAPECODE record.
   */
  public GDSTapecodeRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSTapecodeRecord object.
   *
   * @param   tapecode  The tapecode.
   *
   * @throws  GDSRecordException  If the tapecode doesnt contain 6 elements.
   */
  public GDSTapecodeRecord(short tapecode[])
    throws GDSRecordException {
    setTapecode(tapecode);
    this.rectype = TAPECODE;
    this.dattype = SHORT_TYPE;
    this.length  = (short)16;
  }

  /**
   * Creates a new GDSTapecodeRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSTapecodeRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateShortRec(TAPECODE, 12);

    ByteArrayInputStream bais   = new ByteArrayInputStream(data);
    short                code[] = new short[6];

    for(int i = 0;i < 6;i++) {
      code[i] = GDSByteConverter.readShort(bais);
    }

    this.tapecode = code;
  }

  /**
   * Returns the tapecode.
   *
   * @return  The tapecode.
   */
  public short[] getTapecode() {
    short result[] = new short[tapecode.length];
    System.arraycopy(tapecode, 0, result, 0, tapecode.length);

    return result;
  }

  /**
   * Sets the tapecode.
   *
   * @param   code  The tapecode.
   *
   * @throws  GDSRecordException  If the tapecode doesnt contain 6 elements.
   */
  public void setTapecode(short code[])
    throws GDSRecordException {
    if(code.length != 6) {
      throw new GDSRecordException(i18n.getString(
          i18n.i18n_TAPECODE_THROW));
    }

    this.tapecode = new short[6];
    System.arraycopy(code, 0, tapecode, 0, code.length);
    this.data = updateData();
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    String result = i18n.getString(i18n.i18n_TAPECODE_TOSTRING1);

    for(int i = 0;i < tapecode.length;i++) {
      result += GDSStringUtil.sprintf(i18n.getString(
            i18n.i18n_TAPECODE_TOSTRING2), i, tapecode[i]);
    }

    return result;
  }

  /**
   * A method to update the byte data of the record
   *
   * @return  The updated byte data
   */
  private byte[] updateData() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    for(int i = 0;i < tapecode.length;i++) {
      baos = GDSByteConverter.fromShort(tapecode[i], baos);
    }

    return baos.toByteArray();
  }
} // end class GDSTapecodeRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
