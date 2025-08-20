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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ohrasys.cad.bnf.BNFTestException;
import com.ohrasys.cad.bnf.BNFTestableObject;
import com.ohrasys.cad.gds.validator.DatabaseValidator;

/**
 * A basic GDS file reader
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.13 $
 * @since    1.5
 */
public class GDSReader extends InputStreamReader {

    /** The number of bytes read */
    private int bytesRead;

    /** The input stream */
    private GDSInputStream in;

    /** The Backus-Naur test validator for the GDS database */
    private DatabaseValidator isValid;

    /** A flag to indicate whether we should validate the data read */
    private boolean validating;

    /**
     * Creates a new GDSReader object.
     *
     * @param   file  The file to read from.
     *
     * @throws  FileNotFoundException  If the file doesn't exist or is not
     *                                 readable.
     */
    public GDSReader(File file) throws FileNotFoundException {
        this(file, false);
    }

    /**
     * Creates a new GDSReader object.
     *
     * @param   fd  The file descriptor to read from.
     *
     * @throws  FileNotFoundException  If the file doesn't exist or is not
     *                                 readable.
     */
    public GDSReader(FileDescriptor fd) throws FileNotFoundException {
        this(fd, false);
    }

    /**
     * Creates a new GDSReader object.
     *
     * @param   fileName  The file to read from.
     *
     * @throws  FileNotFoundException  If the file doesn't exist or is not
     *                                 readable.
     */
    public GDSReader(String fileName) throws FileNotFoundException {
        this(fileName, false);
    }

    /**
     * Creates a new GDSReader object.
     *
     * @param   in          The input stream to read from
     * @param   validating  Set to true if on the fly validating is to be
     *                      performed.
     *
     * @throws  FileNotFoundException  If the input file doesn't exist or is not
     *                                 readable.
     */
    public GDSReader(GDSInputStream in, boolean validating) throws FileNotFoundException {
        super(in);
        this.validating = validating;

        if (validating) {
            try {
                this.isValid = new DatabaseValidator();
                this.isValid.reset();
            }
            catch (BNFTestException ex) {
                validating = false;
            }
        }

        this.bytesRead = 0;
        this.in = in;
    }

    /**
     * Creates a new GDSReader object.
     *
     * @param   fd          The file descriptor to read from
     * @param   validating  Set to true if on the fly validating is to be
     *                      performed.
     *
     * @throws  FileNotFoundException  If the file doesn't exist or is not
     *                                 readable.
     */
    public GDSReader(FileDescriptor fd, boolean validating) throws FileNotFoundException {
        this(new GDSInputStream(fd), validating);
    }

    /**
     * Creates a new GDSReader object.
     *
     * @param   fileName    The file to read from.
     * @param   validating  Set to true if on the fly validating is to be
     *                      performed.
     *
     * @throws  FileNotFoundException  If the file doesn't exist or is not
     *                                 readable.
     */
    public GDSReader(String fileName, boolean validating) throws FileNotFoundException {
        this(new GDSInputStream(fileName), validating);
    }

    /**
     * Creates a new GDSReader object.
     *
     * @param   file        The file to read from.
     * @param   validating  Set to true if on the fly validating is to be
     *                      performed.
     *
     * @throws  FileNotFoundException  If the file doesn't exist or is not
     *                                 readable.
     */
    public GDSReader(File file, boolean validating) throws FileNotFoundException {
        this(new GDSInputStream(file), validating);
    }

