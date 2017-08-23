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

package com.liferay.commerce.internal.model.listener;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CommerceCartItemLocalService;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceWarehouseItemLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 */
@Component(immediate = true, service = ModelListener.class)
public class CPInstanceModelListener extends BaseModelListener<CPInstance> {

	@Override
	public void onBeforeRemove(CPInstance cpInstance) {
		try {
			long cpInstanceId = cpInstance.getCPInstanceId();

			_commerceCartItemLocalService.deleteCommerceCartItemsByCPInstanceId(
				cpInstanceId);
			_commerceOrderItemLocalService.
				deleteCommerceOrderItemsByCPInstanceId(cpInstanceId);
			_commerceWarehouseItemLocalService.deleteCommerceWarehouseItems(
				CPInstance.class.getName(), cpInstanceId);
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPInstanceModelListener.class);

	@Reference
	private CommerceCartItemLocalService _commerceCartItemLocalService;

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CommerceWarehouseItemLocalService
		_commerceWarehouseItemLocalService;

}