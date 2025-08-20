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

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * The GDSInputStream is used to read GDSRecord from a data source.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.11 $
 * @since    1.5
 */
public class GDSInputStream extends FileInputStream {

    /**
     * Creates a new GDSInputStream object.
     *
     * @param   name  The name of the GDS file.
     *
     * @throws  FileNotFoundException  If the file cannot be found or is not
     *                                 readable
     */
    public GDSInputStream(String name) throws FileNotFoundException {
        super(name);
    }

    /**
     * Creates a new GDSInputStream object.
     *
     * @param   file  The GDS file
     *
     * @throws  FileNotFoundException  If the file cannot be found or is not
     *                                 readable
     */
    public GDSInputStream(File file) throws FileNotFoundException {
        super(file);
    }

    /**
     * Creates a new GDSInputStream object.
     *
     * @param   fdObj  The GDS file descriptor
     *
     * @throws  FileNotFoundException  If the file cannot be found or is not
     *                                 readable
     */
    public GDSInputStream(FileDescriptor fdObj) throws FileNotFoundException {
        super(fdObj);
    }

    /**
     * Reads a GDS record from the stream.
     *
     * @return  A GDSRecord from the stream.
     *
     * @throws  GDSRecordException  If an invalid record is read.
     */
    public GDSRecord readRecord() throws GDSRecordException {
        try {
            if (available() < 2) {
                return null;
            }
        }
        catch (IOException e) {
            return null;
        }

        int length = GDSByteConverter.readShort(this);
        byte data[] = null;
        byte rectype = GDSRecord.NULL;
        byte dattype = GDSRecord.NO_DATA_TYPE;

        boolean integer = false;
        if (length < 0) {
            integer = true;
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.order(ByteOrder.BIG_ENDIAN); // Or LITTLE_ENDIAN based on desired byte order
            buffer.putInt(length);
            byte[] len = buffer.array();
            len[0] = 0;
            len[1] = 0;
            length = ByteBuffer.wrap(len).getInt();
        }

        if (length > 0) {
            data = new byte[length - 4];

            try {
                rectype = GDSByteConverter.readByte(this);
                dattype = GDSByteConverter.readByte(this);
                if (integer) {
                    rectype = GDSRecord.XY;
                    dattype = GDSRecord.INT_TYPE;
                }
                read(data, 0, length - 4);
            }
            catch (IOException e) {
                return null;
            }
        }
        else {
            data = new byte[0];
            rectype = GDSRecord.NULL;
            dattype = GDSRecord.NO_DATA_TYPE;
        }

        GDSRecord result = new GDSRecord(length, rectype, dattype, data);

        return GDSRecordFactory.expandRecord(result);
    } // end method readRecord

    /**
     * Returns a string representation of this class
     *
     * @return  The physical address of this instance
     */
    @Override
    public String toString() {
        return super.toString();
    }
} // end class GDSInputStream

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
