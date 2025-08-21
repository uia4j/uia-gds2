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
import com.ohrasys.cad.gds.GDSPathtypeRecord;
import com.ohrasys.cad.gds.GDSPlexRecord;
import com.ohrasys.cad.gds.GDSPresentationRecord;
import com.ohrasys.cad.gds.GDSRecord;
import com.ohrasys.cad.gds.GDSSpecificDataConverter;
import com.ohrasys.cad.gds.GDSStringRecord;
import com.ohrasys.cad.gds.GDSTexttypeRecord;
import com.ohrasys.cad.gds.GDSWidthRecord;
import com.ohrasys.cad.gds.GDSXyRecord;
import com.ohrasys.cad.gds.dao.Text;
import com.ohrasys.cad.gds.dao.Transform;

/**
 * A Bachus Naur test for the GDSII &lt;text&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.13 $
 * @since    1.5
 */
public class TextValidator extends BNFRequiredTest {

    /**
     * Creates a new TextValidator object.
     *
     * @throws  BNFTestException  If an error during object creation occurs
     */
    public TextValidator() throws BNFTestException {
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
        List<Text> result = new ArrayList<Text>();
        List<Object> retValue = (List<Object>) super.collect();
        retValue = GDSSpecificDataConverter.flattenList(new ArrayList<Object>(), retValue);
        Text text = new Text();
        for (int i = 0; i < retValue.size(); i++) {
            if (retValue.get(i) instanceof Transform) {
                text.setTransform((Transform) retValue.get(i));
            }
            else {
                GDSRecord rec = (GDSRecord) ((BNFTestableObject) retValue.get(i))
                        .getData();
                switch (rec.getRectype()) {
                    case GDSRecord.ELFLAGS:
                        text.setExternal(((GDSElflagsRecord) rec).isExternal());
                        text.setTemplate(((GDSElflagsRecord) rec).isTemplate());
                        break;

                    case GDSRecord.PLEX:
                        text.setGroup(((GDSPlexRecord) rec).getPlex());
                        break;

                    case GDSRecord.LAYER:
                        text.setLayer(((GDSLayerRecord) rec).getLayer());
                        break;

                    case GDSRecord.TEXTTYPE:
                        text.setDatatype(((GDSTexttypeRecord) rec).getTexttype());
                        break;

                    case GDSRecord.PRESENTATION:
                        text.setFont(((GDSPresentationRecord) rec).getFont());
                        text.setHJustify(((GDSPresentationRecord) rec).getHJustification());
                        text.setVJustify(((GDSPresentationRecord) rec).getVJustification());
                        break;

                    case GDSRecord.PATHTYPE:
                        text.setPathStyle(((GDSPathtypeRecord) rec).getPathtype());
                        break;

                    case GDSRecord.WIDTH:
                        text.setWidth(((GDSWidthRecord) rec).getWidth());
                        break;

                    case GDSRecord.XY:
                        text.setPoints(((GDSXyRecord) rec).getXy());
                        break;

                    case GDSRecord.STRING:
                        text.setValue(((GDSStringRecord) rec).getString());
                        break;

                    default:
                        break;
                }
            } // end if
        } // end for
        result.add(text);

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
     * The list of child tests that comprise the TextValidator
     *
     * @return  A list of tests that comprise the Text element
     *
     * @throws  BNFTestException  If an error occurs during list creation
     */
    private static BNFTestImplementor[] buildTests() throws BNFTestException {
        return new BNFTestImplementor[] {
                new BNFNoFallthruTest(GDSRecord.TEXT),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.ELFLAGS) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.PLEX) }),
                new BNFNoFallthruTest(GDSRecord.LAYER),
                new BNFRequiredTest(new BNFTestImplementor[] { new TextbodyValidator() })
        };
    }
} // end class TextValidator

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
