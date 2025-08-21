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

package com.ohrasys.cad.gds.dao;

import java.util.ResourceBundle;

/**
 * A transform data access object
 *
 * @author   $Author: tvaline $
 * @version  $Revision: 1.6 $, $Date: 2005/05/18 07:11:09 $
 */
public class Transform implements InfoProvider {

    /** The resource bundle */
    private static final String bundle = "com/ohrasys/cad/gds/dao/JGDSDAOProperties" /* NOI18N */;

    /** Holds value of property angle. */
    private double angle;

    /** The resource bundle */
    private ResourceBundle i18n;

    /** Holds value of property magnification. */
    private double magnification;

    /** Holds value of property mirrored. */
    private boolean mirrored;

    /** Holds value of property relativeAngle. */
    private boolean relativeAngle;

    /** Holds value of property relativeMagnification. */
    private boolean relativeMagnification;

    /**
     * Creates a new instance of Transform
     */
    public Transform() {
        this(0.0, 1.0, false, false, false);
    }

    /**
     * Creates a new Transform object.
     *
     * @param  angle                    The rotation angle
     * @param  magnification            The magnification
     * @param  isMirrored               Indicates the object is mirrored
     * @param  isRelativeAngle          Indicates the rotation angle is relative
     * @param  isRelativeMagnification  Indicates the magnification is relative
     */
    public Transform(double angle, double magnification, boolean isMirrored, boolean isRelativeAngle, boolean isRelativeMagnification) {
        setMagnification(magnification);
        setAngle(angle);
        setRelativeAngle(isRelativeAngle);
        setRelativeMagnification(isRelativeMagnification);
        setMirrored(isMirrored);
        this.i18n = ResourceBundle.getBundle(bundle);
    }

    /**
     * Getter for property angle.
     *
     * @return  Value of property angle.
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Returns a textual representation of the transform
     *
     * @return  A textual representation of the transform
     */
    @Override
    public String getInfo() {
        String result = new String();
        result += (this.i18n.getString("I18N_XFRM_MIRD" /*NOI18N*/) +
                (this.mirrored
                        ? this.i18n.getString("I18N_XFRM_YES" /*NOI18N*/)
                        : this.i18n.getString("I18N_XFRM_NO" /*NOI18N*/)) +
                "\n" /*NOI18N*/);
        result += (this.i18n.getString("I18N_XFRM_RANG" /*NOI18N*/) +
                (this.relativeAngle
                        ? this.i18n.getString("I18N_XFRM_YES" /*NOI18N*/)
                        : this.i18n.getString("I18N_XFRM_NO" /*NOI18N*/)) +
                "\n" /*NOI18N*/);
        result += (this.i18n.getString("I18N_XFRM_RMAG" /*NOI18N*/) +
                (this.relativeMagnification
                        ? this.i18n.getString("I18N_XFRM_YES" /*NOI18N*/)
                        : this.i18n.getString("I18N_XFRM_YES" /*NOI18N*/)) +
                "\n" /*NOI18N*/);
        result += (this.i18n.getString("I18N_XFRM_ANG" /*NOI18N*/) +
                String.format("%.4f" /*NOI18N*/, this.angle) +
                "\n" /*NOI18N*/);
        result += (this.i18n.getString("I18N_XFRM_MAG" /*NOI18N*/) +
                String.format("%.4f" /*NOI18N*/, this.magnification) +
                "\n" /*NOI18N*/);

        return result;
    }

    /**
     * Getter for property magnification.
     *
     * @return  Value of property magnification.
     */
    public double getMagnification() {
        return this.magnification;
    }

    /**
     * Getter for property mirrored.
     *
     * @return  Value of property mirrored.
     */
    public boolean isMirrored() {
        return this.mirrored;
    }

    /**
     * Getter for property relativeAngle.
     *
     * @return  Value of property relativeAngle.
     */
    public boolean isRelativeAngle() {
        return this.relativeAngle;
    }

    /**
     * Getter for property relativeMagnification.
     *
     * @return  Value of property relativeMagnification.
     */
    public boolean isRelativeMagnification() {
        return this.relativeMagnification;
    }

    /**
     * Setter for property angle.
     *
     * @param  angle  New value of property angle.
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Setter for property magnification.
     *
     * @param  magnification  New value of property magnification.
     */
    public void setMagnification(double magnification) {
        this.magnification = magnification;
    }

    /**
     * Setter for property mirrored.
     *
     * @param  mirrored  New value of property mirrored.
     */
    public void setMirrored(boolean mirrored) {
        this.mirrored = mirrored;
    }

    /**
     * Setter for property relativeAngle.
     *
     * @param  relativeAngle  New value of property relativeAngle.
     */
    public void setRelativeAngle(boolean relativeAngle) {
        this.relativeAngle = relativeAngle;
    }

    /**
     * Setter for property relativeMagnification.
     *
     * @param  relativeMagnification  New value of property relativeMagnification.
     */
    public void setRelativeMagnification(boolean relativeMagnification) {
        this.relativeMagnification = relativeMagnification;
    }

    /**
     * Returns a string representation of the transform
     *
     * @return  A string representation of the transform
     */
    @Override
    public String toString() {
        return this.i18n.getString("I18N_XFRM_XFRM" /*NOI18N*/);
    }
} // end class Transform

/* This material is distributed under the GNU General Public License.
 * For more information please go to http://www.gnu.org/copyleft/gpl.html */