    /**
     * A method to determine the number of bytes read from the file.
     *
     * @return  The number of bytes read.
     *
     * @throws  GDSReaderException  If an error in processing occurs.
     */
    public int getRead() throws GDSReaderException {
        if (this.in == null) {
            throw new GDSReaderException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_GDSREADER_THROW1));
        }

        return this.bytesRead;
    }

    /**
     * A method to determine the number of bytes remaining to be read
     *
     * @return  The number of bytes remaining to be read.
     *
     * @throws  GDSReaderException  If an error in processing occurs.
     */
    public int getRemaining() throws GDSReaderException {
        if (this.in == null) {
            throw new GDSReaderException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_GDSREADER_THROW1));
        }

        try {
            return this.in.available();
        }
        catch (IOException e) {
            throw new GDSReaderException(e.getMessage());
        }
    }

    /**
     * A method to determine the size of the GDS file.
     *
     * @return  The size of the file in bytes.
     *
     * @throws  GDSReaderException  If an error in processing occurs.
     */
    public int getSize() throws GDSReaderException {
        if (this.in == null) {
            throw new GDSReaderException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_GDSREADER_THROW1));
        }

        try {
            return this.in.available() + this.bytesRead;
        }
        catch (IOException e) {
            throw new GDSReaderException(e.getMessage());
        }
    }

    /**
     * Marks the current position in the file.  This is an unsupported method of
     * the Reader interface.
     *
     * @param   readAheadLimit  The maximum read ahead limit.
     *
     * @throws  IOException  Always thrown. This method is not supported.
     */
    @Override
    public void mark(int readAheadLimit) throws IOException {
        throw new IOException(GDSI18NFactory.getString(GDSI18NFactory.i18n_GDSREADER_THROW2));
    }

    /**
     * Determines if marks are supported by this reader.  This method always
     * returns false since the GDSReader doesn't support marks.
     *
     * @return  false.
     */
    @Override
    public boolean markSupported() {
        return false;
    }

    /**
     * Reads the next value from the stream.
     *
     * @return  The value read.
     *
     * @throws  IOException  If an IO exception occurs.
     */
    @Override
    public int read() throws IOException {
        int result = super.read();

        if (result != -1) {
            this.bytesRead += 4;
        }

        return result;
    }

    /**
     * A blocking read into a character buffer.
     *
     * @param   cbuf  The buffer to read into to
     *
     * @return  The number of characters read or -1 if the end of the file is
     *          reached.
     *
     * @throws  IOException  If an IO exception occurs.
     */
    @Override
    public int read(char cbuf[]) throws IOException {
        int result = super.read(cbuf);

        if (result != -1) {
            this.bytesRead += result;
        }

        return result;
    }

    /**
     * Reads a string of characters into a buffer.
     *
     * @param   buf     The destination for the read.
     * @param   offset  The starting offset from the beginning of the destination
     *                  buffer.
     * @param   length  The number of characters to read.
     *
     * @return  The number of characters read or -1 if the end of file is reached.
     *
     * @throws  IOException  If an IO exception occurs.
     */
    @Override
    public int read(char buf[], int offset, int length) throws IOException {
        int result = super.read(buf, offset, length);

        if (result != -1) {
            this.bytesRead += result;
        }

        return result;
    }

    /**
     * Reads a single GDSRecord from the filestream.
     *
     * @return  The next available GDSII record.
     *
     * @throws  GDSReaderException  If an error in processing occurs.
     */
    public Object readRecord() throws GDSReaderException {
        if (this.in == null) {
            throw new GDSReaderException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_GDSREADER_THROW1));
        }

        GDSRecord result = null;

        try {
            result = this.in.readRecord();
        }
        catch (GDSRecordException e) {
            throw new GDSReaderException(e.getMessage());
        }

        if (result == null) {
            return result;
        }

        try {
            result = GDSRecordFactory.expandRecord(result);
        }
        catch (GDSRecordException e) {
            throw new GDSReaderException(e.getMessage());
        }

        this.bytesRead += result.getLength();
        if (this.validating) {
            this.isValid.test(new BNFTestableObject(result) {

                @Override
                public int getToken() {
                    return ((GDSRecord) getData()).getRectype();
                }
            });
        }

        return result;
    } // end method readRecord

    /**
     * Resets the stream and mark pointer.  This is an unsupported method of the
     * reader interface.
     *
     * @throws  IOException  Always thrown as reset is not supported by GDSReader.
     */
    @Override
    public void reset() throws IOException {
        throw new IOException(GDSI18NFactory.getString(GDSI18NFactory.i18n_GDSREADER_THROW3));
    }

    /**
     * Skips a finite number of bytes in the stream
     *
     * @param   num  The number of bytes to skip
     *
     * @return  The actual number of bytes skipped
     *
     * @throws  IOException  If an IO exception occurs
     */
    @Override
    public long skip(long num) throws IOException {
        long result = super.skip(num);
        this.bytesRead += result;

        return result;
    }

    /**
     * Returns a string representation of this class
     *
     * @return  The physical address of this instance
     */
    @Override
    public String toString() {
        return super.toString();
    }
} // end class GDSReader

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
