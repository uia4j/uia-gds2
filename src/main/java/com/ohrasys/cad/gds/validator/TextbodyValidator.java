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

import com.ohrasys.cad.bnf.BNFNoFallthruTest;
import com.ohrasys.cad.bnf.BNFOptionalTest;
import com.ohrasys.cad.bnf.BNFRequiredTest;
import com.ohrasys.cad.bnf.BNFTestException;
import com.ohrasys.cad.bnf.BNFTestImplementor;
import com.ohrasys.cad.gds.GDSRecord;

/**
 * A Bachus Naur test for the GDSII &lt;textbody&gt; element.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.11 $
 * @since    1.5
 */
public class TextbodyValidator extends BNFRequiredTest {

    /**
     * Creates a new TextbodyValidator object.
     *
     * @throws  BNFTestException  If an error occurs during list creation
     */
    public TextbodyValidator() throws BNFTestException {
        super(buildTests());
    }

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
     * The list of child tests that comprise the TextBodyValidator
     *
     * @return  A list of tests that comprise the Textbody element
     *
     * @throws  BNFTestException  If an error occurs during list creation
     */
    private static BNFTestImplementor[] buildTests() throws BNFTestException {
        return new BNFTestImplementor[] {
                new BNFNoFallthruTest(GDSRecord.TEXTTYPE),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.PRESENTATION) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.PATHTYPE) }),
                new BNFOptionalTest(
                        new BNFTestImplementor[] { new BNFNoFallthruTest(GDSRecord.WIDTH) }),
                new BNFOptionalTest(new BNFTestImplementor[] { new StransValidator() }),
                new BNFNoFallthruTest(GDSRecord.XY),
                new BNFNoFallthruTest(GDSRecord.STRING)
        };
    }
} // end class TextbodyValidator

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
