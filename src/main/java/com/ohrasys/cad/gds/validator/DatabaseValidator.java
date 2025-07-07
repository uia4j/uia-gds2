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

package com.ohrasys.cad.gds.validator;

import java.util.ArrayList;
import java.util.List;

import com.ohrasys.cad.bnf.BNFNoFallthruTest;
import com.ohrasys.cad.bnf.BNFOneOrMoreOptionalTest;
import com.ohrasys.cad.bnf.BNFOptionalTest;
import com.ohrasys.cad.bnf.BNFRequiredTest;
import com.ohrasys.cad.bnf.BNFTestException;
import com.ohrasys.cad.bnf.BNFTestImplementor;
import com.ohrasys.cad.bnf.BNFTestableObject;
import com.ohrasys.cad.gds.GDSAttrtableRecord;
import com.ohrasys.cad.gds.GDSBgnlibRecord;
import com.ohrasys.cad.gds.GDSFontsRecord;
import com.ohrasys.cad.gds.GDSGenerationsRecord;
import com.ohrasys.cad.gds.GDSHeaderRecord;
import com.ohrasys.cad.gds.GDSLibdirsizeRecord;
import com.ohrasys.cad.gds.GDSLibnameRecord;
import com.ohrasys.cad.gds.GDSLibsecurRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSReflibsRecord;
import com.ohrasys.cad.gds.GDSSpecificDataConverter;
import com.ohrasys.cad.gds.GDSSrfnameRecord;
import com.ohrasys.cad.gds.GDSUnitsRecord;
import com.ohrasys.cad.gds.dao.Database;
import com.ohrasys.cad.gds.dao.Format;
import com.ohrasys.cad.gds.dao.Structure;

/**
 * A Bachus Naur test for a GDSII database.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.15 $
 * @since    1.5
 */
public class DatabaseValidator extends BNFRequiredTest {

    /**
     * Creates a new DatabaseValidator object.
     *
     * @throws  BNFTestException  If an object creation error occurs
     */
    public DatabaseValidator() throws BNFTestException {
        super(buildTests());
    }

    /**
     * A method to collect the database data during parsing
     *
     * @return  A list of containing a Database object
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object collect() {
        List<Database> result = new ArrayList<Database>();
        List<Object> retValue = (List<Object>) super.collect();
        retValue = GDSSpecificDataConverter.flattenList(
                new ArrayList<Object>(),
                retValue);
        Database database = new Database();
        List<Structure> structures = null;
        for (int i = 0; i < retValue.size(); i++) {
            if (retValue.get(i) instanceof Structure) {
                if (structures == null) {
                    structures = new ArrayList<Structure>();
                }
                structures.add((Structure) retValue.get(i));
            }
            else if (retValue.get(i) instanceof Format) {
                database.setFormat((Format) retValue.get(i));
            }
            else {
                GDSRecord rec = (GDSRecord) ((BNFTestableObject) retValue.get(i))
                        .getData();
                switch (rec.getRectype()) {
                    case GDSRecord.HEADER:
                        database.setVersion(((GDSHeaderRecord) rec).getVersion());
                        break;

                    case GDSRecord.BGNLIB:
                        database.setAccessed(((GDSBgnlibRecord) rec).getLastAccessedDate());
                        database.setModified(((GDSBgnlibRecord) rec).getModificationDate());
                        break;

                    case GDSRecord.LIBDIRSIZE:
                        database.setDirSize(((GDSLibdirsizeRecord) rec).getLibdirsize());
                        break;

                    case GDSRecord.SRFNAME:
                        database.setSpacingRulesFile(((GDSSrfnameRecord) rec).getSrfname());
                        break;

                    case GDSRecord.LIBSECUR:
                        database.setAccessControls(((GDSLibsecurRecord) rec).getLibsecur());
                        break;

                    case GDSRecord.LIBNAME:
                        database.setName(((GDSLibnameRecord) rec).getLibname());
                        break;

                    case GDSRecord.REFLIBS:
                        database.setRefLibs(((GDSReflibsRecord) rec).getReflibs());
                        break;

                    case GDSRecord.FONTS:
                        database.setFonts(((GDSFontsRecord) rec).getFonts());
                        break;

                    case GDSRecord.ATTRTABLE:
                        database.setAttributeFile(((GDSAttrtableRecord) rec)
                                .getAttrtable());
                        break;

                    case GDSRecord.GENERATIONS:
                        database.setGenerations(((GDSGenerationsRecord) rec)
                                .getGenerations());
                        break;

                    case GDSRecord.UNITS:
                        database.setMetersPerDBU(((GDSUnitsRecord) rec).getMetersperdbu());
                        database.setUserUnitsPerDBU(((GDSUnitsRecord) rec).getUuperdbu());
                        break;

                    default:
                        break;
                }
            } // end if-else
        } // end for
        if (structures != null) {
            database.setStructures(structures.toArray(new Structure[structures.size()]));
        }
        result.add(database);

        return result;
    } // end method collect

    /**
     * The list of child tests that comprise the DatabaseValidator
     *
     * @return  A list of tests that comprise the Database element
     *
     * @throws  BNFTestException  If an error in test list creation occurs
     */
    private static BNFTestImplementor[] buildTests() throws BNFTestException {
        return new BNFTestImplementor[] {
                new BNFNoFallthruTest(GDSRecord.HEADER),
                new BNFNoFallthruTest(GDSRecord.BGNLIB),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.LIBDIRSIZE) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.SRFNAME) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.LIBSECUR) }),
                new BNFNoFallthruTest(GDSRecord.LIBNAME),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.REFLIBS) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.FONTS) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.ATTRTABLE) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.GENERATIONS) }),
                new BNFOptionalTest(new BNFTestImplementor[] { new FormatValidator() }),
                new BNFNoFallthruTest(GDSRecord.UNITS),
                new BNFOneOrMoreOptionalTest(
                        new BNFTestImplementor[] { new StructureValidator() }),
                new BNFNoFallthruTest(GDSRecord.ENDLIB)
        };
    }
} // end class DatabaseValidator

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
