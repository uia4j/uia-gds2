/*
 * Copyright (C) 2004 Thomas N. Valine
 * tvaline@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package com.ohrasys.cad.gds;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Converts java floating point and string types to the corresponding GDSII
 * types.
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.18 $
 * @since    1.5
 */
public class GDSSpecificDataConverter {

    /**
     * Returns a flattened representation of a hierarchical list.
     *
     * @param   flat  The list in which to store the results
     * @param   hier  The list to flatten
     *
     * @return  The flattened list
     */
    @SuppressWarnings("unchecked")
    public static List<Object> flattenList(List<Object> flat, List<Object> hier) {
        if ((flat == null) || (hier == null)) {
            return new ArrayList<Object>();
        }
        for (int i = 0; i < hier.size(); i++) {
            if (!(hier.get(i) instanceof java.util.List)) {
                flat.add(hier.get(i));
            }
            else if (((List<Object>) hier.get(i)).size() == 0) {
                continue;
            }
            else {
                flat = flattenList(flat, (List<Object>) hier.get(i));
            }
        }

        return flat;
    }

    /**
     * Converts a java double to a GDSII double and stores it in the specified
     * stream.
     *
     * @param   data  The double to process.
     * @param   baos  The output stream to store the double.
     *
     * @return  A GDSII double.
     */
    public static ByteArrayOutputStream fromJavaDouble(
            double data,
            ByteArrayOutputStream baos) {
        byte bytes[] = writeJavaDouble(data);

        try {
            baos.write(bytes);
        }
        catch (IOException e) { /* do nothing */
        }

        return baos;
    }

    /**
     * Converts a java float to a GDSII float and stores it in the specified
     * stream.
     *
     * @param   data  The float to process.
     * @param   baos  The output stream to store the float.
     *
     * @return  A GDSII float.
     */
    public static ByteArrayOutputStream fromJavaFloat(
            float data,
            ByteArrayOutputStream baos) {
        byte bytes[] = writeJavaFloat(data);

        try {
            baos.write(bytes);
        }
        catch (IOException e) { /* do nothing */
        }

        return baos;
    }

    /**
     * Converts a java string to a GDSII string and stores it in the specified
     * stream.
     *
     * @param   data  The string to process.
     * @param   baos  The output stream to store the string.
     *
     * @return  A GDSII string.
     */
    public static ByteArrayOutputStream fromJavaString(
            String data,
            ByteArrayOutputStream baos) {
        byte bytes[] = writeJavaString(data);

        try {
            baos.write(bytes);
        }
        catch (IOException e) { /* do nothing */
        }

        return baos;
    }

    /**
     * Converts a java string to a GDSII string and stores it in the specified
     * stream.
     *
     * @param   data  The string to process.
     * @param   len   The required length of the resulting string.
     * @param   baos  The output stream to store the string.
     *
     * @return  A GDSII string of specified length.
     */
    public static ByteArrayOutputStream fromJavaString(
            String data,
            int len,
            ByteArrayOutputStream baos) {
        byte bytes[] = writeJavaString(data, len);

        try {
            baos.write(bytes);
        }
        catch (IOException e) { /* do nothing */
        }

        return baos;
    }

