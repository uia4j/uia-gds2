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
import com.ohrasys.cad.gds.GDSAngleRecord;
import com.ohrasys.cad.gds.GDSMagRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSSpecificDataConverter;
import com.ohrasys.cad.gds.GDSStransRecord;
import com.ohrasys.cad.gds.dao.Transform;

/**
 * A Bachus Naur test for the GDSII &lt;strans&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.14 $
 * @since    1.5
 */
public class StransValidator extends BNFRequiredTest {

    /**
     * Creates a new StransValidator object.
     *
     * @throws  BNFTestException  If an error during object creation occurs
     */
    public StransValidator() throws BNFTestException {
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
        List<Transform> result = new ArrayList<Transform>();
        List<Object> retValue = (List<Object>) super.collect();
        retValue = GDSSpecificDataConverter.flattenList(new ArrayList<Object>(), retValue);
        Transform transform = new Transform();
        for (int i = 0; i < retValue.size(); i++) {
            GDSRecord rec = (GDSRecord) ((BNFTestableObject) retValue.get(i))
                    .getData();
            switch (rec.getRectype()) {
                case GDSRecord.STRANS:
                    transform.setMirrored(((GDSStransRecord) rec).isMirroredX());
                    transform.setRelativeAngle(!((GDSStransRecord) rec).isAbsAngle());
                    transform.setRelativeMagnification(!((GDSStransRecord) rec).isAbsMag());
                    break;

                case GDSRecord.MAG:
                    transform.setMagnification(((GDSMagRecord) rec).getMag());
                    break;

                case GDSRecord.ANGLE:
                    transform.setAngle(((GDSAngleRecord) rec).getAngle());
                    break;

                default:
                    break;
            }
        }
        result.add(transform);

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
     * The list of tests that comprise the StransValidator
     *
     * @return  A list of tests that comprise the STRANS element
     *
     * @throws  BNFTestException  If an error in list creation occurs
     */
    private static BNFTestImplementor[] buildTests() throws BNFTestException {
        return new BNFTestImplementor[] {
                new BNFNoFallthruTest(GDSRecord.STRANS),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.MAG) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.ANGLE) })
        };
    }
} // end class StransValidator

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
