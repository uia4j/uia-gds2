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
 * Represents a GDSII SRFNAME record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSSrfnameRecord
extends GDSRecord {
  /** The spacing rule file name */
  private String srfname;

  /**
   * Creates a new GDSSrfnameRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid SRFNAME record.
   */
  public GDSSrfnameRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSSrfnameRecord object.
   *
   * @param   srfname  The spacing rules file name.
   *
   * @throws  GDSRecordException  If the rules file name is of zero length.
   */
  public GDSSrfnameRecord(String srfname)
    throws GDSRecordException {
    setSrfname(srfname);
    this.rectype = SRFNAME;
    this.dattype = STRING_TYPE;
  }

  /**
   * Creates a new GDSSrfnameRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSSrfnameRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateStringRec(SRFNAME, MAX_REC_LEN, 2);
    this.srfname = validate(GDSSpecificDataConverter.toJavaString(data));
  }

  /**
   * Returns the spacing rules filename.
   *
   * @return  The spacing rules filename.
   */
  public String getSrfname(){return new String(srfname);}

  /**
   * Sets the spacing rules file name.
   *
   * @param   srfname  The spacing rules file name.
   *
   * @throws  GDSRecordException  If the rules file name is of zero length.
   */
  public void setSrfname(String srfname)
    throws GDSRecordException {
    this.srfname = validate(new String(srfname));
    this.data    = GDSSpecificDataConverter.writeJavaString(srfname);
    this.length  = (short)(data.length + 4);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString() {
    return GDSStringUtil.sprintf(i18n.getString(
          i18n.i18n_SRFNAME_TOSTRING), srfname);
  }

  /**
   * A method to validate the spacing rule file name
   *
   * @param   srfname  The spacing rule file name to validate
   *
   * @return  The spacing rule file name
   *
   * @throws  GDSRecordException  If the name is null, has a length not in the
   *                              range 1-MAX_REC_LENGTH, or contains illegal
   *                              characters
   */
  private String validate(String srfname)
    throws GDSRecordException {
    if((srfname == null) || (srfname.length() < 1) ||
        (srfname.length() > MAX_REC_LEN)) {
      throw new GDSRecordException(i18n.getString(
          i18n.i18n_SRFNAME_THROW));
    }

    return srfname;
  }
} // end class GDSSrfnameRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
