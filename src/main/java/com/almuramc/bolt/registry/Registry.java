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
import java.util.UUID;

import com.almuramc.bolt.lock.Lock;
import com.almuramc.bolt.storage.Storage;

/**
 * The blueprint for an object representing a registry.
 */
public interface Registry {
	/**
	 * Called when the registry is initialized.
	 */
	public void onLoad(Storage storage);

	/**
	 * Adds a lock to the registry.
	 * @param lock The lock to add
	 * @return The registry so it can be chained
	 * @throws NullPointerException If the lock is null
	 */
	public Registry addLock(Lock lock);

	/**
	 * Removes a lock from the registry.
	 * @param worldIdentifier The uuid identifier of the world
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param z The z coordinate
	 * @return The registry so it can be chained
	 * @throws NullPointerException If the world identifier is null
	 */
	public Registry removeLock(UUID worldIdentifier, int x, int y, int z);

	/**
	 * Removes a lock from the registry
	 * @param lock The lock to remove
	 * @return The registry so it can be chained
	 * @throws NullPointerException If the lock is null
	 */
	public Registry removeLock(Lock lock);

	/**
	 * Gets a lock from the registry.
	 * @param worldIdentifier The uuid identifier of the world
	 * @param x The x coordinate in the world
	 * @param y The y coordinate in the world
	 * @param z The z coordinate in the world
	 * @return The lock object or null if no lock at this location
	 */
	public Lock getLock(UUID worldIdentifier, int x, int y, int z);

	/**
	 * Returns the Collection of locks this registry has stored. The Collection can be empty.
	 * @return The collection containing the locks
	 */
	public Collection<Lock> getAll();

	/**
	 * Returns if the registry contains the lock specified.
	 * @param lock The lock to determine if the registry contains
	 * @return True if contained, false if not
	 */
	public boolean contains(Lock lock);

	/**
	 * Returns if the registry contains a lock at x, y, z.
	 * @param worldIdentifier The uuid identifier of the world
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param z The z coordinate
	 * @return True if contained, false if not
	 */
	public boolean contains(UUID worldIdentifier, int x, int y, int z);
}

