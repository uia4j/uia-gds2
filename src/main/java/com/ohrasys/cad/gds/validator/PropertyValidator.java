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
import com.ohrasys.cad.bnf.BNFRequiredTest;
import com.ohrasys.cad.bnf.BNFTestException;
import com.ohrasys.cad.bnf.BNFTestImplementor;
import com.ohrasys.cad.bnf.BNFTestableObject;
import com.ohrasys.cad.gds.GDSPropattrRecord;
import com.ohrasys.cad.gds.GDSPropvalueRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSSpecificDataConverter;
import com.ohrasys.cad.gds.dao.Property;

/**
 * A Bachus Naur test for the GDSII &lt;property&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.14 $
 * @since    1.5
 */
public class PropertyValidator extends BNFRequiredTest {

    /**
     * Creates a new PropertyValidator object.
     *
     * @throws  BNFTestException  If an error in object creation occurs
     */
    public PropertyValidator() throws BNFTestException {
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
        List<Property> result = new ArrayList<Property>();
        List<Object> retValue = (List<Object>) super.collect();
        retValue = GDSSpecificDataConverter.flattenList(new ArrayList<Object>(), retValue);
        Property prop = new Property();
        for (int i = 0; i < retValue.size(); i++) {
            GDSRecord rec = (GDSRecord) ((BNFTestableObject) retValue.get(i))
                    .getData();
            switch (rec.getRectype()) {
                case GDSRecord.PROPATTR:
                    prop.setNumber(((GDSPropattrRecord) rec).getPropattr());
                    break;

                case GDSRecord.PROPVALUE:
                    prop.setValue(((GDSPropvalueRecord) rec).getPropvalue());
                    result.add(prop);
                    prop = new Property();
                    break;

                default:
                    break;
            }
        }

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
     * The list of child tests that comprise the PropertyValidator
     *
     * @return  A list of tests that comprise the Property element
     *
     * @throws  BNFTestException  If an error occurs during list creation
     */
    private static BNFTestImplementor[] buildTests() throws BNFTestException {
        return new BNFTestImplementor[] {
                new BNFNoFallthruTest(GDSRecord.PROPATTR),
                new BNFNoFallthruTest(GDSRecord.PROPVALUE)
        };
    }
} // end class PropertyValidator

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
