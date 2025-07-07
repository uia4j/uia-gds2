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

package com.ohrasys.cad.gds;

import java.io.*;

/**
 * A utility to convert byte arrays into java types and vice versa.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.9 $
 * @since    1.5
 */
public class GDSByteConverter {
  /**
   * Appends a java byte onto the <code>ByteArrayOutputStream</code>
   *
   * @param   data  The data to write.
   * @param   baos  The <code>ByteArrayOutputStream</code>
   *
   * @return  The original output stream with the byte appended to it.
   */
  public static ByteArrayOutputStream fromByte(byte data,
      ByteArrayOutputStream baos) {
    DataOutputStream dos = new DataOutputStream(baos);

    try {
      dos.writeByte(data);
      dos.close();
    } catch(IOException e) { /* do nothing */
    }

    return baos;
  }

  /**
   * Appends a java double onto the <code>ByteArrayOutputStream</code>
   *
   * @param   data  The data to write.
   * @param   baos  The <code>ByteArrayOutputStream</code>
   *
   * @return  The original output stream with the byte representation of the
   *          double appended to it.
   */
  public static ByteArrayOutputStream fromDouble(double data,
      ByteArrayOutputStream baos) {
    DataOutputStream dos = new DataOutputStream(baos);

    try {
      dos.writeDouble(data);
      dos.close();
    } catch(IOException e) { /* do nothing */
    }

    return baos;
  }

  /**
   * Appends a java float onto the <code>ByteArrayOutputStream</code>
   *
   * @param   data  The data to write.
   * @param   baos  The <code>ByteArrayOutputStream</code>
   *
   * @return  The original output stream with the byte representation of the
   *          float appended to it.
   */
  public static ByteArrayOutputStream fromFloat(float data,
      ByteArrayOutputStream baos) {
    DataOutputStream dos = new DataOutputStream(baos);

    try {
      dos.writeFloat(data);
      dos.close();
    } catch(IOException e) { /* do nothing */
    }

    return baos;
  }

  /**
   * Appends a java int onto the <code>ByteArrayOutputStream</code>
   *
   * @param   data  The data to write.
   * @param   baos  The <code>ByteArrayOutputStream</code>
   *
   * @return  The original output stream with the byte representation of the int
   *          appended to it.
   */
  public static ByteArrayOutputStream fromInt(int data,
      ByteArrayOutputStream baos) {
    DataOutputStream dos = new DataOutputStream(baos);

    try {
      dos.writeInt(data);
      dos.close();
    } catch(IOException e) { /* do nothing */
    }

    return baos;
  }

  /**
   * Appends a java long onto the <code>ByteArrayOutputStream</code>
   *
   * @param   data  The data to write.
   * @param   baos  The <code>ByteArrayOutputStream</code>
   *
   * @return  The original output stream with the byte representation of the
   *          long appended to it.
   */
  public static ByteArrayOutputStream fromLong(long data,
      ByteArrayOutputStream baos) {
    DataOutputStream dos = new DataOutputStream(baos);

    try {
      dos.writeLong(data);
      dos.close();
    } catch(IOException e) { /* do nothing */
    }

    return baos;
  }

  /**
   * Appends a java short onto the <code>ByteArrayOutputStream</code>
   *
   * @param   data  The data to write.
   * @param   baos  The <code>ByteArrayOutputStream</code>
   *
   * @return  The original output stream with the byte representation of the
   *          short appended to it.
   */
  public static ByteArrayOutputStream fromShort(short data,
      ByteArrayOutputStream baos) {
    DataOutputStream dos = new DataOutputStream(baos);

    try {
      dos.writeShort(data);
      dos.close();
    } catch(IOException e) { /* do nothing */
    }

    return baos;
  }

