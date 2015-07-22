/*
 * Copyright (c) 1997, 1999, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.sun.java.swing.plaf.motif;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;

import javax.swing.plaf.basic.BasicSliderUI;

/**
 * Motif Slider
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases.  The current serialization support is appropriate
 * for short term storage or RMI between applications running the same
 * version of Swing.  A future release of Swing will provide support for
 * long term persistence.
 *
 * @author Jeff Dinkins
 */
public class MotifSliderUI extends BasicSliderUI {

    static final Dimension PREFERRED_HORIZONTAL_SIZE = new Dimension(164, 15);
    static final Dimension PREFERRED_VERTICAL_SIZE = new Dimension(15, 164);

    static final Dimension MINIMUM_HORIZONTAL_SIZE = new Dimension(43, 15);
    static final Dimension MINIMUM_VERTICAL_SIZE = new Dimension(15, 43);

    /**
     * MotifSliderUI Constructor
     */
    public MotifSliderUI(JSlider b)   {
        super(b);
    }

    /**
     * create a MotifSliderUI object
     */
    public static ComponentUI createUI(JComponent b)    {
        return new MotifSliderUI((JSlider)b);
    }

    public Dimension getPreferredHorizontalSize() {
        return PREFERRED_HORIZONTAL_SIZE;
    }

    public Dimension getPreferredVerticalSize() {
        return PREFERRED_VERTICAL_SIZE;
    }

    public Dimension getMinimumHorizontalSize() {
        return MINIMUM_HORIZONTAL_SIZE;
    }

    public Dimension getMinimumVerticalSize() {
        return MINIMUM_VERTICAL_SIZE;
    }

    protected Dimension getThumbSize() {
        if ( slider.getOrientation() == JSlider.HORIZONTAL ) {
            return new Dimension( 30, 15 );
        }
        else {
            return new Dimension( 15, 30 );
        }
    }

    public void paintFocus(Graphics g)  {
    }

    public void paintTrack(Graphics g)  {
    }

    public void paintThumb(Graphics g)  {
        Rectangle knobBounds = thumbRect;

        int x = knobBounds.x;
        int y = knobBounds.y;
        int w = knobBounds.width;
        int h = knobBounds.height;

        if ( slider.isEnabled() ) {
            g.setColor(slider.getForeground());
        }
        else {
            // PENDING(jeff) - the thumb should be dithered when disabled
            g.setColor(slider.getForeground().darker());
        }

        if ( slider.getOrientation() == JSlider.HORIZONTAL ) {
            g.translate(x, knobBounds.y-1);

            // fill
            g.fillRect(0, 1, w, h - 1);

            // highlight
            g.setColor(getHighlightColor());
            g.drawLine(0, 1, w - 1, 1);             // top
            g.drawLine(0, 1, 0, h);                     // left
            g.drawLine(w/2, 2, w/2, h-1);       // center

            // shadow
            g.setColor(getShadowColor());
            g.drawLine(0, h, w - 1, h);         // bottom
            g.drawLine(w - 1, 1, w - 1, h);     // right
            g.drawLine(w/2 - 1, 2, w/2 - 1, h); // center

            g.translate(-x, -(knobBounds.y-1));
        }
        else {
            g.translate(knobBounds.x-1, 0);

            // fill
            g.fillRect(1, y, w - 1, h);

            // highlight
            g.setColor(getHighlightColor());
            g.drawLine(1, y, w, y);                     // top
            g.drawLine(1, y+1, 1, y+h-1);               // left
            g.drawLine(2, y+h/2, w-1, y+h/2);           // center

            // shadow
            g.setColor(getShadowColor());
            g.drawLine(2, y+h-1, w, y+h-1);             // bottom
            g.drawLine(w, y+h-1, w, y);                 // right
            g.drawLine(2, y+h/2-1, w-1, y+h/2-1);       // center

            g.translate(-(knobBounds.x-1), 0);
        }
    }
}
