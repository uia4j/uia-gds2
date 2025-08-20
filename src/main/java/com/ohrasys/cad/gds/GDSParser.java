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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.ohrasys.cad.bnf.BNFTestException;
import com.ohrasys.cad.bnf.BNFTestResult;
import com.ohrasys.cad.bnf.BNFTestableObject;
import com.ohrasys.cad.gds.validator.DatabaseValidator;

/**
 * A GDS design parser.  The parser will parse a GDS design, verify that it is a
 * well formed design and record statistics on the types of GDS records
 * contained in the database.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.28 $
 * @since    1.5
 */
public class GDSParser {

    /** The text separator used in reporting */
    private static final String sep = GDSStringUtil.getSeparator(
            "=-" /*NOI18N*/, 79);

    /**
     * Indicates whether the parser should collect and transform data instead of
     * returning a list of GDSRecords.
     */
    private boolean collecting;

    /**
     * A flag indicating that the output stream onto which the parser information
     * is read should not be closed at the conclusion of parsing.  This is often
     * the case if the output stream is the standard output, for example.
     */
    private boolean dontclose;

    /** The internationalized string factory */
    private GDSI18NFactory i18n;

    /** The string used to insert indentation in the parser report */
    private String indent;

    /**
     * The Backus-Naur test validator for GDS databases.  Used to determine if the
     * input database is valid when using a validating parser
     */
    private DatabaseValidator isValid;

    /** Indicates that the parser can be preempted by other threads */
    private boolean preemptable;

    /** The list of database records parsed from the GDS file */
    private List<Object> records;

    /** The parser statistics tracker */
    private GDSStatistics stats;

    /**
     * The writer object used to print the parser information to the output stream
     */
    private Writer writer;

    /**
     * Creates a new GDSParser object.
     */
    public GDSParser() {
        this(true);
    }

    /**
     * Creates a new GDSParser object.
     *
     * @param  isPreemptable  True if the parser should ocassionally yield to
     *                        other threads.
     */
    public GDSParser(boolean isPreemptable) {
        this.stats = new GDSStatistics();

        this.isValid = null;
        try {
            this.isValid = new DatabaseValidator();
        }
        catch (BNFTestException e) {
            System.out.println(e.getMessage());
            this.isValid = null;
        }
        this.preemptable = isPreemptable;
    }

