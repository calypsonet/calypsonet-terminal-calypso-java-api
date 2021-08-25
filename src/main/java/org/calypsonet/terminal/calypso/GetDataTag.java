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

/**
 * Enumeration of all supported tags for the Get Data command.
 *
 * <p>May not be applicable to all products.
 *
 * @since 1.0.0
 */
public enum GetDataTag {

  /**
   * FCP for the current file, as returned by Select File.
   *
   * @since 1.0.0
   */
  FCP_FOR_CURRENT_FILE,
  /**
   * FCI for the current DF, as returned by Select Application.
   *
   * @since 1.0.0
   */
  FCI_FOR_CURRENT_DF
}
