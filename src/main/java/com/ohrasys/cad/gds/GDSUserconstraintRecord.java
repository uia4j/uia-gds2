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
 * Represents a GDSII USERCONSTRAINT record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.7 $
 * @since    1.5
 */
public class GDSUserconstraintRecord
extends GDSRecord {
  /**
   * Creates a new GDSUserconstraintRecord object.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSUserconstraintRecord()
    throws GDSRecordException {
    this.rectype = USERCONSTRAINT;
    this.dattype = NO_DATA_TYPE;
    this.length  = (short)4;
    this.data    = new byte[0];
  }

  /**
   * Creates a new GDSUserconstraintRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid USERCONSTRAINT
   *                              record.
   */
  public GDSUserconstraintRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSUserconstraintRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSUserconstraintRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateNodataRec(USERCONSTRAINT);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A String representation of the record.
   */
  public String toString() {
    return i18n.getString(i18n.i18n_USERCONSTRAINT_TOSTRING);
  }
} // end class GDSUserconstraintRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
