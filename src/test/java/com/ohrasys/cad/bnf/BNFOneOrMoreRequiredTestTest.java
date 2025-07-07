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

public class BNFOneOrMoreRequiredTestTest
  extends TestCase {
  public BNFOneOrMoreRequiredTestTest(String testName) {super(testName);}

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite(BNFOneOrMoreRequiredTestTest.class);
    return suite;
  }

  public void testCompositeDirectedFailingCase()
    throws BNFTestException {
    BNFTestImplementor       leafTests[] = {
        new BNFNoFallthruTest(1), new BNFNoFallthruTest(2),
        new BNFNoFallthruTest(4),
      };
    BNFTestImplementor       subtests[]  = {
        new BNFNoFallthruTest(0), new BNFOneOrMoreRequiredTest(leafTests)
      };
    BNFOneOrMoreRequiredTest test        =
      new BNFOneOrMoreRequiredTest(subtests);
    BNFInteger               obj;
    BNFTestResult            result;
    List<BNFTestableObject>  list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject>  replayable;
    List<BNFTestableObject>  committable;
    for(int i = 0; i < 4; i++) {
      obj                                = new BNFInteger(i);
      list.add(obj);
      result = test.test(obj);
      if(i == 3) {
        assertTrue(result.isFailed());
        committable = result.getCommittableTokens();
        replayable  = result.getReplayableTokens();
        assertTrue(committable.size() == 0);
        assertTrue(replayable.size() == 4);
        assertTrue(replayable.containsAll(list));
        assertTrue(replayable.get(0).equals(list.get(0)));
        assertTrue(replayable.get(1).equals(list.get(1)));
        assertTrue(replayable.get(2).equals(list.get(2)));
        assertTrue(replayable.get(3).equals(list.get(3)));
      } else {
        assertTrue(result.isUnfinished());
        committable = result.getCommittableTokens();
        replayable  = result.getReplayableTokens();
        assertTrue(committable.size() == 0);
        assertTrue(replayable.size() == 0);
        assertFalse(committable.containsAll(list));
      }
    }
  }

  public void testCompositeDirectedPassingCase()
    throws BNFTestException {
    BNFTestImplementor       leafTests[] = {
        new BNFNoFallthruTest(1), new BNFNoFallthruTest(2),
        new BNFNoFallthruTest(3),
      };
    BNFTestImplementor       subtests[]  = {
        new BNFNoFallthruTest(0), new BNFOneOrMoreRequiredTest(leafTests)
      };
    BNFOneOrMoreRequiredTest test        =
      new BNFOneOrMoreRequiredTest(subtests);
    BNFInteger               obj         = null;
    BNFTestResult            result      = null;
    List<BNFTestableObject>  list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject>  replayable;
    List<BNFTestableObject>  committable;
    for(int i = 0; i < 4; i++) {
      obj                                = new BNFInteger(i);
      list.add(obj);
      result = test.test(obj);
      assertTrue(result.isUnfinished());
      committable = result.getCommittableTokens();
      replayable  = result.getReplayableTokens();
      assertTrue(committable.size() == 0);
      assertTrue(replayable.size() == 0);
      assertFalse(committable.containsAll(list));
    }
    list.add(obj);
    result = test.test(obj);
    assertTrue(result.isFinished());
    committable = result.getCommittableTokens();
    replayable  = result.getReplayableTokens();
    assertTrue(committable.size() == 4);
    assertTrue(replayable.size() == 1);
    assertTrue(committable.containsAll(list.subList(0, 3)));
    List sublist = list.subList(0, 3);
    assertTrue(committable.get(0).equals(list.get(0)));
    assertTrue(committable.get(1).equals(list.get(1)));
    assertTrue(committable.get(2).equals(list.get(2)));
    assertTrue(committable.get(3).equals(list.get(3)));
    assertTrue(replayable.contains(obj));
    assertTrue(replayable.get(0).equals(obj));
  }

  public void testSimpleDirectedFailingCase()
    throws BNFTestException {
    BNFTestImplementor       subtests[] = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(2),
        new BNFNoFallthruTest(3),
      };
    BNFOneOrMoreRequiredTest test       =
      new BNFOneOrMoreRequiredTest(subtests);
    test.setCollecting(true);
    BNFInteger              obj;
    BNFTestResult           result;
    List<BNFTestableObject> list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    for(int i = 0; i < 2; i++) {
      obj                               = new BNFInteger(i);
      list.add(obj);
      result = test.test(obj);
      if(i == 1) {
        assertTrue(result.isFailed());
        committable = result.getCommittableTokens();
        replayable  = result.getReplayableTokens();
        assertTrue(committable.size() == 0);
        assertTrue(replayable.size() == 2);
        assertFalse(committable.containsAll(list));
        assertTrue(replayable.containsAll(list));
        assertTrue(replayable.get(0).equals(list.get(0)));
        assertTrue(replayable.get(1).equals(list.get(1)));
        assertTrue(((List)test.collect()).size() == 0);
      } else {
        assertTrue(result.isUnfinished());
        committable = result.getCommittableTokens();
        replayable  = result.getReplayableTokens();
        assertTrue(committable.size() == 0);
        assertTrue(replayable.size() == 0);
        assertFalse(committable.containsAll(list));
      }
    }
  }

  public void testSimpleDirectedFailingCaseWithNoPasses()
    throws BNFTestException {
    BNFTestImplementor       subtests[] = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1),
        new BNFNoFallthruTest(2),
      };
    BNFOneOrMoreRequiredTest test       =
      new BNFOneOrMoreRequiredTest(subtests);
    test.setCollecting(true);
    BNFInteger              obj;
    BNFTestResult           result;
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    obj         = new BNFInteger(1);
    result      = test.test(obj);
    assertTrue(result.isFailed());
    committable = result.getCommittableTokens();
    replayable  = result.getReplayableTokens();
    assertTrue(committable.size() == 0);
    assertTrue(replayable.size() == 1);
    assertFalse(committable.contains(obj));
    assertTrue(replayable.contains(obj));
    assertTrue(replayable.get(0).equals(obj));
    assertTrue(((List)test.collect()).size() == 0);
  }

  public void testSimpleDirectedPassingCaseWithPartialPass()
    throws BNFTestException {
    BNFTestImplementor       subtests[] = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1),
        new BNFNoFallthruTest(2),
      };
    BNFOneOrMoreRequiredTest test       =
      new BNFOneOrMoreRequiredTest(subtests);
    test.setCollecting(true);
    BNFInteger              obj         = null;
    BNFTestResult           result      = null;
    List<BNFTestableObject> list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    List<Object>            expected    = new Vector<Object>();
    for(int i = 0; i < 5; i++) {
      obj = new BNFInteger(i % 3);
      list.add(obj);
      result = test.test(obj);
      assertTrue(result.isUnfinished());
      committable = result.getCommittableTokens();
      replayable  = result.getReplayableTokens();
      assertTrue(committable.size() == 0);
      assertTrue(replayable.size() == 0);
      assertFalse(committable.containsAll(list));
      if(i < 3) {
        List<Object> sub = new Vector<Object>();
        sub.add(obj);
        expected.add(sub);
      }
    }
    list.add(obj);
    result = test.test(obj);
    assertTrue(result.isFinished());
    committable  = result.getCommittableTokens();
    replayable   = result.getReplayableTokens();
    assertTrue(committable.size() == 3);
    assertTrue(replayable.size() == 3);
    assertTrue(committable.containsAll(list.subList(0, 2)));
    assertTrue(committable.get(0).equals(list.get(0)));
    assertTrue(committable.get(1).equals(list.get(1)));
    assertTrue(committable.get(2).equals(list.get(2)));
    assertTrue(replayable.containsAll(list.subList(3, 5)));
    assertTrue(replayable.get(0).equals(list.get(3)));
    assertTrue(replayable.get(1).equals(list.get(4)));
    assertTrue(replayable.get(2).equals(list.get(5)));
    assertEquals(expected, test.collect());
  }

  public void testSimpleDirectedPassingCaseWithTwoPasses()
    throws BNFTestException {
    BNFTestImplementor       subtests[] = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1),
        new BNFNoFallthruTest(2),
      };
    BNFOneOrMoreRequiredTest test       =
      new BNFOneOrMoreRequiredTest(subtests);
    test.setCollecting(true);
    BNFInteger              obj         = null;
    BNFTestResult           result      = null;
    List<BNFTestableObject> list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    List<Object>            expected    = new Vector<Object>();
    for(int i = 0; i < 6; i++) {
      obj = new BNFInteger(i % 3);
      list.add(obj);
      result = test.test(obj);
      assertTrue(result.isUnfinished());
      committable = result.getCommittableTokens();
      replayable  = result.getReplayableTokens();
      assertTrue(committable.size() == 0);
      assertTrue(replayable.size() == 0);
      assertFalse(committable.containsAll(list));
      List<Object> sub = new Vector<Object>();
      sub.add(obj);
      expected.add(sub);
    }
    result = test.test(obj);
    assertTrue(result.isFinished());
    committable  = result.getCommittableTokens();
    replayable   = result.getReplayableTokens();
    assertTrue(committable.size() == 6);
    assertTrue(replayable.size() == 1);
    assertTrue(committable.containsAll(list));
    assertTrue(committable.equals(list));
    assertTrue(replayable.contains(obj));
    assertTrue(replayable.get(0).equals(obj));
    assertEquals(expected, test.collect());
  }

  public void testToString()
    throws BNFTestException {
    BNFOneOrMoreRequiredTest test     =
      new BNFOneOrMoreRequiredTest(
        new BNFTestImplementor[] {new BNFNoFallthruTest(0)}
        );
    String                   expected =
      String.format(
        BNFI18NFactory.getString(BNFI18NFactory.i18n_CHLD_TSTS),
        test.getClass().getName(), 1
        );
    assertEquals(expected, test.toString());
  }

  private class BNFInteger
    extends BNFTestableObject {
    public BNFInteger(Integer num) {super(num);}

    public int getToken() {return ((Integer)getData()).intValue();}
  }
}

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