    /**
     * Parses a GDS design, records statistics about the design and verifies that
     * it is well formed.
     *
     * <p>Please see <a href="http://jgds.sf.net/gdsii.pdf">The GDSII
     * techincal reference</a> for a more complete discussion of the GDSII stream
     * syntax.</p>
     *
     * @param   in   The File from which to read.
     * @param   out  The output stream to write statistics and log information to.
     *               May be null.
     *
     * @return  An <code>ArrayList</code> containing the ordered list of
     *          GDSRecords parsed.
     */
    public List parseDesign(File in, OutputStream out) {
        boolean resultVld = true;
        open(out);
        this.records = new ArrayList<Object>(estimateSize(in));
        this.stats.clear();
        this.isValid.reset();
        this.isValid.setCollecting(this.collecting);
        write(sep);

        GDSRecord record = null;
        this.indent = new String();
        try {
            GDSInputStream gdsin = new GDSInputStream(in);

            while ((record = gdsin.readRecord()) != null) {
                if (this.preemptable && ((this.records.size() % 100) == 0)) {
                    Thread.yield();
                }
                BNFTestableObject testable = new BNFTestableObject(record) {

                    @Override
                    public int getToken() {
                        return ((GDSRecord) getData()).getRectype();
                    }
                };
                BNFTestResult result = this.isValid.test(testable);
                if (result.isFailed()) {
                    throw new GDSRecordException(GDSStringUtil.sprintf(
                            GDSI18NFactory.getString(GDSI18NFactory.i18n_GDSPARSER_THROW1),
                            record.toString().subSequence(0,
                                    record.toString().indexOf(' '))));
                }
                this.stats.record(record);
                this.records.add(record);

                if (record.getRectype() != GDSRecord.NULL) {
                    write("\t" /*NOI18N*/ + this.indent +
                            record.toString().replaceAll("\n" /*NOI18N*/,
                                    "\n\t" /*NOI18N*/ + this.indent));

                    if ((record.getRectype() == GDSRecord.BOUNDARY) ||
                            (record.getRectype() == GDSRecord.PATH) ||
                            (record.getRectype() == GDSRecord.SREF) ||
                            (record.getRectype() == GDSRecord.AREF) ||
                            (record.getRectype() == GDSRecord.TEXT) ||
                            (record.getRectype() == GDSRecord.NODE) ||
                            (record.getRectype() == GDSRecord.BGNSTR)) {
                        this.indent += "\t" /*NOI18N*/;
                    }
                    else if (((record.getRectype() == GDSRecord.ENDEL) ||
                            (record.getRectype() == GDSRecord.ENDSTR)) &&
                            (this.indent.length() > 0)) {
                        this.indent = this.indent.substring(0, this.indent.length() - 1);
                    }
                }

                if (Thread.currentThread().interrupted()) {
                    if (this.collecting) {
                        this.isValid.collect();
                    }
                    this.isValid.reset();
                    resultVld = false;

                    break;
                }
                if (result.isFinished()) {
                    break;
                }
            } // end while
            try {
                gdsin.close();
            }
            catch (IOException ex) { /* can't do anything now */
            }
        }
        catch (GDSRecordException e) {
            write(GDSStringUtil.sprintf(
                    GDSI18NFactory.getString(GDSI18NFactory.i18n_GDSPARSER_THROW2),
                    e.getMessage()));
            resultVld = false;
        }
        catch (FileNotFoundException e) {
            write(GDSStringUtil.sprintf(
                    GDSI18NFactory.getString(GDSI18NFactory.i18n_GDSPARSER_THROW2),
                    e.getMessage()));
            resultVld = false;
        }
        write(sep + "\n" /*NOI18N*/ + this.stats + "\n" /*NOI18N*/ + sep);
        write(
                GDSStringUtil.sprintf(GDSI18NFactory.getString(GDSI18NFactory.i18n_GDSPARSER_TOSTRING),
                        this.records.size()) + "\n" /*NOI18N*/ + sep);
        close();
        if (resultVld) {
            if (this.collecting) {
                return (List) this.isValid.collect();
            }
            /* else*/ {
                return this.records;
            }
        }
        else {
            this.isValid.reset();
            this.isValid.collect();

            return null;
        }
    } // end method parseDesign

    /**
     * Turns on data collection in the parser
     *
     * @param  isCollecting  true if data collection is to be performed
     */
    public void setCollecting(boolean isCollecting) {
        this.collecting = isCollecting;
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

    /** A method to close the output stream if required */
    private void close() {
        try {
            if ((this.writer != null) && (!this.dontclose)) {
                this.writer.close();
            }
            else {
                this.writer.flush();
            }
        }
        catch (IOException ex) {
            /* cant' do anything now */
        }
    }

    /**
     * A method to estimate the number of records in the GDS database.  This is
     * used to initialize the <code>records</code> member variable.
     *
     * @param   in  The input GDS file
     *
     * @return  The estimated number of GDS records in the file
     */
    private int estimateSize(File in) {
        return (int) (in.length() / 4);
    }

    /**
     * Opens the output stream onto which the parser output will be written
     *
     * @param  out  The output stream to write parser output to
     */
    private void open(OutputStream out) {
        if (out != null) {
            this.writer = new BufferedWriter(new PrintWriter(out));
        }

        if (out == System.out) {
            this.dontclose = true;
        }
        else {
            this.dontclose = false;
        }
    }

    /**
     * Writes a message to the output stream
     *
     * @param  msg  The message to output
     */
    private void write(String msg) {
        if (this.writer != null) {
            try {
                this.writer.write(msg + "\n" /* NOI18N */);
            }
            catch (IOException ex) {
                /* can't do anything now */
            }
        }
    }
} // end class GDSParser

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
