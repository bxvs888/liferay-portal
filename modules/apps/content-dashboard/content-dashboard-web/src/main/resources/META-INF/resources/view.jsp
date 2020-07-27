<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
ContentDashboardAdminDisplayContext contentDashboardAdminDisplayContext = (ContentDashboardAdminDisplayContext)request.getAttribute(ContentDashboardWebKeys.CONTENT_DASHBOARD_ADMIN_DISPLAY_CONTEXT);

ContentDashboardAdminManagementToolbarDisplayContext contentDashboardAdminManagementToolbarDisplayContext = (ContentDashboardAdminManagementToolbarDisplayContext)request.getAttribute(ContentDashboardWebKeys.CONTENT_DASHBOARD_ADMIN_MANAGEMENT_TOOLBAR_DISPLAY_CONTEXT);
%>

<clay:row
	cssClass="no-gutters sidebar-wrapper"
>
	<clay:container-fluid>
		<div class="main-content-body">
			<clay:sheet
				size="<%= StringPool.BLANK %>"
			>
				<h2 class="sheet-title">
					<%= contentDashboardAdminDisplayContext.getAuditGraphTitle() %>
				</h2>

				<div class="audit-graph">
					<div class="c-my-5 c-p-5 inline-item w-100">
						<span aria-hidden="true" class="loading-animation"></span>
					</div>

					<react:component
						module="js/AuditGraphApp"
						props="<%= contentDashboardAdminDisplayContext.getData() %>"
					/>
				</div>
			</clay:sheet>
		</div>

		<div class="main-content-body">
			<clay:sheet
				size="<%= StringPool.BLANK %>"
			>
				<h2 class="sheet-title">
					<%= LanguageUtil.format(request, "content-x", contentDashboardAdminDisplayContext.getSearchContainer().getTotal(), false) %>
				</h2>

				<clay:management-toolbar
					displayContext="<%= contentDashboardAdminManagementToolbarDisplayContext %>"
					elementClasses="content-dashboard-management-toolbar"
				/>

				<div class="sheet-section">
					<liferay-ui:search-container
						id="content"
						searchContainer="<%= contentDashboardAdminDisplayContext.getSearchContainer() %>"
					>
						<liferay-ui:search-container-row
							className="com.liferay.content.dashboard.web.internal.item.ContentDashboardItem"
							keyProperty="id"
							modelVar="contentDashboardItem"
						>
							<liferay-ui:search-container-column-text
								cssClass="table-cell-expand table-title"
								name="title"
							>
								<span class="lfr-portal-tooltip text-truncate-inline" title="<%= HtmlUtil.escape(contentDashboardItem.getTitle(locale)) %>">
									<span class="text-truncate"><%= HtmlUtil.escape(contentDashboardItem.getTitle(locale)) %></span>
								</span>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								cssClass="text-center"
								name=""
							>
								<c:if test="<%= contentDashboardItem.isViewURLEnabled(request) %>">
									<span class="lfr-portal-tooltip" title="<%= LanguageUtil.get(request, "this-content-has-a-display-page") %>">
										<clay:icon
											cssClass="text-secondary"
											symbol="page"
										/>
									</span>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								cssClass="text-center"
								name="author"
							>
								<span class="lfr-portal-tooltip" title="<%= HtmlUtil.escape(contentDashboardItem.getUserName()) %>">
									<liferay-ui:user-portrait
										userId="<%= contentDashboardItem.getUserId() %>"
									/>
								</span>
							</liferay-ui:search-container-column-text>

							<%
							ContentDashboardItemType contentDashboardItemType = contentDashboardItem.getContentDashboardItemType();
							%>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-expand-smaller"
								name="subtype"
								value="<%= HtmlUtil.escape(contentDashboardItemType.getLabel(locale)) %>"
							/>

							<liferay-ui:search-container-column-text
								name="site-or-asset-library"
								value="<%= HtmlUtil.escape(contentDashboardItem.getScopeName(locale)) %>"
							/>

							<liferay-ui:search-container-column-text
								cssClass="text-nowrap"
								name="status"
							>

								<%
								List<ContentDashboardItem.Version> versions = contentDashboardItem.getVersions(locale);

								for (ContentDashboardItem.Version version : versions) {
								%>

									<clay:label
										label="<%= StringUtil.toUpperCase(version.getLabel()) %>"
										style="<%= version.getStyle() %>"
									/>

								<%
								}
								%>

							</liferay-ui:search-container-column-text>

							<%
							for (AssetVocabulary assetVocabulary : contentDashboardAdminDisplayContext.getAssetVocabularies()) {
							%>

								<liferay-ui:search-container-column-text
									cssClass="table-cell-expand-smaller"
									name="<%= assetVocabulary.getTitle(locale) %>"
								>

									<%
									List<AssetCategory> assetCategories = (List<AssetCategory>)contentDashboardItem.getAssetCategories(assetVocabulary.getVocabularyId());

									if (assetCategories.size() >= 1) {
									%>

									<clay:label
										displayType="secondary"
										large="<%= true %>"
									>
										<clay:label-item-expand><%= assetCategories.get(0).getTitle(locale) %></clay:label-item-expand>
									</clay:label>

									<%} %>

									<%if (assetCategories.size() > 1) {
										assetCategories.remove(assetCategories.get(0));

										StringBuilder sb = new StringBuilder();
											for (AssetCategory assetCategory : assetCategories) {
												sb.append(
													assetCategory.getTitle(
														locale));
												sb.append("\n");
											} %>

										<span class="lfr-portal-tooltip" title="<%= sb %>">
											<clay:label
												aria-title="<%= sb %>"
												displayType="secondary"
												large="<%= true %>"
											>
												<span>...</span>
											</clay:label>
										</span>

									<%
									}
									%>

								</liferay-ui:search-container-column-text>

							<%
							}
							%>

							<liferay-ui:search-container-column-date
								name="modified-date"
								value="<%= contentDashboardItem.getModifiedDate() %>"
							/>

							<liferay-ui:search-container-column-text>

								<%
								Map<String, Object> additionalProps = HashMapBuilder.<String, Object>put(
									"namespace", liferayPortletResponse.getNamespace()
								).put(
									"sidebarContainerSelector", ".sidebar-container"
								).build();
								%>

								<clay:dropdown-actions
									additionalProps="<%= additionalProps %>"
									dropdownItems="<%= contentDashboardAdminDisplayContext.getDropdownItems(contentDashboardItem) %>"
									propsTransformer="js/transformers/ActionsComponentPropsTransformer"
								/>
							</liferay-ui:search-container-column-text>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							markupView="lexicon"
						/>
					</liferay-ui:search-container>
				</div>
			</clay:sheet>
		</div>
	</clay:container-fluid>
</clay:row>

<liferay-frontend:component
	componentId="<%= contentDashboardAdminManagementToolbarDisplayContext.getDefaultEventHandler() %>"
	module="js/ContentDashboardManagementToolbarDefaultEventHandler"
/>