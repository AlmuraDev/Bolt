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
package com.almuramc.bolt.registry.type;

import java.util.Collection;

import com.almuramc.bolt.lock.Lock;
import com.almuramc.bolt.registry.Registry;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The blueprint for an object representing a registry. Implementations are welcome to provide their
 * own characteristics,
 */
public abstract class LockRegistry extends Registry {
	//Hashcode
	private boolean isHashed = false;
	private int hashcode = 0;

	/**
	 * Adds a lock to the registry.
	 * @param lock The lock to add
	 * @return The Registry so it can be chained
	 * @throws NullPointerException If the lock is null
	 */
	public abstract LockRegistry addLock(Lock lock);

	/**
	 * Adds a collection of locks to the registry.
	 * @param locks The locks to add
	 * @return The Registry so it can be chained
	 * @throws NullPointerException If the lock collection is null
	 */
	public abstract LockRegistry addLocks(Collection<Lock> locks);

	/**
	 * Removes a lock from the registry.
	 * @param lock The lock to remove
	 * @return The Registry so it can be chained
	 * @throws NullPointerException If the lock is null
	 */
	public abstract LockRegistry removeLock(Lock lock);

	/**
	 * Removes a collection of locks from the registry.
	 * @param locks The locks to remove
	 * @return The Registry so it can be chained
	 * @throws NullPointerException If lock collection is null
	 */
	public abstract LockRegistry removeLocks(Collection<Lock> locks);

	/**
	 * Returns the Collection of locks this registry has stored. The Collection can be empty.
	 * @return The Collection containing the locks
	 */
	public abstract Collection<Lock> getAll();

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final LockRegistry other = (LockRegistry) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(this.getAll(), other.getAll())
				.isEquals();
	}

	/**
	 * Generates a unique hashcode for this object, used when comparing.
	 * @return the hashcode of this object.
	 */
	@Override
	public int hashCode() {
		if (!isHashed) {
			hashcode = new HashCodeBuilder(7, 11).append(getAll()).hashCode();
			isHashed = true;
		}
		return hashcode;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(getAll())
				.toString();
	}
}
