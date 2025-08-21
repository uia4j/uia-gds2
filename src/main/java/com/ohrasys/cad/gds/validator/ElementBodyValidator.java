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

import com.ohrasys.cad.bnf.BNFOneOfManyRequiredTest;
import com.ohrasys.cad.bnf.BNFRequiredTest;
import com.ohrasys.cad.bnf.BNFTestException;
import com.ohrasys.cad.bnf.BNFTestImplementor;

/**
 * A Bachus Naur test for a the body of a GDSII &lt;element&gt;
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.12 $
 * @since    1.5
 */
public class ElementBodyValidator extends BNFRequiredTest {

    /**
     * Creates a new ElementBodyValidator object.
     *
     * @throws  BNFTestException  If an error in object creation occurs
     */
    public ElementBodyValidator() throws BNFTestException {
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
     * Creates the list of tests that define the element body element
     *
     * @return  A list of tests that define an element body
     *
     * @throws  BNFTestException  If an error in test list creation occurs
     */
    private static BNFTestImplementor[] buildTests() throws BNFTestException {
        return new BNFTestImplementor[] { new BNFOneOfManyRequiredTest(
                new BNFTestImplementor[] {
                        new BoundaryValidator(),
                        new PathValidator(),
                        new SrefValidator(),
                        new ArefValidator(),
                        new TextValidator(),
                        new NodeValidator(),
                        new BoxValidator()
                }) };
    }
} // end class ElementBodyValidator

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
