/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.dynamicdatamapping.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourceFinder;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMContentLocalService;
import com.liferay.portlet.dynamicdatamapping.service.DDMStorageLinkLocalService;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLinkLocalService;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLinkService;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureService;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalService;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateService;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMContentPersistence;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStorageLinkPersistence;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStructureFinder;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStructureLinkPersistence;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStructurePersistence;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMTemplateFinder;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMTemplatePersistence;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the d d m template local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.dynamicdatamapping.service.impl.DDMTemplateLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.dynamicdatamapping.service.impl.DDMTemplateLocalServiceImpl
 * @see com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil
 * @generated
 */
public abstract class DDMTemplateLocalServiceBaseImpl
	implements DDMTemplateLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil} to access the d d m template local service.
	 */

	/**
	 * Adds the d d m template to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplate the d d m template
	 * @return the d d m template that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DDMTemplate addDDMTemplate(DDMTemplate ddmTemplate)
		throws SystemException {
		ddmTemplate.setNew(true);

		ddmTemplate = ddmTemplatePersistence.update(ddmTemplate, false);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.reindex(ddmTemplate);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}

		return ddmTemplate;
	}

	/**
	 * Creates a new d d m template with the primary key. Does not add the d d m template to the database.
	 *
	 * @param templateId the primary key for the new d d m template
	 * @return the new d d m template
	 */
	public DDMTemplate createDDMTemplate(long templateId) {
		return ddmTemplatePersistence.create(templateId);
	}

	/**
	 * Deletes the d d m template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param templateId the primary key of the d d m template
	 * @throws PortalException if a d d m template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDDMTemplate(long templateId)
		throws PortalException, SystemException {
		DDMTemplate ddmTemplate = ddmTemplatePersistence.remove(templateId);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.delete(ddmTemplate);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}
	}

	/**
	 * Deletes the d d m template from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplate the d d m template
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDDMTemplate(DDMTemplate ddmTemplate)
		throws SystemException {
		ddmTemplatePersistence.remove(ddmTemplate);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.delete(ddmTemplate);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return ddmTemplatePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return ddmTemplatePersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return ddmTemplatePersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return ddmTemplatePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the d d m template with the primary key.
	 *
	 * @param templateId the primary key of the d d m template
	 * @return the d d m template
	 * @throws PortalException if a d d m template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMTemplate getDDMTemplate(long templateId)
		throws PortalException, SystemException {
		return ddmTemplatePersistence.findByPrimaryKey(templateId);
	}

	/**
	 * Returns the d d m template with the UUID in the group.
	 *
	 * @param uuid the UUID of d d m template
	 * @param groupId the group id of the d d m template
	 * @return the d d m template
	 * @throws PortalException if a d d m template with the UUID in the group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMTemplate getDDMTemplateByUuidAndGroupId(String uuid, long groupId)
		throws PortalException, SystemException {
		return ddmTemplatePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the d d m templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m templates
	 * @param end the upper bound of the range of d d m templates (not inclusive)
	 * @return the range of d d m templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMTemplate> getDDMTemplates(int start, int end)
		throws SystemException {
		return ddmTemplatePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of d d m templates.
	 *
	 * @return the number of d d m templates
	 * @throws SystemException if a system exception occurred
	 */
	public int getDDMTemplatesCount() throws SystemException {
		return ddmTemplatePersistence.countAll();
	}

	/**
	 * Updates the d d m template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplate the d d m template
	 * @return the d d m template that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DDMTemplate updateDDMTemplate(DDMTemplate ddmTemplate)
		throws SystemException {
		return updateDDMTemplate(ddmTemplate, true);
	}

	/**
	 * Updates the d d m template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplate the d d m template
	 * @param merge whether to merge the d d m template with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the d d m template that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DDMTemplate updateDDMTemplate(DDMTemplate ddmTemplate, boolean merge)
		throws SystemException {
		ddmTemplate.setNew(false);

		ddmTemplate = ddmTemplatePersistence.update(ddmTemplate, merge);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.reindex(ddmTemplate);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}

		return ddmTemplate;
	}

	/**
	 * Returns the d d m content local service.
	 *
	 * @return the d d m content local service
	 */
	public DDMContentLocalService getDDMContentLocalService() {
		return ddmContentLocalService;
	}

	/**
	 * Sets the d d m content local service.
	 *
	 * @param ddmContentLocalService the d d m content local service
	 */
	public void setDDMContentLocalService(
		DDMContentLocalService ddmContentLocalService) {
		this.ddmContentLocalService = ddmContentLocalService;
	}

	/**
	 * Returns the d d m content persistence.
	 *
	 * @return the d d m content persistence
	 */
	public DDMContentPersistence getDDMContentPersistence() {
		return ddmContentPersistence;
	}

	/**
	 * Sets the d d m content persistence.
	 *
	 * @param ddmContentPersistence the d d m content persistence
	 */
	public void setDDMContentPersistence(
		DDMContentPersistence ddmContentPersistence) {
		this.ddmContentPersistence = ddmContentPersistence;
	}

	/**
	 * Returns the d d m storage link local service.
	 *
	 * @return the d d m storage link local service
	 */
	public DDMStorageLinkLocalService getDDMStorageLinkLocalService() {
		return ddmStorageLinkLocalService;
	}

	/**
	 * Sets the d d m storage link local service.
	 *
	 * @param ddmStorageLinkLocalService the d d m storage link local service
	 */
	public void setDDMStorageLinkLocalService(
		DDMStorageLinkLocalService ddmStorageLinkLocalService) {
		this.ddmStorageLinkLocalService = ddmStorageLinkLocalService;
	}

	/**
	 * Returns the d d m storage link persistence.
	 *
	 * @return the d d m storage link persistence
	 */
	public DDMStorageLinkPersistence getDDMStorageLinkPersistence() {
		return ddmStorageLinkPersistence;
	}

	/**
	 * Sets the d d m storage link persistence.
	 *
	 * @param ddmStorageLinkPersistence the d d m storage link persistence
	 */
	public void setDDMStorageLinkPersistence(
		DDMStorageLinkPersistence ddmStorageLinkPersistence) {
		this.ddmStorageLinkPersistence = ddmStorageLinkPersistence;
	}

	/**
	 * Returns the d d m structure local service.
	 *
	 * @return the d d m structure local service
	 */
	public DDMStructureLocalService getDDMStructureLocalService() {
		return ddmStructureLocalService;
	}

	/**
	 * Sets the d d m structure local service.
	 *
	 * @param ddmStructureLocalService the d d m structure local service
	 */
	public void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {
		this.ddmStructureLocalService = ddmStructureLocalService;
	}

	/**
	 * Returns the d d m structure remote service.
	 *
	 * @return the d d m structure remote service
	 */
	public DDMStructureService getDDMStructureService() {
		return ddmStructureService;
	}

	/**
	 * Sets the d d m structure remote service.
	 *
	 * @param ddmStructureService the d d m structure remote service
	 */
	public void setDDMStructureService(DDMStructureService ddmStructureService) {
		this.ddmStructureService = ddmStructureService;
	}

	/**
	 * Returns the d d m structure persistence.
	 *
	 * @return the d d m structure persistence
	 */
	public DDMStructurePersistence getDDMStructurePersistence() {
		return ddmStructurePersistence;
	}

	/**
	 * Sets the d d m structure persistence.
	 *
	 * @param ddmStructurePersistence the d d m structure persistence
	 */
	public void setDDMStructurePersistence(
		DDMStructurePersistence ddmStructurePersistence) {
		this.ddmStructurePersistence = ddmStructurePersistence;
	}

	/**
	 * Returns the d d m structure finder.
	 *
	 * @return the d d m structure finder
	 */
	public DDMStructureFinder getDDMStructureFinder() {
		return ddmStructureFinder;
	}

	/**
	 * Sets the d d m structure finder.
	 *
	 * @param ddmStructureFinder the d d m structure finder
	 */
	public void setDDMStructureFinder(DDMStructureFinder ddmStructureFinder) {
		this.ddmStructureFinder = ddmStructureFinder;
	}

	/**
	 * Returns the d d m structure link local service.
	 *
	 * @return the d d m structure link local service
	 */
	public DDMStructureLinkLocalService getDDMStructureLinkLocalService() {
		return ddmStructureLinkLocalService;
	}

	/**
	 * Sets the d d m structure link local service.
	 *
	 * @param ddmStructureLinkLocalService the d d m structure link local service
	 */
	public void setDDMStructureLinkLocalService(
		DDMStructureLinkLocalService ddmStructureLinkLocalService) {
		this.ddmStructureLinkLocalService = ddmStructureLinkLocalService;
	}

	/**
	 * Returns the d d m structure link remote service.
	 *
	 * @return the d d m structure link remote service
	 */
	public DDMStructureLinkService getDDMStructureLinkService() {
		return ddmStructureLinkService;
	}

	/**
	 * Sets the d d m structure link remote service.
	 *
	 * @param ddmStructureLinkService the d d m structure link remote service
	 */
	public void setDDMStructureLinkService(
		DDMStructureLinkService ddmStructureLinkService) {
		this.ddmStructureLinkService = ddmStructureLinkService;
	}

	/**
	 * Returns the d d m structure link persistence.
	 *
	 * @return the d d m structure link persistence
	 */
	public DDMStructureLinkPersistence getDDMStructureLinkPersistence() {
		return ddmStructureLinkPersistence;
	}

	/**
	 * Sets the d d m structure link persistence.
	 *
	 * @param ddmStructureLinkPersistence the d d m structure link persistence
	 */
	public void setDDMStructureLinkPersistence(
		DDMStructureLinkPersistence ddmStructureLinkPersistence) {
		this.ddmStructureLinkPersistence = ddmStructureLinkPersistence;
	}

	/**
	 * Returns the d d m template local service.
	 *
	 * @return the d d m template local service
	 */
	public DDMTemplateLocalService getDDMTemplateLocalService() {
		return ddmTemplateLocalService;
	}

	/**
	 * Sets the d d m template local service.
	 *
	 * @param ddmTemplateLocalService the d d m template local service
	 */
	public void setDDMTemplateLocalService(
		DDMTemplateLocalService ddmTemplateLocalService) {
		this.ddmTemplateLocalService = ddmTemplateLocalService;
	}

	/**
	 * Returns the d d m template remote service.
	 *
	 * @return the d d m template remote service
	 */
	public DDMTemplateService getDDMTemplateService() {
		return ddmTemplateService;
	}

	/**
	 * Sets the d d m template remote service.
	 *
	 * @param ddmTemplateService the d d m template remote service
	 */
	public void setDDMTemplateService(DDMTemplateService ddmTemplateService) {
		this.ddmTemplateService = ddmTemplateService;
	}

	/**
	 * Returns the d d m template persistence.
	 *
	 * @return the d d m template persistence
	 */
	public DDMTemplatePersistence getDDMTemplatePersistence() {
		return ddmTemplatePersistence;
	}

	/**
	 * Sets the d d m template persistence.
	 *
	 * @param ddmTemplatePersistence the d d m template persistence
	 */
	public void setDDMTemplatePersistence(
		DDMTemplatePersistence ddmTemplatePersistence) {
		this.ddmTemplatePersistence = ddmTemplatePersistence;
	}

	/**
	 * Returns the d d m template finder.
	 *
	 * @return the d d m template finder
	 */
	public DDMTemplateFinder getDDMTemplateFinder() {
		return ddmTemplateFinder;
	}

	/**
	 * Sets the d d m template finder.
	 *
	 * @param ddmTemplateFinder the d d m template finder
	 */
	public void setDDMTemplateFinder(DDMTemplateFinder ddmTemplateFinder) {
		this.ddmTemplateFinder = ddmTemplateFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the resource finder.
	 *
	 * @return the resource finder
	 */
	public ResourceFinder getResourceFinder() {
		return resourceFinder;
	}

	/**
	 * Sets the resource finder.
	 *
	 * @param resourceFinder the resource finder
	 */
	public void setResourceFinder(ResourceFinder resourceFinder) {
		this.resourceFinder = resourceFinder;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return DDMTemplate.class;
	}

	protected String getModelClassName() {
		return DDMTemplate.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = ddmTemplatePersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = DDMContentLocalService.class)
	protected DDMContentLocalService ddmContentLocalService;
	@BeanReference(type = DDMContentPersistence.class)
	protected DDMContentPersistence ddmContentPersistence;
	@BeanReference(type = DDMStorageLinkLocalService.class)
	protected DDMStorageLinkLocalService ddmStorageLinkLocalService;
	@BeanReference(type = DDMStorageLinkPersistence.class)
	protected DDMStorageLinkPersistence ddmStorageLinkPersistence;
	@BeanReference(type = DDMStructureLocalService.class)
	protected DDMStructureLocalService ddmStructureLocalService;
	@BeanReference(type = DDMStructureService.class)
	protected DDMStructureService ddmStructureService;
	@BeanReference(type = DDMStructurePersistence.class)
	protected DDMStructurePersistence ddmStructurePersistence;
	@BeanReference(type = DDMStructureFinder.class)
	protected DDMStructureFinder ddmStructureFinder;
	@BeanReference(type = DDMStructureLinkLocalService.class)
	protected DDMStructureLinkLocalService ddmStructureLinkLocalService;
	@BeanReference(type = DDMStructureLinkService.class)
	protected DDMStructureLinkService ddmStructureLinkService;
	@BeanReference(type = DDMStructureLinkPersistence.class)
	protected DDMStructureLinkPersistence ddmStructureLinkPersistence;
	@BeanReference(type = DDMTemplateLocalService.class)
	protected DDMTemplateLocalService ddmTemplateLocalService;
	@BeanReference(type = DDMTemplateService.class)
	protected DDMTemplateService ddmTemplateService;
	@BeanReference(type = DDMTemplatePersistence.class)
	protected DDMTemplatePersistence ddmTemplatePersistence;
	@BeanReference(type = DDMTemplateFinder.class)
	protected DDMTemplateFinder ddmTemplateFinder;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = ResourceFinder.class)
	protected ResourceFinder resourceFinder;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	private static Log _log = LogFactoryUtil.getLog(DDMTemplateLocalServiceBaseImpl.class);
	private String _beanIdentifier;
}