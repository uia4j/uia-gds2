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
 * The GDSOutputStream is used to write GDSRecords to a data source.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.5 $
 * @since    1.5
 */
public class GDSOutputStream
extends FileOutputStream {
  /**
   * Creates a new GDSOutputStream object.
   *
   * @param   name  The name of the GDS file.
   *
   * @throws  FileNotFoundException  If the file cannot be found or is not
   *                                 readable
   */
  public GDSOutputStream(String name)
    throws FileNotFoundException{super(name);}

  /**
   * Creates a new GDSOutputStream object.
   *
   * @param   file  The GDS file
   *
   * @throws  FileNotFoundException  If the file cannot be found or is not
   *                                 readable
   */
  public GDSOutputStream(File file)
    throws FileNotFoundException{super(file);}

  /**
   * Creates a new GDSOutputStream object.
   *
   * @param   fdObj  The GDS file descriptor
   *
   * @throws  FileNotFoundException  If the file cannot be found or is not
   *                                 readable
   */
  public GDSOutputStream(FileDescriptor fdObj)
    throws FileNotFoundException{super(fdObj);}

  /**
   * Returns a string representation of this class
   *
   * @return  The physical address of this instance
   */
  public String toString(){return super.toString();}

  /**
   * Reads a GDS record from the stream.
   *
   * @param   record  The GDSRecord to write out.
   *
   * @return  The number of bytes written.
   *
   * @throws  IOException  If an IO error occurs.
   */
  public int writeRecord(GDSRecord record)
    throws IOException {
    write(record.toBytes());

    return record.getLength();
  }
} // end class GDSOutputStream


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
