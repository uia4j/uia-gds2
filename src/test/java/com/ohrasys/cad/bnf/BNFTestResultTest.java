/* Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA. */
package com.ohrasys.cad.bnf;
import junit.framework.*;
import java.util.*;

public class BNFTestResultTest
  extends TestCase {
  private int           numTests;
  private Random        random;
  private BNFTestResult result;

  public BNFTestResultTest(String testName) {
    super(testName);
    result = new BNFTestResult();
    int seed = new Random().nextInt();
    System.out.println("Random seed for test is " + seed);
    random   = new Random(seed);
    numTests = 1000;
  }

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite(BNFTestResultTest.class);
    return suite;
  }

  public void testAddCommittableToken() {
    ArrayList<BNFTestableObject> list = new ArrayList<BNFTestableObject>();
    for(int i = 0; i < numTests; i++) {
      int number = random.nextInt();
      list.add(new BNFInteger(number));
      result.addCommittableToken(new BNFInteger(number));
    }
    List<BNFTestableObject> tokens = result.getCommittableTokens();
    assertTrue(tokens.containsAll(list));
  }

  public void testAddCommittableTokens() {
    result.getCommittableTokens();
    assertTrue(result.getCommittableTokens().size() == 0);
    List<BNFTestableObject> list = new ArrayList<BNFTestableObject>(numTests);
    for(int i = 0; i < numTests; i++)
      list.add(new BNFInteger(random.nextInt()));
    result.addCommittableTokens(list);
    List<BNFTestableObject> tester = result.getCommittableTokens();
    assertTrue(tester.containsAll(list));
  }

  public void testAddReplayableToken() {
    ArrayList<BNFTestableObject> list = new ArrayList<BNFTestableObject>();
    for(int i = 0; i < numTests; i++) {
      int number = random.nextInt();
      list.add(new BNFInteger(number));
      result.addReplayableToken(new BNFInteger(number));
    }
    List<BNFTestableObject> tokens = result.getReplayableTokens();
    assertTrue(tokens.containsAll(list));
  }

  public void testAddReplayableTokens() {
    result.getReplayableTokens();
    assertTrue(result.getReplayableTokens().size() == 0);
    List<BNFTestableObject> list = new ArrayList<BNFTestableObject>(numTests);
    for(int i = 0; i < numTests; i++)
      list.add(new BNFInteger(random.nextInt()));
    result.addReplayableTokens(list);
    List<BNFTestableObject> tester = result.getReplayableTokens();
    assertTrue(tester.containsAll(list));
  }

  public void testConstructor() {
    result = new BNFTestResult();
    assertTrue(result.isUnfinished());
  }

  public void testGetCommittableTokens() {
    List<BNFTestableObject> list = new ArrayList<BNFTestableObject>();
    for(int i = 0; i < numTests; i++) {
      int number = random.nextInt();
      list.add(new BNFInteger(number));
    }
    result.committable = list;
    List<BNFTestableObject> tokens = result.getCommittableTokens();
    assertEquals(tokens, list);
  }

  public void testGetReplayableTokens() {
    List<BNFTestableObject> list = new ArrayList<BNFTestableObject>();
    for(int i = 0; i < numTests; i++) {
      int number = random.nextInt();
      list.add(new BNFInteger(number));
    }
    result.replayable = list;
    List<BNFTestableObject> tokens = result.getReplayableTokens();
    assertEquals(tokens, list);
  }

  public void testIsFailed() {
    result.setResult(result.FAILED);
    assertTrue(result.isFailed());
    assertFalse(result.isFinished());
    assertFalse(result.isUnfinished());
  }

  public void testIsFinished() {
    result.setResult(result.FINISHED);
    assertFalse(result.isFailed());
    assertTrue(result.isFinished());
    assertFalse(result.isUnfinished());
  }

  public void testIsUnfinished() {
    result.setResult(result.UNFINISHED);
    assertFalse(result.isFailed());
    assertFalse(result.isFinished());
    assertTrue(result.isUnfinished());
  }

  public void testSetResult() {
    int testResult = result.UNFINISHED;
    while(
      (testResult == result.FINISHED) ||
        (testResult == result.UNFINISHED) ||
        (testResult == result.FAILED)
    )
      testResult = random.nextInt();
    boolean caught = false;
    try {
      result.setResult(testResult);
    } catch(IllegalArgumentException ex) {
      caught = true;
    }
    assertTrue(caught);
  }

  public void testToString() {
    try {
      String expected =
        result.getClass().getName() + '@' +
        Integer.toHexString(result.hashCode());
      String actual   = result.toString();
      assertTrue(expected.equals(actual));
    } catch(Exception ex) {
      fail("testToString() method failed");
    }
  }

  private class BNFInteger
    extends BNFTestableObject {
    public BNFInteger(Integer num) {super(num);}

    public int getToken() {return ((Integer)getData()).intValue();}
  }
}

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
