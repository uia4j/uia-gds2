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

public class BNFOptionalTestTest
  extends TestCase {
  public BNFOptionalTestTest(String testName) {super(testName);}

  public static junit.framework.Test suite() {
    junit.framework.TestSuite suite =
      new junit.framework.TestSuite(BNFOptionalTestTest.class);
    return suite;
  }

  public void testCompositeDirectedFailingCase()
    throws BNFTestException {
    BNFTestImplementor      leafTests[] = {
        new BNFNoFallthruTest(1), new BNFNoFallthruTest(2),
        new BNFNoFallthruTest(4),
      };
    BNFTestImplementor      subtests[]  = {
        new BNFNoFallthruTest(0), new BNFOptionalTest(leafTests)
      };
    BNFOptionalTest         test        = new BNFOptionalTest(subtests);
    BNFInteger              obj;
    BNFTestResult           result;
    List<BNFTestableObject> list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    for(int i = 0; i < 4; i++) {
      obj                               = new BNFInteger(i);
      list.add(obj);
      result = test.test(obj);
      if(i == 3) {
        assertTrue(result.isFinished());
        committable = result.getCommittableTokens();
        replayable  = result.getReplayableTokens();
        assertTrue(committable.size() == 1);
        assertTrue(replayable.size() == 3);
        assertTrue(committable.contains(list.get(0)));
        assertTrue(replayable.containsAll(list.subList(1, 3)));
        assertTrue(replayable.get(0).equals(list.get(1)));
        assertTrue(replayable.get(1).equals(list.get(2)));
        assertTrue(replayable.get(2).equals(list.get(3)));
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
    BNFTestImplementor      leafTests[] = {
        new BNFNoFallthruTest(1), new BNFNoFallthruTest(2),
        new BNFNoFallthruTest(3),
      };
    BNFTestImplementor      subtests[]  = {
        new BNFNoFallthruTest(0), new BNFOptionalTest(leafTests)
      };
    BNFOptionalTest         test        = new BNFOptionalTest(subtests);
    BNFInteger              obj;
    BNFTestResult           result;
    List<BNFTestableObject> list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    for(int i = 0; i < 4; i++) {
      obj                               = new BNFInteger(i);
      list.add(obj);
      result = test.test(obj);
      if(i == 3) {
        assertTrue(result.isFinished());
        committable = result.getCommittableTokens();
        replayable  = result.getReplayableTokens();
        assertTrue(committable.size() == 4);
        assertTrue(replayable.size() == 0);
        assertTrue(committable.containsAll(list));
        assertTrue(committable.get(0).equals(list.get(0)));
        assertTrue(committable.get(1).equals(list.get(1)));
        assertTrue(committable.get(2).equals(list.get(2)));
        assertTrue(committable.get(3).equals(list.get(3)));
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

  public void testSimpleDirectedFailingCase()
    throws BNFTestException {
    BNFTestImplementor      subtests[]  = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(2),
        new BNFNoFallthruTest(3),
      };
    BNFOptionalTest         test        = new BNFOptionalTest(subtests);
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
        assertTrue(result.isFinished());
        committable = result.getCommittableTokens();
        replayable  = result.getReplayableTokens();
        assertTrue(committable.size() == 0);
        assertTrue(replayable.size() == 2);
        assertFalse(committable.containsAll(list));
        assertTrue(replayable.containsAll(list));
        assertTrue(replayable.get(0).equals(list.get(0)));
        assertTrue(replayable.get(1).equals(list.get(1)));
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

  public void testSimpleDirectedPassingCase()
    throws BNFTestException {
    BNFTestImplementor      subtests[]  = {
        new BNFNoFallthruTest(0), new BNFNoFallthruTest(1),
        new BNFNoFallthruTest(2),
      };
    BNFOptionalTest         test        = new BNFOptionalTest(subtests);
    BNFInteger              obj;
    BNFTestResult           result;
    List<BNFTestableObject> list        = new Vector<BNFTestableObject>();
    List<BNFTestableObject> replayable;
    List<BNFTestableObject> committable;
    for(int i = 0; i < 3; i++) {
      obj                               = new BNFInteger(i);
      list.add(obj);
      result = test.test(obj);
      if(i == 2) {
        assertTrue(result.isFinished());
        committable = result.getCommittableTokens();
        replayable  = result.getReplayableTokens();
        assertTrue(committable.size() == 3);
        assertTrue(replayable.size() == 0);
        assertTrue(committable.containsAll(list));
        assertTrue(committable.get(0).equals(list.get(0)));
        assertTrue(committable.get(1).equals(list.get(1)));
        assertTrue(committable.get(2).equals(list.get(2)));
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

  public void testToString()
    throws BNFTestException {
    BNFOptionalTest test     =
      new BNFOptionalTest(new BNFTestImplementor[] {new BNFNoFallthruTest(0)});
    String          expected =
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