  /**
   * Converts a byte array from the input stream into an equivalent byte.
   *
   * @param   in  The input stream from which to read.
   *
   * @return  A byte from the input stream.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static byte readByte(InputStream in)
    throws NumberFormatException {
    try {
      byte data[] = new byte[1];
      in.read(data, 0, 1);

      return toByte(data);
    } catch(IOException e) {
      throw new NumberFormatException(e.getMessage());
    }
  }

  /**
   * Converts a byte array from the input stream into an equivalent double.
   *
   * @param   in  The input stream from which to read.
   *
   * @return  A double from the input stream.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static double readDouble(InputStream in)
    throws NumberFormatException {
    try {
      byte data[] = new byte[8];
      in.read(data, 0, 8);

      return toDouble(data);
    } catch(IOException e) {
      throw new NumberFormatException(e.getMessage());
    }
  }

  /**
   * Converts a byte array from the input stream into an equivalent float.
   *
   * @param   in  The input stream from which to read.
   *
   * @return  A float from the input stream.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static float readFloat(InputStream in)
    throws NumberFormatException {
    try {
      byte data[] = new byte[4];
      in.read(data, 0, 4);

      return toFloat(data);
    } catch(IOException e) {
      throw new NumberFormatException(e.getMessage());
    }
  }

  /**
   * Converts a byte array from the input stream into an equivalent int.
   *
   * @param   in  The input stream from which to read.
   *
   * @return  A int from the input stream.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static int readInt(InputStream in)
    throws NumberFormatException {
    try {
      byte data[] = new byte[4];
      in.read(data, 0, 4);

      return toInt(data);
    } catch(IOException e) {
      throw new NumberFormatException(e.getMessage());
    }
  }

  /**
   * Converts a byte array from the input stream into an equivalent long.
   *
   * @param   in  The input stream from which to read.
   *
   * @return  A long from the input stream.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static long readLong(InputStream in)
    throws NumberFormatException {
    try {
      byte data[] = new byte[8];
      in.read(data, 0, 8);

      return toLong(data);
    } catch(IOException e) {
      throw new NumberFormatException(e.getMessage());
    }
  }

  /**
   * Converts a byte array from the input stream into an equivalent short.
   *
   * @param   in  The input stream from which to read.
   *
   * @return  A short from the input stream.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static short readShort(InputStream in)
    throws NumberFormatException {
    try {
      byte data[] = new byte[2];
      in.read(data, 0, 2);

      return toShort(data);
    } catch(IOException e) {
      throw new NumberFormatException(e.getMessage());
    }
  }

  /**
   * Converts the byte array representation of the byte to an equivalent byte.
   *
   * @param   bytes  The byte representation of the byte.
   *
   * @return  The java representation of the byte.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static byte toByte(byte bytes[])
    throws NumberFormatException {
    if(bytes.length < 1) {
      throw new NumberFormatException(GDSI18NFactory.getString(
          GDSI18NFactory.i18n_1TOBYTE_THROW));
    } else {
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      DataInputStream      dis  = new DataInputStream(bais);

      try {
        byte result = dis.readByte();
        dis.close();
        bais.close();

        return result;
      } catch(IOException e) {
        throw new NumberFormatException(e.getMessage());
      }
    }
  }

  /**
   * Converts the byte array representation of the double to an equivalent
   * double.
   *
   * @param   bytes  The byte representation of the double.
   *
   * @return  The java representation of the double.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static double toDouble(byte bytes[])
    throws NumberFormatException {
    if(bytes.length < 8) {
      throw new NumberFormatException(GDSI18NFactory.getString(
          GDSI18NFactory.i18n_8TOBYTE_THROW));
    } else {
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      DataInputStream      dis  = new DataInputStream(bais);

      try {
        double result = dis.readDouble();
        dis.close();
        bais.close();

        return result;
      } catch(IOException e) {
        throw new NumberFormatException(e.getMessage());
      }
    }
  }

  /**
   * Converts the byte array representation of the float to an equivalent float.
   *
   * @param   bytes  The byte representation of the float.
   *
   * @return  The java representation of the float.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static float toFloat(byte bytes[])
    throws NumberFormatException {
    if(bytes.length < 4) {
      throw new NumberFormatException(GDSI18NFactory.getString(
          GDSI18NFactory.i18n_4TOBYTE_THROW));
    } else {
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      DataInputStream      dis  = new DataInputStream(bais);

      try {
        float result = dis.readFloat();
        dis.close();
        bais.close();

        return result;
      } catch(IOException e) {
        throw new NumberFormatException(e.getMessage());
      }
    }
  }

  /**
   * Converts the byte array representation of the int to an equivalent int.
   *
   * @param   bytes  The byte representation of the int.
   *
   * @return  The java representation of the int.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static int toInt(byte bytes[])
    throws NumberFormatException {
    if(bytes.length < 4) {
      throw new NumberFormatException(GDSI18NFactory.getString(
          GDSI18NFactory.i18n_4TOBYTE_THROW));
    } else {
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      DataInputStream      dis  = new DataInputStream(bais);

      try {
        int result = dis.readInt();
        dis.close();
        bais.close();

        return result;
      } catch(IOException e) {
        throw new NumberFormatException(e.getMessage());
      }
    }
  }

  /**
   * Converts the byte array representation of the long to an equivalent long.
   *
   * @param   bytes  The byte representation of the long.
   *
   * @return  The java representation of the long.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static long toLong(byte bytes[])
    throws NumberFormatException {
    if(bytes.length < 8) {
      throw new NumberFormatException(GDSI18NFactory.getString(
          GDSI18NFactory.i18n_8TOBYTE_THROW));
    } else {
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      DataInputStream      dis  = new DataInputStream(bais);

      try {
        long result = dis.readLong();
        dis.close();
        bais.close();

        return result;
      } catch(IOException e) {
        throw new NumberFormatException(e.getMessage());
      }
    }
  }

  /**
   * Converts the byte array representation of the short to an equivalent short.
   *
   * @param   bytes  The byte representation of the short.
   *
   * @return  The java representation of the short.
   *
   * @throws  NumberFormatException  If the an error in processing occurs.
   */
  public static short toShort(byte bytes[])
    throws NumberFormatException {
    if(bytes.length < 2) {
      throw new NumberFormatException(GDSI18NFactory.getString(
          GDSI18NFactory.i18n_2TOBYTE_THROW));
    } else {
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      DataInputStream      dis  = new DataInputStream(bais);

      try {
        short result = dis.readShort();
        dis.close();
        bais.close();

        return result;
      } catch(IOException e) {
        throw new NumberFormatException(e.getMessage());
      }
    }
  }

