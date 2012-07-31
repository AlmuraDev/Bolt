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
package com.almuramc.bolt.registry;

import java.util.Collection;

import com.almuramc.bolt.lock.Lock;

/**
 * The blueprint for an object representing a registry. Implementations are welcome to provide their
 * own characteristics,
 */
public interface Registry {
	/**
	 * Adds a lock to the registry.
	 * @param lock The lock to add
	 * @return The Registry so it can be chained
	 * @throws NullPointerException If the lock is null
	 */
	public Registry addLock(Lock lock);

	/**
	 * Adds a collection of locks to the registry.
	 * @param locks The locks to add
	 * @return The Registry so it can be chained
	 * @throws NullPointerException If the lock collection is null
	 */
	public Registry addLocks(Collection<Lock> locks);

	/**
	 * Removes a lock from the registry.
	 * @param lock The lock to remove
	 * @return The Registry so it can be chained
	 * @throws NullPointerException If the lock is null
	 */
	public Registry removeLock(Lock lock);

	/**
	 * Removes a collection of locks from the registry.
	 * @param locks The locks to remove
	 * @return The Registry so it can be chained
	 * @throws NullPointerException If lock collection is null
	 */
	public Registry removeLocks(Collection<Lock> locks);

	/**
	 * Returns the Collection of locks this registry has stored. The Collection can be empty.
	 * @return The Collection containing the locks
	 */
	public Collection<Lock> getAll();
}
