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
 * Represents a GDSII NODETYPE record.
 *
 * <p>Please see <a href='http://jgds.sf.net/gdsii.pdf'>The GDSII techincal
 * reference</a> for a more complete discussion of the GDSII stream syntax.</p>
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.8 $
 * @since    1.5
 */
public class GDSNodetypeRecord
extends GDSRecord {
  /** The node type number */
  private short nodetype;

  /**
   * Creates a new GDSNodetypeRecord object from an existing record.
   *
   * @param   rec  The base record.
   *
   * @throws  GDSRecordException  If the record is not a valid NODETYPE record.
   */
  public GDSNodetypeRecord(GDSRecord rec)
    throws GDSRecordException {
    this(rec.getLength(), rec.getRectype(), rec.getDattype(),
      rec.getData());
  }

  /**
   * Creates a new GDSNodetypeRecord object.
   *
   * @param   nodetype  The node type.
   *
   * @throws  GDSRecordException  If the node type is not in the range of 0:255.
   */
  public GDSNodetypeRecord(short nodetype)
    throws GDSRecordException {
    setNodetype(nodetype);
    this.rectype = NODETYPE;
    this.dattype = SHORT_TYPE;
    this.length  = (short)6;
  }

  /**
   * Creates a new GDSNodetypeRecord object.
   *
   * @param   length   The record length.
   * @param   rectype  The record type.
   * @param   dattype  The data type.
   * @param   data     The record data.
   *
   * @throws  GDSRecordException  If the record is malformed.
   */
  public GDSNodetypeRecord(short length, byte rectype, byte dattype,
      byte data[])
    throws GDSRecordException {
    super(length, rectype, dattype, data);
    validateShortRec(NODETYPE);
    this.nodetype = validate(GDSByteConverter.toShort(data));
  }

  /**
   * Returns the node type.
   *
   * @return  The node type.
   */
  public short getNodeType(){return this.nodetype;}

  /**
   * Sets the node type.
   *
   * @param   nodetype  The node type.
   *
   * @throws  GDSRecordException  If the node type is not in the range 0:255.
   */
  public void setNodetype(short nodetype)
    throws GDSRecordException {
    this.nodetype = validate(nodetype);
    this.data     = GDSByteConverter.writeShort(nodetype);
  }

  /**
   * Returns a description of the record.
   *
   * @return  A string representation of the record.
   */
  public String toString(){return i18n.getString(i18n.i18n_NODETYPE_TOSTRING);}

  /**
   * A method to validate the node type number
   *
   * @param   nodetype  The node type number to validate
   *
   * @return  The node type number
   *
   * @throws  GDSRecordException  If the node type number is outside the range
   *                              0-255
   */
  private short validate(short nodetype)
    throws GDSRecordException {
    if((nodetype >= 0) && (nodetype <= 255)){return nodetype;}

    throw new GDSRecordException(i18n.getString(i18n.i18n_NODETYPE_THROW));
  }
} // end class GDSNodetypeRecord


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
