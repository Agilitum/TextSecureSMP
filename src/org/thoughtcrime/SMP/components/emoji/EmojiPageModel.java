package org.thoughtcrime.SMP.components.emoji;

public interface EmojiPageModel {
  int getIconAttr();
  String[] getEmoji();
  boolean hasSpriteMap();
  String getSprite();
  boolean isDynamic();
}
