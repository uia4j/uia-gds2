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

public class BNFOneOfManyRequiredTestTest
  extends TestCase {
  public BNFOneOfManyRequiredTestTest(String testName) {super(testName);}

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite(BNFOneOfManyRequiredTestTest.class);
    return suite;
  }

  public void testCompositeDirectedFailingCase()
    throws BNFTestException {
    BNFTestImplementor       leafTests[] = {
        new BNFNoFallthruTest(1), new BNFNoFallthruTest(2),
        new BNFNoFallthruTest(3),
      };
    BNFTestImplementor       subtests[]  = {
        new BNFNoFallthruTest(0), new BNFOneOfManyRequiredTest(leafTests)
      };
    BNFOneOfManyRequiredTest test        =
      new BNFOneOfManyRequiredTest(subtests);
    BNFInteger               obj;
    BNFTestResult            result;
    List<BNFTestableObject>  replayable;
    List<BNFTestableObject>  committable;
    obj                                  = new BNFInteger(4);
    result                               = test.test(obj);
    assertTrue(result.isFailed());
    committable = result.getCommittableTokens();
    replayable  = result.getReplayableTokens();
    assertTrue(committable.size() == 0);
    assertTrue(replayable.size() == 1);
    assertTrue(replayable.get(0).equals(obj));
  }

  public void testCompositeDirectedPassingCase()
    throws BNFTestException {
    BNFTestImplementor       leafTests[] = {
        new BNFNoFallthruTest(1), new BNFNoFallthruTest(2),
        new BNFNoFallthruTest(3),
      };
    BNFTestImplementor       subtests[]  = {
        new BNFNoFallthruTest(0), new BNFOneOfManyRequiredTest(leafTests)
      };
    BNFOneOfManyRequiredTest test        =
      new BNFOneOfManyRequiredTest(subtests);
    test.setCollecting(true);
    BNFInteger              obj;
    BNFTestResult           result;
    List<BNFTestableObject> list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    for(int i = 0; i < 4; i++) {
      obj                               = new BNFInteger(i);
      result                            = test.test(obj);
      assertTrue(result.isFinished());
      committable = result.getCommittableTokens();
      replayable  = result.getReplayableTokens();
      assertTrue(committable.size() == 1);
      assertTrue(replayable.size() == 0);
      assertTrue(committable.contains(obj));
      assertTrue(committable.get(0).equals(obj));
      if(i == 0) {
        List<Object> expected = new Vector<Object>();
        List<Object> sub0     = new Vector<Object>();
        sub0.add(obj);
        expected.add(sub0);
        assertEquals(expected, test.collect());
      } else {
        List<Object> expected = new Vector<Object>();
        List<Object> sub0     = new Vector<Object>();
        List<Object> sub1     = new Vector<Object>();
        sub1.add(new BNFInteger(i));
        sub0.add(sub1);
        expected.add(sub0);
        assertEquals(expected, test.collect());
      }
    }
  }

  public void testSimpleDirectedFailingCase()
    throws BNFTestException {
    BNFTestImplementor       subtests[] = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1),
        new BNFNoFallthruTest(2),
      };
    BNFOneOfManyRequiredTest test       =
      new BNFOneOfManyRequiredTest(subtests);
    test.setCollecting(true);
    BNFInteger              obj;
    BNFTestResult           result;
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    obj         = new BNFInteger(3);
    result      = test.test(obj);
    assertTrue(result.isFailed());
    committable = result.getCommittableTokens();
    replayable  = result.getReplayableTokens();
    assertTrue(committable.size() == 0);
    assertTrue(replayable.size() == 1);
    assertTrue(replayable.contains(obj));
    assertTrue(replayable.get(0).equals(obj));
    List collectedData = (List)test.collect();
    assertTrue(collectedData.size() == 0);
  }

  public void testSimpleDirectedPassingCase()
    throws BNFTestException {
    BNFTestImplementor       subtests[] = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1),
        new BNFNoFallthruTest(2),
      };
    BNFOneOfManyRequiredTest test       =
      new BNFOneOfManyRequiredTest(subtests);
    test.setCollecting(true);
    BNFInteger              obj;
    BNFTestResult           result;
    List<BNFTestableObject> list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    for(int i = 0; i < 3; i++) {
      obj                               = new BNFInteger(i);
      result                            = test.test(obj);
      assertTrue(result.isFinished());
      committable = result.getCommittableTokens();
      replayable  = result.getReplayableTokens();
      assertTrue(committable.size() == 1);
      assertTrue(replayable.size() == 0);
      assertTrue(committable.contains(obj));
      assertTrue(committable.get(0).equals(obj));
      Object collectedData[] = ((List)(((List)test.collect()).get(0))).toArray();
      assertTrue(collectedData.length == 1);
      assertTrue(collectedData[0].equals(obj));
    }
  }

  public void testSimpleDirectedUnfinishedCase()
    throws BNFTestException {
    BNFTestImplementor       subtests[]  = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1),
      };
    BNFRequiredTest          required    = new BNFRequiredTest(subtests);
    BNFTestImplementor       tests[]     = {
        required, new BNFNoFallthruTest(2)
      };
    BNFOneOfManyRequiredTest test        = new BNFOneOfManyRequiredTest(tests);
    BNFInteger               obj;
    BNFTestResult            result;
    List<BNFTestableObject>  replayable;
    List<BNFTestableObject>  committable;
    obj                                  = new BNFInteger(0);
    result                               = test.test(obj);
    assertTrue(result.isUnfinished());
    obj         = new BNFInteger(1);
    result      = test.test(obj);
    assertTrue(result.isFinished());
    committable = result.getCommittableTokens();
    replayable  = result.getReplayableTokens();
    assertTrue(committable.size() == 2);
    assertTrue(replayable.size() == 0);
    obj         = new BNFInteger(2);
    result      = test.test(obj);
    assertTrue(result.isFinished());
    committable = result.getCommittableTokens();
    replayable  = result.getReplayableTokens();
    assertTrue(committable.size() == 1);
    assertTrue(replayable.size() == 0);
  }

  public void testToString()
    throws BNFTestException {
    BNFOneOfManyRequiredTest test     =
      new BNFOneOfManyRequiredTest(
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
