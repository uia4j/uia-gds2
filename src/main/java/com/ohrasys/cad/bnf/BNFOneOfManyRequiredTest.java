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
 * A test in which one of the sub-tests must pass.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.22 $, $Date: 2005/03/31 22:13:16 $
 */
public class BNFOneOfManyRequiredTest extends BNFAbstractTest {

    /**
     * Creates a new BNFOneOfManyRequiredTest object.
     *
     * @param   tests  The list of sub-tests that comprise this test
     *
     * @throws  BNFTestException  If the list of sub-tests is null or zero
     *                            length or contains a null sub-test
     */
    public BNFOneOfManyRequiredTest(BNFTestImplementor tests[]) throws BNFTestException {
        super(tests);
    }

    /**
     * A method to evaluate this test using the supplied testable object
     *
     * @param   obj  The BNFTestableObject from which to extract the token
     *               used to test the object
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
                this.committed.addAll(childResult.getCommittableTokens());
                this.playable.addAll(0, childResult.getReplayableTokens());
                this.result.addCommittableTokens(this.committed);
                this.result.addReplayableTokens(this.playable);
                this.result.setResult(BNFTestResult.FINISHED);
                if (this.collecting) {
                    this.collectedData.add(this.tests[this.nextTest].collect());
                }
                reset();
                this.playable.clear();
                this.committed.clear();
            }
            else {
                this.committed.addAll(childResult.getCommittableTokens());
                this.playable.addAll(0, childResult.getReplayableTokens());
                if (isLastTest()) {
                    this.result.addReplayableTokens(this.committed);
                    this.result.addReplayableTokens(this.playable);
                    this.result.setResult(BNFTestResult.FAILED);
                    this.collectedData.clear();
                    reset();
                    this.playable.clear();
                    this.committed.clear();
                }
                else {
                    this.result.setResult(BNFTestResult.UNFINISHED);
                    this.nextTest++;

                    continue;
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
} // end class BNFOneOfManyRequiredTest

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
