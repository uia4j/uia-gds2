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
import com.ohrasys.cad.gds.GDSElflagsRecord;
import com.ohrasys.cad.gds.GDSLayerRecord;
import com.ohrasys.cad.gds.GDSNodetypeRecord;
import com.ohrasys.cad.gds.GDSPlexRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSSpecificDataConverter;
import com.ohrasys.cad.gds.GDSXyRecord;
import com.ohrasys.cad.gds.dao.Node;

/**
 * A Bachus Naur test for the GDSII &lt;node&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.13 $
 * @since    1.5
 */
public class NodeValidator extends BNFRequiredTest {

    /**
     * Creates a new NodeValidator object.
     *
     * @throws  BNFTestException  If an error in object creation occurs
     */
    public NodeValidator() throws BNFTestException {
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
        List<Node> result = new ArrayList<Node>();
        List<Object> retValue = (List<Object>) super.collect();
        retValue = GDSSpecificDataConverter.flattenList(new ArrayList<Object>(), retValue);
        Node node = new Node();
        for (int i = 0; i < retValue.size(); i++) {
            GDSRecord rec = (GDSRecord) ((BNFTestableObject) retValue.get(i))
                    .getData();
            switch (rec.getRectype()) {
                case GDSRecord.ELFLAGS:
                    node.setExternal(((GDSElflagsRecord) rec).isExternal());
                    node.setTemplate(((GDSElflagsRecord) rec).isTemplate());
                    break;

                case GDSRecord.PLEX:
                    node.setGroup(((GDSPlexRecord) rec).getPlex());
                    break;

                case GDSRecord.LAYER:
                    node.setLayer(((GDSLayerRecord) rec).getLayer());
                    break;

                case GDSRecord.NODETYPE:
                    node.setDatatype(((GDSNodetypeRecord) rec).getNodeType());
                    break;

                case GDSRecord.XY:
                    node.setPoints(((GDSXyRecord) rec).getXy());
                    break;

                default:
                    break;
            }
        } // end for
        result.add(node);

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
     * The list of child tests that comprise the NodeValidator
     *
     * @return  A list of tests that comprise the Node element
     *
     * @throws  BNFTestException  If an error in test list creation occurs
     */
    private static BNFTestImplementor[] buildTests() throws BNFTestException {
        return new BNFTestImplementor[] {
                new BNFNoFallthruTest(GDSRecord.NODE),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.ELFLAGS) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.PLEX) }),
                new BNFNoFallthruTest(GDSRecord.LAYER),
                new BNFNoFallthruTest(GDSRecord.NODETYPE),
                new BNFNoFallthruTest(GDSRecord.XY)
        };
    }
} // end class NodeValidator

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
