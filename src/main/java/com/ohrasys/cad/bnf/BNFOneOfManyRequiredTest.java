/* Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA. */

package com.ohrasys.cad.bnf;
import java.util.*;

/**
 * A test in which one of the sub-tests must pass.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.22 $, $Date: 2005/03/31 22:13:16 $
 */
public class BNFOneOfManyRequiredTest
extends BNFAbstractTest {
  /**
   * Creates a new BNFOneOfManyRequiredTest object.
   *
   * @param   tests  The list of sub-tests that comprise this test
   *
   * @throws  BNFTestException  If the list of sub-tests is null or zero
   *                            length or contains a null sub-test
   */
  public BNFOneOfManyRequiredTest(BNFTestImplementor tests[])
    throws BNFTestException{super(tests);}

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
  public BNFTestResult test(BNFTestableObject obj) {
    playable.add(0, obj);
    while(playable.size() > 0) {
      BNFTestableObject crntToken   = playable.remove(0);
      BNFTestResult     childResult = tests[nextTest].test(crntToken);
      if(childResult.isUnfinished()){result.setResult(result.UNFINISHED);}
      else if(childResult.isFinished()) {
        committed.addAll(childResult.getCommittableTokens());
        playable.addAll(0, childResult.getReplayableTokens());
        result.addCommittableTokens(committed);
        result.addReplayableTokens(playable);
        result.setResult(result.FINISHED);
        if(collecting){collectedData.add(tests[nextTest].collect());}
        reset();
        playable.clear();
        committed.clear();
      } else {
        committed.addAll(childResult.getCommittableTokens());
        playable.addAll(0, childResult.getReplayableTokens());
        if(isLastTest()) {
          result.addReplayableTokens(committed);
          result.addReplayableTokens(playable);
          result.setResult(result.FAILED);
          collectedData.clear();
          reset();
          playable.clear();
          committed.clear();
        } else {
          result.setResult(result.UNFINISHED);
          nextTest++;

          continue;
        }
      }
    } // end while
    BNFTestResult testResult = result;
    result = new BNFTestResult();

    return testResult;
  } // end method test

  /**
   * Returns a text representation of this test
   *
   * @return  A text representation of this test
   */
  public String toString(){return super.toString();}
} // end class BNFOneOfManyRequiredTest


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