    /**
     * Reads a GDSII double from the stream and converts it to a java double.
     *
     * @param   in  The stream to read the GDSII double from.
     *
     * @return  A java double.
     *
     * @throws  NumberFormatException  If a number format exception occurs.
     */
    public static double readDouble(InputStream in) throws NumberFormatException {
        try {
            byte data[] = new byte[8];
            in.read(data, 0, 8);

            return toDouble(data);
        }
        catch (IOException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    /**
     * Reads a GDSII float from the stream and converts it to a java float.
     *
     * @param   in  The stream to read the GDSII float from.
     *
     * @return  A java float.
     *
     * @throws  NumberFormatException  If a number format exception occurs.
     */
    public static float readFloat(InputStream in) throws NumberFormatException {
        try {
            byte data[] = new byte[4];
            in.read(data, 0, 4);

            return toFloat(data);
        }
        catch (IOException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    /**
     * Converts a GDSII double to a java double.
     *
     * @param   bytes  The GDSII double to process.
     *
     * @return  A java double.
     *
     * @throws  NumberFormatException  If a number format exception occurs.
     */
    public static double toDouble(byte bytes[]) throws NumberFormatException {
        if (bytes.length < 8) {
            throw new NumberFormatException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_DATACONVERT_THROW1));
        }
        else {
            int sign;
            int exponent;
            long mantissa_int;
            double mantissa_float;
            double result;
            sign = bytes[0] & 0x80;
            exponent = (bytes[0] & 0x0000007F) - 64;
            mantissa_int = 0;

            for (int i = 1; i < 8; i++) {
                mantissa_int <<= 8;
                mantissa_int |= (bytes[i] & 0x000000FF);
            }

            mantissa_float = mantissa_int / Math.pow(2, 56);
            result = mantissa_float * Math.pow(16, exponent);

            if (sign != 0) {
                result *= -1;
            }

            return result;
        }
    } // end method toDouble

    /**
     * Converts a GDSII float to a java float.
     *
     * @param   bytes  The GDSII float to process.
     *
     * @return  A java float.
     *
     * @throws  NumberFormatException  If a number format exception occurs.
     */
    public static float toFloat(byte bytes[]) throws NumberFormatException {
        if (bytes.length < 4) {
            throw new NumberFormatException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_DATACONVERT_THROW2));
        }
        else {
            int sign;
            int exponent;
            long mantissa_int;
            double mantissa_float;
            double result;
            sign = bytes[0] & 0x80;
            exponent = (bytes[0] & 0x0000007F) - 64;
            mantissa_int = 0;

            for (int i = 1; i < 4; i++) {
                mantissa_int <<= 8;
                mantissa_int |= (bytes[i] & 0x000000FF);
            }

            mantissa_float = mantissa_int / Math.pow(2, 24);
            result = mantissa_float * Math.pow(16, exponent);

            if (sign != 0) {
                result *= -1;
            }

            return (float) result;
        }
    } // end method toFloat

    /**
     * Converts a GDSII string to a java string.
     *
     * @param   data  The GDSII string to process.
     *
     * @return  A java string.
     */
    public static String toJavaString(byte data[]) {
        return new String(data).trim();
    }

