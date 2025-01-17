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

package com.liferay.custom.elements.service.base;

import com.liferay.custom.elements.model.CustomElementsSource;
import com.liferay.custom.elements.service.CustomElementsSourceLocalService;
import com.liferay.custom.elements.service.CustomElementsSourceLocalServiceUtil;
import com.liferay.custom.elements.service.persistence.CustomElementsSourcePersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the custom elements source local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.custom.elements.service.impl.CustomElementsSourceLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.custom.elements.service.impl.CustomElementsSourceLocalServiceImpl
 * @generated
 */
public abstract class CustomElementsSourceLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CustomElementsSourceLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CustomElementsSourceLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CustomElementsSourceLocalServiceUtil</code>.
	 */

	/**
	 * Adds the custom elements source to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CustomElementsSourceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param customElementsSource the custom elements source
	 * @return the custom elements source that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CustomElementsSource addCustomElementsSource(
		CustomElementsSource customElementsSource) {

		customElementsSource.setNew(true);

		return customElementsSourcePersistence.update(customElementsSource);
	}

	/**
	 * Creates a new custom elements source with the primary key. Does not add the custom elements source to the database.
	 *
	 * @param customElementsSourceId the primary key for the new custom elements source
	 * @return the new custom elements source
	 */
	@Override
	@Transactional(enabled = false)
	public CustomElementsSource createCustomElementsSource(
		long customElementsSourceId) {

		return customElementsSourcePersistence.create(customElementsSourceId);
	}

	/**
	 * Deletes the custom elements source with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CustomElementsSourceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param customElementsSourceId the primary key of the custom elements source
	 * @return the custom elements source that was removed
	 * @throws PortalException if a custom elements source with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CustomElementsSource deleteCustomElementsSource(
			long customElementsSourceId)
		throws PortalException {

		return customElementsSourcePersistence.remove(customElementsSourceId);
	}

	/**
	 * Deletes the custom elements source from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CustomElementsSourceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param customElementsSource the custom elements source
	 * @return the custom elements source that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CustomElementsSource deleteCustomElementsSource(
		CustomElementsSource customElementsSource) {

		return customElementsSourcePersistence.remove(customElementsSource);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return customElementsSourcePersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CustomElementsSource.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return customElementsSourcePersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.custom.elements.model.impl.CustomElementsSourceModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return customElementsSourcePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.custom.elements.model.impl.CustomElementsSourceModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return customElementsSourcePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return customElementsSourcePersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return customElementsSourcePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CustomElementsSource fetchCustomElementsSource(
		long customElementsSourceId) {

		return customElementsSourcePersistence.fetchByPrimaryKey(
			customElementsSourceId);
	}

	/**
	 * Returns the custom elements source with the matching UUID and company.
	 *
	 * @param uuid the custom elements source's UUID
	 * @param companyId the primary key of the company
	 * @return the matching custom elements source, or <code>null</code> if a matching custom elements source could not be found
	 */
	@Override
	public CustomElementsSource fetchCustomElementsSourceByUuidAndCompanyId(
		String uuid, long companyId) {

		return customElementsSourcePersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the custom elements source with the primary key.
	 *
	 * @param customElementsSourceId the primary key of the custom elements source
	 * @return the custom elements source
	 * @throws PortalException if a custom elements source with the primary key could not be found
	 */
	@Override
	public CustomElementsSource getCustomElementsSource(
			long customElementsSourceId)
		throws PortalException {

		return customElementsSourcePersistence.findByPrimaryKey(
			customElementsSourceId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			customElementsSourceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CustomElementsSource.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"customElementsSourceId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			customElementsSourceLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CustomElementsSource.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"customElementsSourceId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			customElementsSourceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CustomElementsSource.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"customElementsSourceId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CustomElementsSource>() {

				@Override
				public void performAction(
						CustomElementsSource customElementsSource)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, customElementsSource);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CustomElementsSource.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return customElementsSourcePersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return customElementsSourceLocalService.deleteCustomElementsSource(
			(CustomElementsSource)persistedModel);
	}

	@Override
	public BasePersistence<CustomElementsSource> getBasePersistence() {
		return customElementsSourcePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return customElementsSourcePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the custom elements source with the matching UUID and company.
	 *
	 * @param uuid the custom elements source's UUID
	 * @param companyId the primary key of the company
	 * @return the matching custom elements source
	 * @throws PortalException if a matching custom elements source could not be found
	 */
	@Override
	public CustomElementsSource getCustomElementsSourceByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return customElementsSourcePersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the custom elements sources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.custom.elements.model.impl.CustomElementsSourceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom elements sources
	 * @param end the upper bound of the range of custom elements sources (not inclusive)
	 * @return the range of custom elements sources
	 */
	@Override
	public List<CustomElementsSource> getCustomElementsSources(
		int start, int end) {

		return customElementsSourcePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of custom elements sources.
	 *
	 * @return the number of custom elements sources
	 */
	@Override
	public int getCustomElementsSourcesCount() {
		return customElementsSourcePersistence.countAll();
	}

	/**
	 * Updates the custom elements source in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CustomElementsSourceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param customElementsSource the custom elements source
	 * @return the custom elements source that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CustomElementsSource updateCustomElementsSource(
		CustomElementsSource customElementsSource) {

		return customElementsSourcePersistence.update(customElementsSource);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CustomElementsSourceLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		customElementsSourceLocalService =
			(CustomElementsSourceLocalService)aopProxy;

		_setLocalServiceUtilService(customElementsSourceLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CustomElementsSourceLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CustomElementsSource.class;
	}

	protected String getModelClassName() {
		return CustomElementsSource.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				customElementsSourcePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		CustomElementsSourceLocalService customElementsSourceLocalService) {

		try {
			Field field =
				CustomElementsSourceLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, customElementsSourceLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected CustomElementsSourceLocalService customElementsSourceLocalService;

	@Reference
	protected CustomElementsSourcePersistence customElementsSourcePersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}