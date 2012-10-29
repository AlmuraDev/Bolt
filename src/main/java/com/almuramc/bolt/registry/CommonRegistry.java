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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import com.almuramc.bolt.lock.Lock;
import com.almuramc.bolt.util.TInt21TripleObjectHashMap;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Simple registry of locks per world.
 */
public abstract class CommonRegistry implements Registry {
	private final HashMap<UUID, TInt21TripleObjectHashMap<Lock>> registry;
	//Hashcode
	private boolean isHashed = false;
	private int hashcode = 0;

	public CommonRegistry() {
		registry = new HashMap<UUID, TInt21TripleObjectHashMap<Lock>>();
	}

	@Override
	public Registry addLock(Lock lock) {
		if (lock == null) {
			throw new NullPointerException("Trying to add a null lock to the registry!");
		}
		if (!registry.containsKey(lock.getWorld())) {
			registry.put(lock.getWorld(), new TInt21TripleObjectHashMap<Lock>());
		}
		registry.get(lock.getWorld()).put(lock.getX(), lock.getY(), lock.getZ(), lock);
		return this;
	}

	@Override
	public Registry removeLock(UUID worldIdentifier, int x, int y, int z) {
		if (worldIdentifier == null) {
			throw new NullPointerException("World identifier passed in is null!");
		}
		if (registry.containsKey(worldIdentifier)) {
			registry.get(worldIdentifier).remove(x, y, z);
		}
		return this;
	}

	@Override
	public Registry removeLock(Lock lock) {
		if (lock == null) {
			throw new NullPointerException("Trying to remove a null lock from the registry!");
		}
		return removeLock(lock.getWorld(), lock.getX(), lock.getY(), lock.getZ());
	}

	@Override
	public Lock getLock(UUID worldIdentifier, int x, int y, int z) {
		if (worldIdentifier == null) {
			throw new NullPointerException("World identifier passed in is null!");
		}
		return registry.get(worldIdentifier) != null ? registry.get(worldIdentifier).get(x, y, z) : null;
	}

	@Override
	public Collection<Lock> getAll() {
		ArrayList<Lock> locks = new ArrayList<Lock>();
		for (TInt21TripleObjectHashMap value : registry.values()) {
			locks.addAll(value.valueCollection());
		}
		return Collections.unmodifiableCollection(locks);
	}

	protected Collection<Lock> getRawAll() {
		ArrayList<Lock> locks = new ArrayList<Lock>();
		for (TInt21TripleObjectHashMap value : registry.values()) {
			locks.addAll(value.valueCollection());
		}
		return locks;
	}

	@Override
	public boolean contains(Lock lock) {
		if (lock == null) {
			throw new NullPointerException("Lock passed in is null!");
		}
		return registry.containsKey(lock.getWorld()) && registry.get(lock.getWorld()).containsValue(lock);
	}

	@Override
	public boolean contains(UUID worldIdentifier, int x, int y, int z) {
		if (worldIdentifier == null) {
			throw new NullPointerException("World identifier passed in is null!");
		}
		return registry.containsKey(worldIdentifier) && registry.get(worldIdentifier).containsKey(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CommonRegistry)) {
			return false;
		}
		final CommonRegistry other = (CommonRegistry) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(this.getRawAll(), other.getRawAll())
				.isEquals();
	}

	/**
	 * Generates a unique hashcode for this object, used when comparing.
	 * @return The hashcode of this object.
	 */
	@Override
	public int hashCode() {
		if (!isHashed) {
			hashcode = new HashCodeBuilder(7, 11).append(getRawAll()).hashCode();
			isHashed = true;
		}
		return hashcode;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(getRawAll().toString())
				.toString();
	}
}
