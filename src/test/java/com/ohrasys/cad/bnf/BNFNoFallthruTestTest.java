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
import junit.framework.TestCase;
import java.util.*;

public class BNFNoFallthruTestTest
  extends TestCase {
  private static BNFI18NFactory i18n;
  private BNFTestableObject     object;
  private BNFNoFallthruTest     test;

  public BNFNoFallthruTestTest(String testName) {super(testName);}

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite(BNFNoFallthruTestTest.class);
    return suite;
  }

  public void testCheckTests() {
    boolean caught = false;
    try {
      test.tests = new BNFTestImplementor[] {new BNFNoFallthruTest(0)};
      test.checkTests();
    } catch(BNFTestException ex) {
      caught = true;
    }
    assertTrue(caught);
  }

  public void testDataCollectionForFailingTest() {
    test.requirement = 1;
    test.setCollecting(true);
    object.setData(new Integer(0));
    BNFTestResult result = test.test(object);
    assertTrue(result.isFailed());
    List collectedData = (List)test.collect();
    assertTrue(collectedData.size() == 0);
  }

  public void testDataCollectionForPassingTest() {
    test.requirement = 0;
    test.setCollecting(true);
    object.setData(new Integer(0));
    BNFTestResult result = test.test(object);
    assertTrue(result.isFinished());
    List collectedData = (List)test.collect();
    assertTrue(collectedData.size() == 1);
    assertTrue(collectedData.get(0).equals(object));
  }

  public void testFailingTest() {
    test.requirement = 0;
    object.setData(new Integer(1));
    BNFTestResult result = test.test(object);
    assertFalse(result.isFinished());
    assertFalse(result.isUnfinished());
    assertTrue(result.isFailed());
    List committable = result.getCommittableTokens();
    List replayable  = result.getReplayableTokens();
    assertTrue(replayable.size() == 1);
    assertTrue(committable.size() == 0);
    assertFalse(
      ((BNFTestableObject)replayable.get(0)).getData().equals(new Integer(0))
      );
    assertTrue(result.getReplayableTokens().size() == 0);
  }

  public void testIsFirstTest()
    throws BNFTestException {
    BNFNoFallthruTest test = new BNFNoFallthruTest(0);
    assertTrue(test.isFirstTest());
    BNFTestImplementor tests[] = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1),
        new BNFNoFallthruTest(2)
      };
    test.tests    = tests;
    test.nextTest = 1;
    assertFalse(test.isFirstTest());
  }

  public void testIsLastTest()
    throws BNFTestException {
    BNFNoFallthruTest test = new BNFNoFallthruTest(0);
    assertTrue(test.isLastTest());
  }

  public void testIsNotLastTest()
    throws BNFTestException {
    BNFNoFallthruTest test = new BNFNoFallthruTest(0);
    assertFalse(test.isNotLastTest());
  }

  public void testPassingTest() {
    test.requirement = 0;
    object.setData(new Integer(0));
    BNFTestResult result = test.test(object);
    assertTrue(result.isFinished());
    assertFalse(result.isUnfinished());
    assertFalse(result.isFailed());
    List committable = result.getCommittableTokens();
    List replayable  = result.getReplayableTokens();
    assertTrue(committable.size() == 1);
    assertTrue(replayable.size() == 0);
    assertTrue(
      ((BNFTestableObject)committable.get(0)).getData().equals(new Integer(0))
      );
    assertTrue(result.getCommittableTokens().size() == 0);
  }

  public void testReset() {
    test.nextTest = 9;
    test.reset();
    assertEquals(test.nextTest, 0);
  }

  public void testToString() {
    test.requirement = 0;
    String className = test.getClass().getName();
    String expected;
    expected         = String.format(
        i18n.getString(i18n.i18n_RQMT), className, 0
        );
    assertEquals(expected, test.toString());
    expected = String.format(i18n.getString(i18n.i18n_RQMT), className, 1);
    assertFalse(expected.equals(test.toString()));
    className   = this.getClass().getName();
    expected    = String.format(i18n.getString(i18n.i18n_RQMT), className, 0);
    assertFalse(expected.equals(test.toString()));
  }

  protected void setUp()
    throws java.lang.Exception {
    test   = new BNFNoFallthruTest(0);
    object = new BNFTestableObject((Integer)null) {
        public int getToken() {return ((Integer)getData()).intValue();}
      };
  }
}

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
