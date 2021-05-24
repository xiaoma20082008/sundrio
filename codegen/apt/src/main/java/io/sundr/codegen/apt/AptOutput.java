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

package io.sundr.codegen.apt;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.function.Function;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import io.sundr.SundrException;
import io.sundr.codegen.api.Output;
import io.sundr.model.TypeDef;

public class AptOutput implements Output<TypeDef> {

  private final Filer filer;
  private final static StringWriter DEV_NULL = new StringWriter();

  public AptOutput(Filer filer) {
    this.filer = filer;
  }

  @Override
  public Function<TypeDef, Writer> getFunction() {
    return type -> {
      try {
        FileObject fileObject = filer.getResource(StandardLocation.SOURCE_OUTPUT, type.getPackageName(), type.getName());
        File file = Paths.get(fileObject.toUri()).toFile();
        //If file exists just send output to /dev/null
        return file.exists() ? DEV_NULL : filer.createSourceFile(type.getFullyQualifiedName()).openWriter();
      } catch (IOException e) {
        throw SundrException.launderThrowable(e);
      }
    };
  }
}
