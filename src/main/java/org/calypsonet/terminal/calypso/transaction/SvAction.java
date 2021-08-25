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
package org.calypsonet.terminal.calypso.transaction;

/**
 * Defines the type Stored Value of action.
 *
 * @since 1.0.0
 */
public enum SvAction {

  /**
   * In the case of a {@link SvOperation#RELOAD}, loads a positive amount; in the case of a {@link
   * SvOperation#DEBIT}, debits a positive amount
   *
   * @since 1.0.0
   */
  DO,
  /**
   * In the case of a {@link SvOperation#RELOAD}, loads a negative amount; in the case of a {@link
   * SvOperation#DEBIT}, cancels, totally or partially, a previous debit.
   *
   * @since 1.0.0
   */
  UNDO
}
