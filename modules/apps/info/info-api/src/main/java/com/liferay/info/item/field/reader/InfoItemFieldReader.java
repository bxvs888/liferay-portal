/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.info.item.field.reader;

import com.liferay.info.field.InfoField;

/**
 * @author Jürgen Kappler
 * @author Jorge Ferrer
 */
public interface InfoItemFieldReader<T> {

	/**
	 *   @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *          #getInfoField()}
	 */
	@Deprecated
	public InfoField getField();

	public default InfoField getInfoField() {
		return getField();
	}

	public default String getKey() {
		Class<?> clazz = getClass();

		return clazz.getName();
	}

	public Object getValue(T model);

}