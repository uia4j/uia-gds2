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

import java.util.List;
import java.util.Vector;

/**
 * Abstract Backus-Naur test.  This is the base for all the concrete test
 * implementations in this package.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.32 $, $Date: 2005/04/01 06:21:52 $
 */
public abstract class BNFAbstractTest implements BNFTestImplementor {

    /** The list containing data collected by this test */
    protected List<Object> collectedData;

    /** A flag indicating whether data collection is turned on */
    protected boolean collecting;

    /** A list of all subtokens successfully processed by the sub-tests */
    protected List<BNFTestableObject> committed;

    /** A pointer to the sub-test being evaluated */
    protected int nextTest;

    /** A list of tokens to be evaluated by this test */
    protected List<BNFTestableObject> playable;

    /** The result object for this test */
    protected BNFTestResult result;

    /** The list of sub-tests that comprise this test */
    protected BNFTestImplementor tests[];

    /**
     * Creates a new BNFAbstractTest object.
     *
     * @param   tests  The list of sub-tests that comprise this test.
     *
     * @throws  BNFTestException  If the list of sub-tests is null, empty or
     *                            contains null sub-tests.
     */
    public BNFAbstractTest(BNFTestImplementor tests[]) throws BNFTestException {
        this.collecting = false;
        this.tests = tests;
        this.result = new BNFTestResult();
        this.playable = new Vector<BNFTestableObject>();
        this.committed = new Vector<BNFTestableObject>();
        this.collectedData = new Vector<Object>();
        checkTests();
        reset();
    }

    /**
     * A method to evaluate this test using the supplied testable object.
     *
     * @param   obj  The testable object from which to extract the token
     *
     * @return  A BNFTestResult object containing a list of tokens that may
     *          be committed by the calling test, a list of tokens that must
     *          be replayed by the calling test and the completion status of
     *          the test.
     */
    @Override
    public abstract BNFTestResult test(BNFTestableObject obj);

    /**
     * A method to retrieve data collected during the evaluation of this test
     *
     * @return  A list containing the data collected by this test during
     *          evaluation
     */
    @Override
    public Object collect() {
        List<Object> result;
        if (this.collecting) {
            result = this.collectedData;
            this.collectedData = new Vector<Object>();
        }
        else {
            result = new Vector<Object>(0);
        }

        return result;
    }

    /** Resets the test state */
    @Override
    public void reset() {
        this.nextTest = 0;
        if (this.tests != null) {
            for (int i = 0; i < this.tests.length; i++) {
                if (this.tests[i] != null) {
                    this.tests[i].reset();
                }
            }
        }
    }

    /**
     * A method to turn on data collection
     *
     * @param  isCollecting  true if data should be collecting during test
     *                       evaluation
     */
    @Override
    public void setCollecting(boolean isCollecting) {
        this.collecting = isCollecting;
        if (this.tests != null) {
            for (int i = 0; i < this.tests.length; i++) {
                this.tests[i].setCollecting(isCollecting);
            }
        }
    }

    /**
     * Returns a text representation of the test
     *
     * @return  A text representation of the test
     */
    @Override
    public String toString() {
        String name = getClass().getName();
        int numtests = this.tests.length;

        return String.format(BNFI18NFactory.getString(BNFI18NFactory.i18n_CHLD_TSTS), name,
                numtests);
    }

    /**
     * A method to check the sub-tests that comprise this test
     *
     * @throws  BNFTestException  If the sub-test list is null, zero length
     *                            or contains a null sub-test
     */
    protected void checkTests() throws BNFTestException {
        if ((this.tests == null) || (this.tests.length == 0)) {
            throw new BNFTestException(
                    BNFI18NFactory.getString(BNFI18NFactory.i18n_TST_REQD));
        }
        for (int i = 0; i < this.tests.length; i++) {
            if (this.tests[i] == null) {
                throw new BNFTestException(
                        BNFI18NFactory.getString(BNFI18NFactory.i18n_TST_REQD));
            }
        }
    }

    /**
     * A method to determine if the test is evaluating the first sub-test
     *
     * @return  true if the first sub-test is being evaluated
     */
    protected boolean isFirstTest() {
        if ((this.tests == null) || (this.tests.length == 0)) {
            return true;
        }
        else {
            return this.nextTest == 0;
        }
    }

    /**
     * A method to determine if the test is evaluating the last sub-test
     *
     * @return  true if the last sub-test is being evaluated
     */
    protected boolean isLastTest() {
        if ((this.tests == null) || (this.tests.length == 0)) {
            return true;
        }
        else {
            return this.nextTest == (this.tests.length - 1);
        }
    }

    /**
     * A method to determine if the test is evaluating a sub-test other than
     * the last sub-test
     *
     * @return  true if the last sub-test is not being evaluated
     */
    protected boolean isNotLastTest() {
        return !isLastTest();
    }
} // end class BNFAbstractTest

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
