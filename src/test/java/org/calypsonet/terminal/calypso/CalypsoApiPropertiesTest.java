/* **************************************************************************************
 * Copyright (c) 2021 Calypso Networks Association https://calypsonet.org/
 *
 * See the NOTICE file(s) distributed with this work for additional information
 * regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 ************************************************************************************** */
package org.calypsonet.terminal.calypso;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalypsoApiPropertiesTest {

  private static String libVersion;

  @BeforeClass
  public static void beforeClass() throws Exception {
    InputStream inputStream = new FileInputStream("gradle.properties");
    try {
      Properties properties = new Properties();
      properties.load(inputStream);
      libVersion = properties.getProperty("version");
    } finally {
      inputStream.close();
    }
  }

  @Test
  public void versionIsCorrectlyWritten() {
    String apiVersion = CalypsoApiProperties.VERSION;
    assertThat(apiVersion).matches("\\d+\\.\\d+");
    assertThat(libVersion).startsWith(apiVersion);
  }
}
