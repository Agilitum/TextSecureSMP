/** 
 * Copyright (C) 2011 Whisper Systems
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thoughtcrime.SMP.mms;

import android.content.Context;

import org.thoughtcrime.SMP.R;
import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.util.MediaUtil;
import org.thoughtcrime.SMP.util.Util;

import java.util.LinkedList;
import java.util.List;

import ws.com.google.android.mms.pdu.PduBody;

public class SlideDeck {

  private final List<Slide> slides = new LinkedList<>();

  public SlideDeck(SlideDeck copy) {
    this.slides.addAll(copy.getSlides());
  }

  public SlideDeck(Context context, MasterSecret masterSecret, PduBody body) {
    for (int i=0;i<body.getPartsNum();i++) {
      String contentType = Util.toIsoString(body.getPart(i).getContentType());
      Slide  slide       = MediaUtil.getSlideForPart(context, masterSecret, body.getPart(i), contentType);
      if (slide != null) slides.add(slide);
    }
  }

  public SlideDeck() {
  }

  public void clear() {
    slides.clear();
  }

  public PduBody toPduBody() {
    PduBody body = new PduBody();

    for (Slide slide : slides) {
      body.addPart(slide.getPart());
    }

    return body;
  }

  public void addSlide(Slide slide) {
    slides.add(slide);
  }

  public List<Slide> getSlides() {
    return slides;
  }

  public boolean containsMediaSlide() {
    for (Slide slide : slides) {
      if (slide.hasImage() || slide.hasVideo() || slide.hasAudio()) {
        return true;
      }
    }
    return false;
  }

  public Slide getThumbnailSlide(Context context) {
    for (Slide slide : slides) {
      if (slide.hasImage()) {
        return slide;
      }
    }
    return null;
  }
}