    /**
     * Returns a GDSII representation of the double.
     *
     * @param   data  The double to process.
     *
     * @return  A GDSII double.
     *
     * @throws  IllegalArgumentException  If a number format exception occurs.
     */
    public static byte[] writeJavaDouble(double data) {
        byte result[];
        BigDecimal f_mantissa;
        long mantissa;

        if (data == 0.0) {
            result = new byte[8];
            Arrays.fill(result, (byte) 0x00);

            return result;
        }

        boolean negsign = false;
        int exponent = 64;
        BigDecimal reg = new BigDecimal(data);

        if (reg.doubleValue() < 0) {
            negsign = true;
            reg = reg.negate();
        }

        for (; (reg.doubleValue() < 0.0625) && (exponent > 0); exponent--) {
            reg = reg.multiply(new BigDecimal(16.0));
        }

        if (exponent == 0) {
            throw new IllegalArgumentException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_DATACONVERT_THROW3));
        }

        for (; (reg.doubleValue() >= 1) && (exponent < 128); exponent++) {
            reg = reg.divide(new BigDecimal(16.0));
        }

        if (exponent > 127) {
            throw new IllegalArgumentException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_DATACONVERT_THROW4));
        }

        if (negsign) {
            exponent |= 0x00000080;
        }

        f_mantissa = reg.remainder(new BigDecimal(1.0));

        for (int i = 0; i < 56; i++) {
            f_mantissa = f_mantissa.multiply(new BigDecimal(2.0));
        }

        mantissa = f_mantissa.longValue();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GDSByteConverter.fromByte((byte) exponent, baos);

        for (int i = 6; i >= 0; i--) {
            baos = GDSByteConverter.fromByte((byte) ((mantissa >> (i * 8)) &
                    0x000000FF), baos);
        }

        result = baos.toByteArray();

        try {
            baos.close();
        }
        catch (IOException e) { /* do nothing */
        }

        return result;
    } // end method writeJavaDouble

    /**
     * Returns a GDSII representation of the java float.
     *
     * @param   data  The float to process.
     *
     * @return  A GDSII float.
     *
     * @throws  IllegalArgumentException  If a number format exception is
     *                                    encountered.
     */
    public static byte[] writeJavaFloat(float data) {
        byte result[];
        BigDecimal f_mantissa;
        int mantissa;

        if (data == 0.0) {
            result = new byte[4];
            Arrays.fill(result, (byte) 0x00);

            return result;
        }

        boolean negsign = false;
        int exponent = 64;
        BigDecimal reg = new BigDecimal(data);

        if (reg.doubleValue() < 0) {
            negsign = true;
            reg = reg.negate();
        }

        for (; (reg.doubleValue() < 0.0625) && (exponent > 0); exponent--) {
            reg = reg.multiply(new BigDecimal(16.0));
        }

        if (exponent == 0) {
            throw new IllegalArgumentException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_DATACONVERT_THROW3));
        }

        for (; (reg.doubleValue() >= 1) && (exponent < 128); exponent++) {
            reg = reg.divide(new BigDecimal(16.0));
        }

        if (exponent > 127) {
            throw new IllegalArgumentException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_DATACONVERT_THROW4));
        }

        if (negsign) {
            exponent |= 0x00000080;
        }

        f_mantissa = reg.remainder(new BigDecimal(1.0));

        for (int i = 0; i < 24; i++) {
            f_mantissa = f_mantissa.multiply(new BigDecimal(2.0));
        }

        mantissa = f_mantissa.intValue();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GDSByteConverter.fromByte((byte) exponent, baos);

        for (int i = 2; i >= 0; i--) {
            baos = GDSByteConverter.fromByte((byte) ((mantissa >> (i * 8)) &
                    0x000000FF), baos);
        }

        result = baos.toByteArray();

        try {
            baos.close();
        }
        catch (IOException e) { /* do nothing */
        }

        return result;
    } // end method writeJavaFloat

    /**
     * Returns a GDSII string.
     *
     * @param   data  The string to process.
     *
     * @return  A byte array representation of the string.
     */
    public static byte[] writeJavaString(String data) {
        byte result[] = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            OutputStreamWriter osw = new OutputStreamWriter(baos,
                    "ASCII" /*NOI18N*/);
            osw.write(data);
            osw.close();

            if ((baos.size() % 2) == 1) {
                GDSByteConverter.fromByte((byte) 0x00, baos);
            }

            result = baos.toByteArray();
            baos.close();
        }
        catch (IOException e) { /* do nothing */
        }

        return result;
    }

    /**
     * Returns a GDSII string of specified length.
     *
     * @param   data  The string to process.
     * @param   len   The required length of the resulting string.
     *
     * @return  A byte array representation of the string.
     *
     * @throws  IllegalArgumentException  If the string is not an even number of
     *                                    characters.
     */
    public static byte[] writeJavaString(String data, int len) {
        if ((len % 2) == 1) {
            throw new IllegalArgumentException(GDSI18NFactory.getString(
                    GDSI18NFactory.i18n_DATACONVERT_THROW5));
        }

        byte result[] = null;
        String reg;

        if (data.length() > (len - 1)) {
            reg = data.substring(0, len - 1);
        }
        else {
            reg = data;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            OutputStreamWriter osw = new OutputStreamWriter(baos,
                    "ASCII" /*NOI18N*/);
            osw.write(data);
            osw.close();

            for (int i = baos.size(); i < len; i++) {
                baos.write((byte) 0x00);
            }

            result = baos.toByteArray();
            baos.close();
        }
        catch (IOException e) { /* do nothing */
        }

        return result;
    } // end method writeJavaString

    /**
     * Returns a string representation of this class
     *
     * @return  The physical address of this instance
     */
    @Override
    public String toString() {
        return super.toString();
    }
} // end class GDSSpecificDataConverter

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
