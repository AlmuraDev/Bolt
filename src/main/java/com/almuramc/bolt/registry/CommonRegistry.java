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
import java.util.Collections;

import com.almuramc.bolt.lock.Lock;
import com.almuramc.bolt.util.TInt21TripleObjectHashMap;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Simple registry of locks
 */
public class CommonRegistry implements Registry {
	private final TInt21TripleObjectHashMap<Lock> registry;
	//Hashcode
	private boolean isHashed = false;
	private int hashcode = 0;

	public CommonRegistry() {
		registry = new TInt21TripleObjectHashMap<Lock>();
	}

	@Override
	public Registry addLock(Lock lock) {
		if (lock == null) {
			throw new NullPointerException("Trying to add a null lock to the registry!");
		}
		registry.put(lock.getX(), lock.getY(), lock.getZ(), lock);
		return this;
	}

	@Override
	public Registry removeLock(int x, int y, int z) {
		registry.remove(x, y, z);
		return this;
	}

	@Override
	public Lock getLock(int x, int y, int z) {
		return registry.get(x, y, z);
	}

	@Override
	public Collection<Lock> getAll() {
		return Collections.unmodifiableCollection(registry.valueCollection());
	}

	protected Collection<Lock> getRawAll() {
		return registry.valueCollection();
	}

	@Override
	public boolean contains(Lock lock) {
		if (registry.containsValue(lock)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean contains(int x, int y, int z) {
		if (registry.containsKey(x, y, z)) {
			return true;
		}
		return false;
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
				.append(getRawAll().toString())
				.toString();
	}
}
