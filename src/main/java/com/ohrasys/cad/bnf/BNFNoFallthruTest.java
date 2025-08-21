/*
 * Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package com.ohrasys.cad.bnf;

/**
 * Represents a terminal test
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.20 $, $Date: 2005/03/31 22:13:16 $
 */
public class BNFNoFallthruTest extends BNFAbstractTest {

    /** The requirement that must be matched in order for the test to pass */
    protected int requirement;

    /**
     * Creates a new BNFNoFallthruTest object.
     *
     * @param   requirement  The requirement for the test.
     *
     * @throws  BNFTestException  If an error in test creation occurs
     */
    public BNFNoFallthruTest(int requirement) throws BNFTestException {
        super(null);
        this.requirement = requirement;
    }

    /**
     * A method to evaluate this test.
     *
     * @param   obj  The testable object from which to extract the token used
     *               to evaluate the test.
     *
     * @return  A BNFTestResult object representing the result of this test
     *          evaluation. If the test passes, the status of the result is
     *          FINISHED and the BNFTestableObject used to evaluate the test
     *          is added to the committable list of the result. Otherwise the
     *          status of the result is FAILED and the BNFTestableObject used
     *          to evaluate the test is added to the replayable list of the
     *          result.
     */
    @Override
    public BNFTestResult test(BNFTestableObject obj) {
        if (obj.getToken() == this.requirement) {
            this.result.setResult(BNFTestResult.FINISHED);
            this.result.addCommittableToken(obj);
            if (this.collecting) {
                this.collectedData.add(obj);
            }
        }
        else {
            this.result.setResult(BNFTestResult.FAILED);
            this.result.addReplayableToken(obj);
        }
        BNFTestResult testResult = this.result;
        this.result = new BNFTestResult();

        return testResult;
    }

    /**
     * Returns a text representation of this test
     *
     * @return  A text representation of this test
     */
    @Override
    public String toString() {
        String name = getClass().getName();
        int req = this.requirement;

        return String.format(BNFI18NFactory.getString(BNFI18NFactory.i18n_RQMT), name, req);
    }

    /**
     * A method to ensure that the sub-test list is null.  This is required
     * since this is a terminal test.
     *
     * @throws  BNFTestException  If the sub-test list of this test is not
     *                            null
     */
    @Override
    protected void checkTests() throws BNFTestException {
        if (this.tests != null) {
            throw new BNFTestException();
        }
    }
} // end class BNFNoFallthruTest

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
