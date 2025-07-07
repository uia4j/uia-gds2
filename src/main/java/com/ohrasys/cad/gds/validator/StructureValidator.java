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
import java.util.LinkedList;
import java.util.List;

import com.ohrasys.cad.bnf.BNFNoFallthruTest;
import com.ohrasys.cad.bnf.BNFOneOrMoreOptionalTest;
import com.ohrasys.cad.bnf.BNFOptionalTest;
import com.ohrasys.cad.bnf.BNFRequiredTest;
import com.ohrasys.cad.bnf.BNFTestException;
import com.ohrasys.cad.bnf.BNFTestImplementor;
import com.ohrasys.cad.bnf.BNFTestableObject;
import com.ohrasys.cad.gds.GDSBgnstrRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSSpecificDataConverter;
import com.ohrasys.cad.gds.GDSStrnameRecord;
import com.ohrasys.cad.gds.dao.Element;
import com.ohrasys.cad.gds.dao.Structure;

/**
 * A Bachus Naur test for the GDSII &lt;structure&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.14 $
 * @since    1.5
 */
public class StructureValidator extends BNFRequiredTest {

    /**
     * Creates a new StructureValidator object.
     *
     * @throws  BNFTestException  If an error during object creation occurs
     */
    public StructureValidator() throws BNFTestException {
        super(

                new BNFTestImplementor[] {
                        new BNFNoFallthruTest(GDSRecord.BGNSTR),
                        new BNFNoFallthruTest(GDSRecord.STRNAME),
                        new BNFOptionalTest(
                                new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.STRCLASS) }),
                        new BNFOneOrMoreOptionalTest(
                                new BNFTestImplementor[] { new ElementValidator() }),
                        new BNFNoFallthruTest(GDSRecord.ENDSTR)
                });
    }

    /**
     * A method to retrieve data collected during the evaluation of this test
     *
     * @return  A list containing the data collected by this test during
     *          evaluation
     */
    @Override
    public Object collect() {
        List<Structure> result = new ArrayList<Structure>();
        List<Object> retValue = (List<Object>) super.collect();
        retValue = GDSSpecificDataConverter.flattenList(
                new ArrayList<Object>(),
                retValue);
        List<Element> elements = new LinkedList<Element>();
        Structure struct = new Structure();
        for (int i = 0; i < retValue.size(); i++) {
            if (retValue.get(i) instanceof Element) {
                if (elements == null) {
                    elements = new LinkedList<Element>();
                }
                elements.add((Element) retValue.get(i));
            }
            else {
                GDSRecord rec = (GDSRecord) ((BNFTestableObject) retValue.get(i))
                        .getData();
                switch (rec.getRectype()) {
                    case GDSRecord.BGNSTR:
                        struct.setAccessed(((GDSBgnstrRecord) rec).getLastAccessedDate());
                        struct.setModified(((GDSBgnstrRecord) rec).getModificationDate());
                        break;

                    case GDSRecord.STRNAME:
                        struct.setName(((GDSStrnameRecord) rec).getStrname());
                        break;

                    default:
                        break;
                }
            }
        }
        struct.setElements(elements.toArray(new Element[elements.size()]));
        result.add(struct);

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
} // end class StructureValidator

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
