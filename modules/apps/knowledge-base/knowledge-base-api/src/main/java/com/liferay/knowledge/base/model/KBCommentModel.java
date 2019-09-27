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

package com.liferay.knowledge.base.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the KBComment service. Represents a row in the &quot;KBComment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.knowledge.base.model.impl.KBCommentModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.knowledge.base.model.impl.KBCommentImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBComment
 * @generated
 */
@ProviderType
public interface KBCommentModel
	extends AttachedModel, BaseModel<KBComment>, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kb comment model instance should use the {@link KBComment} interface instead.
	 */

	/**
	 * Returns the primary key of this kb comment.
	 *
	 * @return the primary key of this kb comment
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kb comment.
	 *
	 * @param primaryKey the primary key of this kb comment
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this kb comment.
	 *
	 * @return the mvcc version of this kb comment
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this kb comment.
	 *
	 * @param mvccVersion the mvcc version of this kb comment
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this kb comment.
	 *
	 * @return the uuid of this kb comment
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this kb comment.
	 *
	 * @param uuid the uuid of this kb comment
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the kb comment ID of this kb comment.
	 *
	 * @return the kb comment ID of this kb comment
	 */
	public long getKbCommentId();

	/**
	 * Sets the kb comment ID of this kb comment.
	 *
	 * @param kbCommentId the kb comment ID of this kb comment
	 */
	public void setKbCommentId(long kbCommentId);

	/**
	 * Returns the group ID of this kb comment.
	 *
	 * @return the group ID of this kb comment
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kb comment.
	 *
	 * @param groupId the group ID of this kb comment
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kb comment.
	 *
	 * @return the company ID of this kb comment
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kb comment.
	 *
	 * @param companyId the company ID of this kb comment
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kb comment.
	 *
	 * @return the user ID of this kb comment
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kb comment.
	 *
	 * @param userId the user ID of this kb comment
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kb comment.
	 *
	 * @return the user uuid of this kb comment
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kb comment.
	 *
	 * @param userUuid the user uuid of this kb comment
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kb comment.
	 *
	 * @return the user name of this kb comment
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kb comment.
	 *
	 * @param userName the user name of this kb comment
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kb comment.
	 *
	 * @return the create date of this kb comment
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kb comment.
	 *
	 * @param createDate the create date of this kb comment
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kb comment.
	 *
	 * @return the modified date of this kb comment
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kb comment.
	 *
	 * @param modifiedDate the modified date of this kb comment
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this kb comment.
	 *
	 * @return the fully qualified class name of this kb comment
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this kb comment.
	 *
	 * @return the class name ID of this kb comment
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this kb comment.
	 *
	 * @param classNameId the class name ID of this kb comment
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this kb comment.
	 *
	 * @return the class pk of this kb comment
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this kb comment.
	 *
	 * @param classPK the class pk of this kb comment
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the content of this kb comment.
	 *
	 * @return the content of this kb comment
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this kb comment.
	 *
	 * @param content the content of this kb comment
	 */
	public void setContent(String content);

	/**
	 * Returns the user rating of this kb comment.
	 *
	 * @return the user rating of this kb comment
	 */
	public int getUserRating();

	/**
	 * Sets the user rating of this kb comment.
	 *
	 * @param userRating the user rating of this kb comment
	 */
	public void setUserRating(int userRating);

	/**
	 * Returns the last publish date of this kb comment.
	 *
	 * @return the last publish date of this kb comment
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this kb comment.
	 *
	 * @param lastPublishDate the last publish date of this kb comment
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this kb comment.
	 *
	 * @return the status of this kb comment
	 */
	public int getStatus();

	/**
	 * Sets the status of this kb comment.
	 *
	 * @param status the status of this kb comment
	 */
	public void setStatus(int status);

}