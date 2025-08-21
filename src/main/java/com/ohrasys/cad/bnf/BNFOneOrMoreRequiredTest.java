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
 * A test in which all of the sub-tests must occur one or more times.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.27 $, $Date: 2005/04/01 04:38:31 $
 */
public class BNFOneOrMoreRequiredTest extends BNFAbstractTest {

    /**
     * The buffer used to store data until the current pass can be committed
     */
    private List<Object> dataBuffer;

    /** A flag indicating that all sub-tests have completed at least once */
    private boolean foundOne;

    /**
     * The buffer used to store tokens until the current pass can be
     * committed
     */
    private List<BNFTestableObject> tokenBuffer;

    /**
     * Creates a new BNFOneOrMoreRequiredTest object.
     *
     * @param   tests  The list of sub-tests that comprise this test
     *
     * @throws  BNFTestException  If the sub-test list is null, zero length
     *                            or contains a null sub-test
     */
    public BNFOneOrMoreRequiredTest(BNFTestImplementor tests[]) throws BNFTestException {
        super(tests);
        this.tokenBuffer = new Vector<BNFTestableObject>();
        this.dataBuffer = new Vector<Object>();
    }

    /** Resets the state of the test */
    @Override
    public void reset() {
        super.reset();
        this.foundOne = false;
    }

    /**
     * A method to evaluate this test using the supplied testable object
     *
     * @param   obj  The BNFTestableObject from which to extract the token
     *               used to evaluate this test
     *
     * @return  A BNFTestResult object representing the result of this test.
     *          If more tokens are required to resolve the test, then
     *          UNFINISHED is returned and the results committable tokens
     *          list and replayable tokens list are empty. If the test
     *          completed successfully then FINISHED is returned and the
     *          committable tokens list contains a list of all tokens
     *          successfully processed by this test and the replayable tokens
     *          list contains a list of tokens required to be replayed.
     *          Otherwise FAILED is returned and all tokens processed by this
     *          test are returned in the replayable list.
     */
    @Override
    public BNFTestResult test(BNFTestableObject obj) {
        this.playable.add(0, obj);
        while (this.playable.size() > 0) {
            BNFTestableObject crntToken = this.playable.remove(0);
            BNFTestResult childResult = this.tests[this.nextTest].test(crntToken);
            if (childResult.isUnfinished()) {
                this.result.setResult(BNFTestResult.UNFINISHED);
            }
            else if (childResult.isFinished()) {
                this.tokenBuffer.addAll(childResult.getCommittableTokens());
                this.playable.addAll(0, childResult.getReplayableTokens());
                this.result.setResult(BNFTestResult.UNFINISHED);
                if (this.collecting) {
                    this.dataBuffer.add(this.tests[this.nextTest].collect());
                }
                if (isLastTest()) {
                    this.nextTest = 0;
                    this.foundOne = true;
                    this.committed.addAll(this.tokenBuffer);
                    if (this.collecting) {
                        this.collectedData.addAll(this.dataBuffer);
                    }
                    this.tokenBuffer.clear();
                    this.dataBuffer.clear();
                }
                else {
                    this.nextTest++;
                }

                continue;
            }
            else {
                this.playable.addAll(childResult.getCommittableTokens());
                this.playable.addAll(0, childResult.getReplayableTokens());
                if (this.foundOne) {
                    this.result.addCommittableTokens(this.committed);
                    this.result.addReplayableTokens(this.tokenBuffer);
                    this.result.addReplayableTokens(this.playable);
                    this.result.setResult(BNFTestResult.FINISHED);
                    reset();
                    this.playable.clear();
                    this.committed.clear();
                    this.tokenBuffer.clear();
                }
                else {
                    this.result.addReplayableTokens(this.committed);
                    this.result.addReplayableTokens(this.tokenBuffer);
                    this.result.addReplayableTokens(this.playable);
                    this.result.setResult(BNFTestResult.FAILED);
                    this.collectedData.clear();
                    reset();
                    this.playable.clear();
                    this.committed.clear();
                    this.tokenBuffer.clear();
                }
            }
        } // end while
        BNFTestResult testResult = this.result;
        this.result = new BNFTestResult();

        return testResult;
    } // end method test

    /**
     * Returns a text representation of this test
     *
     * @return  A text representation of this test
     */
    @Override
    public String toString() {
        return super.toString();
    }
} // end class BNFOneOrMoreRequiredTest

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
