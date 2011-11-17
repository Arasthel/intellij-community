/*
 * Copyright 2000-2011 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.ide.navigationToolbar.ui;

import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.util.ui.UIUtil;

import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public class CommonNavBarUI extends AbstractNavBarUI {
  @Override
  public void doPaintWrapperPanel(Graphics2D g, Rectangle bounds, boolean mainToolbarVisible) {
    if (mainToolbarVisible) {
      g.setColor(Gray._200);
      g.fillRect(0, 0, bounds.width, bounds.height);
    } else {
      final Color startColor = UIUtil.getControlColor();
      final Color endColor = ColorUtil.shift(startColor, 7.0d / 8.0d);
      g.setPaint(new GradientPaint(0, 0, startColor, 0, bounds.height, endColor));
      g.fillRect(0, 0, bounds.width, bounds.height);
    }
  }
}
