/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.adapter.apt;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import io.sundr.adapter.api.Adapter;
import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.AdapterFactory;

public class AptAdapterFactory
    implements AdapterFactory<TypeElement, TypeMirror, VariableElement, ExecutableElement> {

  @Override
  public Adapter<TypeElement, TypeMirror, VariableElement, ExecutableElement> create(AdapterContext context) {
    return new AptAdapter(context);
  }

  @Override
  public Class<TypeElement> getTypeAdapterType() {
    return TypeElement.class;
  }

  @Override
  public Class<TypeMirror> getReferenceAdapterType() {
    return TypeMirror.class;
  }

  @Override
  public Class<ExecutableElement> getMethodAdapterType() {
    return ExecutableElement.class;
  }

  @Override
  public Class<VariableElement> getPropertyAdapterType() {
    return VariableElement.class;
  }
}