  /**
   * Converts a java byte into an equivalent byte array representation.
   *
   * @param   data  The data to convert.
   *
   * @return  an equivalent byte array representation of the data.
   */
  public static byte[] writeByte(byte data) {
    byte                  result[] = null;
    ByteArrayOutputStream baos     = new ByteArrayOutputStream();
    DataOutputStream      dos      = new DataOutputStream(baos);

    try {
      dos.writeByte(data);
      result = baos.toByteArray();
      dos.close();
      baos.close();
    } catch(IOException e) { /* do nothing */
    }

    return result;
  }

  /**
   * Converts a java double into an equivalent byte array representation.
   *
   * @param   data  The data to convert.
   *
   * @return  an equivalent byte array representation of the data.
   */
  public static byte[] writeDouble(double data) {
    byte                  result[] = null;
    ByteArrayOutputStream baos     = new ByteArrayOutputStream();
    DataOutputStream      dos      = new DataOutputStream(baos);

    try {
      dos.writeDouble(data);
      result = baos.toByteArray();
      dos.close();
      baos.close();
    } catch(IOException e) { /* do nothing */
    }

    return result;
  }

  /**
   * Converts a java float into an equivalent byte array representation.
   *
   * @param   data  The data to convert.
   *
   * @return  an equivalent byte array representation of the data.
   */
  public static byte[] writeFloat(float data) {
    byte                  result[] = null;
    ByteArrayOutputStream baos     = new ByteArrayOutputStream();
    DataOutputStream      dos      = new DataOutputStream(baos);

    try {
      dos.writeFloat(data);
      result = baos.toByteArray();
      dos.close();
      baos.close();
    } catch(IOException e) { /* do nothing */
    }

    return result;
  }

  /**
   * Converts a java int into an equivalent byte array representation.
   *
   * @param   data  The data to convert.
   *
   * @return  an equivalent byte array representation of the data.
   */
  public static byte[] writeInt(int data) {
    byte                  result[] = null;
    ByteArrayOutputStream baos     = new ByteArrayOutputStream();
    DataOutputStream      dos      = new DataOutputStream(baos);

    try {
      dos.writeInt(data);
      result = baos.toByteArray();
      dos.close();
      baos.close();
    } catch(IOException e) { /* do nothing */
    }

    return result;
  }

  /**
   * Converts a java long into an equivalent byte array representation.
   *
   * @param   data  The data to convert.
   *
   * @return  an equivalent byte array representation of the data.
   */
  public static byte[] writeLong(long data) {
    byte                  result[] = null;
    ByteArrayOutputStream baos     = new ByteArrayOutputStream();
    DataOutputStream      dos      = new DataOutputStream(baos);

    try {
      dos.writeLong(data);
      result = baos.toByteArray();
      dos.close();
      baos.close();
    } catch(IOException e) { /* do nothing */
    }

    return result;
  }

  /**
   * Converts a java short into an equivalent byte array representation.
   *
   * @param   data  The data to convert.
   *
   * @return  an equivalent byte array representation of the data.
   */
  public static byte[] writeShort(short data) {
    byte                  result[] = null;
    ByteArrayOutputStream baos     = new ByteArrayOutputStream();
    DataOutputStream      dos      = new DataOutputStream(baos);

    try {
      dos.writeShort(data);
      result = baos.toByteArray();
      dos.close();
      baos.close();
    } catch(IOException e) { /* do nothing */
    }

    return result;
  }

  /**
   * Returns a string representation of this class
   *
   * @return  The physical address of this instance
   */
  public String toString(){return super.toString();}
} // end class GDSByteConverter


/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
