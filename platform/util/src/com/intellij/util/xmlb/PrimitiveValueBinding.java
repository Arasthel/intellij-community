/*
 * Copyright 2000-2014 JetBrains s.r.o.
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
package com.intellij.util.xmlb;

import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class PrimitiveValueBinding extends Binding {
  private final Class<?> myType;

  public PrimitiveValueBinding(@NotNull Class<?> myType, @Nullable Accessor accessor) {
    super(accessor);

    this.myType = myType;
  }

  @Override
  public Object serialize(Object o, Object context, SerializationFilter filter) {
    return new Text(String.valueOf(o));
  }

  @Override
  @Nullable
  public Object deserialize(Object o, @NotNull Object node) {
    return convertString(node instanceof Attribute ? ((Attribute)node).getValue() : ((Content)node).getValue());
  }

  @Override
  public Object deserializeEmpty(Object context) {
    return convertString("");
  }

  @Nullable
  protected Object convertString(String value) {
    return XmlSerializerImpl.convert(value, myType);
  }

  @Override
  public boolean isBoundTo(Object node) {
    throw new UnsupportedOperationException("Method isBoundTo is not supported in " + getClass());
  }

  @Override
  public Class getBoundNodeType() {
    return Text.class;
  }
}
