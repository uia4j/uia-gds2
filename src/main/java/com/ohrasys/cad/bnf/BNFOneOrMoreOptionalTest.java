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
 * A test in which all of the sub-tests must occur zero or more times
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.25 $, $Date: 2005/04/01 04:38:31 $
 */
public class BNFOneOrMoreOptionalTest
extends BNFAbstractTest {
  /**
   * The buffer used to store data until the current pass can be committed
   */
  private List<Object> dataBuffer;

  /**
   * The buffer used to store tokens until the current pass can be
   * committed
   */
  private List<BNFTestableObject> tokenBuffer;

  /**
   * Creates a new BNFOneOrMoreOptionalTest object.
   *
   * @param   tests  The list of sub-tests that comprise this test
   *
   * @throws  BNFTestException  If the sub-tests list is null, zero length
   *                            or contains a null sub-test
   */
  public BNFOneOrMoreOptionalTest(BNFTestImplementor tests[])
    throws BNFTestException {
    super(tests);
    tokenBuffer = new Vector<BNFTestableObject>();
    dataBuffer  = new Vector<Object>();
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
  public BNFTestResult test(BNFTestableObject obj) {
    playable.add(0, obj);
    while(playable.size() > 0) {
      BNFTestableObject crntToken   = playable.remove(0);
      BNFTestResult     childResult = tests[nextTest].test(crntToken);
      if(childResult.isUnfinished()){result.setResult(result.UNFINISHED);}
      else if(childResult.isFinished()) {
        tokenBuffer.addAll(childResult.getCommittableTokens());
        playable.addAll(0, childResult.getReplayableTokens());
        result.setResult(result.UNFINISHED);
        if(collecting){dataBuffer.add(tests[nextTest].collect());}
        if(isLastTest()) {
          nextTest = 0;
          committed.addAll(tokenBuffer);
          if(collecting){collectedData.addAll(dataBuffer);}
          tokenBuffer.clear();
          dataBuffer.clear();
        } else{nextTest++;}

        continue;
      } else {
        playable.addAll(childResult.getCommittableTokens());
        playable.addAll(0, childResult.getReplayableTokens());
        result.addCommittableTokens(committed);
        result.addReplayableTokens(tokenBuffer);
        result.addReplayableTokens(playable);
        result.setResult(result.FINISHED);
        reset();
        playable.clear();
        committed.clear();
        tokenBuffer.clear();
        /*        if(isFirstTest()) {
         *        result.addCommittableTokens(committed);
         * result.addReplayableTokens(playable);
         * result.setResult(result.FINISHED);       reset();
         * playable.clear();       committed.clear();     } else {
         * result.addReplayableTokens(committed);
         * result.addReplayableTokens(playable);
         * result.setResult(result.FAILED);       collectedData.clear();
         * reset();       playable.clear();       committed.clear();
         * }*/
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
} // end class BNFOneOrMoreOptionalTest


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
