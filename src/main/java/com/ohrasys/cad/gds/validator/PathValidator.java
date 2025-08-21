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
import com.ohrasys.cad.bnf.BNFOptionalTest;
import com.ohrasys.cad.bnf.BNFRequiredTest;
import com.ohrasys.cad.bnf.BNFTestException;
import com.ohrasys.cad.bnf.BNFTestImplementor;
import com.ohrasys.cad.bnf.BNFTestableObject;
import com.ohrasys.cad.gds.GDSBgnextnRecord;
import com.ohrasys.cad.gds.GDSDatatypeRecord;
import com.ohrasys.cad.gds.GDSElflagsRecord;
import com.ohrasys.cad.gds.GDSEndextnRecord;
import com.ohrasys.cad.gds.GDSLayerRecord;
import com.ohrasys.cad.gds.GDSPathtypeRecord;
import com.ohrasys.cad.gds.GDSPlexRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSSpecificDataConverter;
import com.ohrasys.cad.gds.GDSWidthRecord;
import com.ohrasys.cad.gds.GDSXyRecord;
import com.ohrasys.cad.gds.dao.Path;

/**
 * A Bachus Naur test for the GDSII &lt;path&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.15 $
 * @since    1.5
 */
public class PathValidator extends BNFRequiredTest {

    /**
     * Creates a new PathValidator object.
     *
     * @throws  BNFTestException  If an error in object creation occurs
     */
    public PathValidator() throws BNFTestException {
        super(buildTests());
    }

    /**
     * A method to retrieve data collected during the evaluation of this test
     *
     * @return  A list containing the data collected by this test during
     *          evaluation
     */
    @Override
    public Object collect() {
        List<Path> result = new ArrayList<Path>();
        List<Object> retValue = (List<Object>) super.collect();
        retValue = GDSSpecificDataConverter.flattenList(new ArrayList<Object>(), retValue);
        Path path = new Path();
        for (int i = 0; i < retValue.size(); i++) {
            GDSRecord rec = (GDSRecord) ((BNFTestableObject) retValue.get(i))
                    .getData();
            switch (rec.getRectype()) {
                case GDSRecord.ELFLAGS:
                    path.setExternal(((GDSElflagsRecord) rec).isExternal());
                    path.setTemplate(((GDSElflagsRecord) rec).isTemplate());
                    break;

                case GDSRecord.PLEX:
                    path.setGroup(((GDSPlexRecord) rec).getPlex());
                    break;

                case GDSRecord.LAYER:
                    path.setLayer(((GDSLayerRecord) rec).getLayer());
                    break;

                case GDSRecord.DATATYPE:
                    path.setDatatype(((GDSDatatypeRecord) rec).getDatatype());
                    break;

                case GDSRecord.PATHTYPE:
                    path.setPathtype(((GDSPathtypeRecord) rec).getPathtype());
                    break;

                case GDSRecord.WIDTH:
                    path.setWidth(((GDSWidthRecord) rec).getWidth());
                    break;

                case GDSRecord.BGNEXTN:
                    path.setStartExtension(((GDSBgnextnRecord) rec).getBgnextn());
                    break;

                case GDSRecord.ENDEXTN:
                    path.setEndExtension(((GDSEndextnRecord) rec).getEndextn());
                    break;

                case GDSRecord.XY:
                    path.setPoints(((GDSXyRecord) rec).getXy());
                    break;

                default:
                    break;
            }
        } // end for
        result.add(path);

        return result;
    } // end method collect

    /**
     * Returns a string representation of this validator
     *
     * @return  The physical address of this object
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * The list of child tests that comprise the PathValidator
     *
     * @return  A list of tests that comprise a Path element
     *
     * @throws  BNFTestException  If an error occurs during list creation
     */
    private static BNFTestImplementor[] buildTests() throws BNFTestException {
        return new BNFTestImplementor[] {
                new BNFNoFallthruTest(GDSRecord.PATH),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.ELFLAGS) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.PLEX) }),
                new BNFNoFallthruTest(GDSRecord.LAYER),
                new BNFNoFallthruTest(GDSRecord.DATATYPE),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.PATHTYPE) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.WIDTH) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.BGNEXTN) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.ENDEXTN) }),
                new BNFNoFallthruTest(GDSRecord.XY)
        };
    }
} // end class PathValidator

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
