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
package com.almuramc.bolt.storage;

import java.util.Collection;

import com.almuramc.bolt.lock.Lock;

/**
 * Blueprint for a storage backend to persist locks.
 */
public interface Storage {
	/**
	 * Initializes the storage backend.
	 */
	public void initialize();

	/**
	 * Adds a lock to the storage backend.
	 * @param lock The lock to add
	 * @return The storage backend so it can be chained
	 * @throws NullPointerException If the lock is null
	 */
	public Storage add(Lock lock);

	/**
	 * Removes a lock from the storage backend.
	 * @param lock The lock to remove
	 * @return The storage backend so it can be chained
	 * @throws NullPointerException If the lock is null
	 */
	public Storage remove(Lock lock);

	/**
	 * Grabs all the locks within the storage backend.
	 * @return The collection of locks in the storage backend
	 */
	public Collection<Lock> getAll();
}
