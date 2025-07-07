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
 * A class used to represent the completion status of a test.  This object
 * contains a list of tokens successfully evaluated by the test, a list of
 * tokens that must be replayed and the completion status of the test which
 * must be one of FINISHED, UNFINISHED, or FAILED.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.5 $, $Date: 2005/03/31 22:13:17 $
 */
public class BNFTestResult {
  /** Indicates the test completed successfully */
  public static final int FINISHED = 0x1;

  /** Indicates the test requires more tokens to resolve */
  public static final int UNFINISHED = 0x2;

  /** Indicates the test failed */
  public static final int FAILED = 0x4;

  /** A list of tokens that have been successfully evaluated */
  protected List<BNFTestableObject> committable;

  /** A list of tokens that must be replayed */
  protected List<BNFTestableObject> replayable;

  /** The completion status of the test */
  protected int status;

  /**
   * Creates a new BNFTestResult object.
   */
  public BNFTestResult() {
    committable = new Stack<BNFTestableObject>();
    replayable  = new Stack<BNFTestableObject>();
    setResult(UNFINISHED);
  }

  /**
   * Adds a token to the list of committable tokens
   *
   * @param   token  The token to add
   *
   * @return  true if the token was successfully added
   */
  public boolean addCommittableToken(BNFTestableObject token) {
    return committable.add(token);
  }

  /**
   * Adds a list of tokens to the list of committable tokens
   *
   * @param   list  The list of tokens to add
   *
   * @return  true if the tokens were successfully added
   */
  public boolean addCommittableTokens(List<BNFTestableObject> list) {
    return committable.addAll(list);
  }

  /**
   * Adds a token to the list of replayable tokens
   *
   * @param   token  The token to add
   *
   * @return  true if the token was successfully added
   */
  public boolean addReplayableToken(BNFTestableObject token) {
    return replayable.add(token);
  }

  /**
   * Adds a list of tokens to the list of replayable tokens
   *
   * @param   list  The list of tokens to add
   *
   * @return  true if the tokens were successfully added
   */
  public boolean addReplayableTokens(List<BNFTestableObject> list) {
    return replayable.addAll(list);
  }

  /**
   * Destructively retrieves the list of committable tokens.  After
   * invoking this method the list of committable tokens is empty
   *
   * @return  The list of committable tokens
   */
  public List<BNFTestableObject> getCommittableTokens() {
    List<BNFTestableObject> result = committable;
    committable = new Vector<BNFTestableObject>();

    return result;
  }

  /**
   * Destructively retrieves the list of replayable tokens.  After invoking
   * this method the list of replayable tokens is empty
   *
   * @return  The list of replayable tokens
   */
  public List<BNFTestableObject> getReplayableTokens() {
    List<BNFTestableObject> result = replayable;
    replayable = new Vector<BNFTestableObject>();

    return result;
  }

  /**
   * Indicates the test failed
   *
   * @return  true if the test failed
   */
  public boolean isFailed() {
    return ((status != FINISHED) && (status != UNFINISHED));
  }

  /**
   * Indicates that the test finished
   *
   * @return  true if the test finished
   */
  public boolean isFinished(){return status == FINISHED;}

  /**
   * Indicatest that the test is unfinished
   *
   * @return  true if the test is unfinished
   */
  public boolean isUnfinished(){return status == UNFINISHED;}

  /**
   * Sets the completion status of this result object
   *
   * @param   result  The completion status of the result object
   *
   * @throws  IllegalArgumentException  If the result is not one of
   *                                    FINISHED, UNFINISHED or FAILED
   */
  public void setResult(int result) {
    if((result != FINISHED) && (result != FAILED) &&
        (result != UNFINISHED)){throw new IllegalArgumentException();}
    else{status = result;}
  }

  /**
   * Returns a text representation of this object
   *
   * @return  A text representation of this object
   */
  public String toString(){return super.toString();}
} // end class BNFTestResult


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html
 */
