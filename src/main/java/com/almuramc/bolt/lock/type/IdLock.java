/*
 * This file is part of Bolt.
 *
 * Copyright (c) 2012, AlmuraDev <http://www.almuramc.com/>
 * Bolt is licensed under the Almura Development License.
 *
 * Bolt is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * As an exception, all classes which do not reference GPL licensed code
 * are hereby licensed under the GNU Lesser Public License, as described
 * in the Almura Development License.
 *
 * Bolt is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * the GNU Lesser Public License (for classes that fulfill the exception)
 * and the Almura Development License along with this program. If not, see
 * <http://www.gnu.org/licenses/> for the GNU General Public License and
 * the GNU Lesser Public License.
 */
package com.almuramc.bolt.lock.type;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The basic id-based lock.
 */
public class IdLock extends BasicLock {
	private int id;

	public IdLock(String owner, List<String> coowners, int x, int y, int z, int id) {
		super(owner, coowners, x, y, z);
		this.id = id;
	}

	public IdLock(String owner, int x, int y, int z, int id) {
		this(owner, null, x, y, z, id);
	}

	/**
	 * Gets the id that is used as a characteristic of this lock
	 * @return the id of the lock
	 */
	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof IdLock)) {
			return false;
		}
		final IdLock other = (IdLock) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(this.id, other.id)
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(super.toString())
				.append("id", id)
				.toString();
	}
}
