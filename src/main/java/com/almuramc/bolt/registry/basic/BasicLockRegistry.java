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
/*
 * This file is part of Aqualock.
 *
 * Copyright (c) 2012, AlmuraDev <http://www.almuramc.com/>
 * Aqualock is licensed under the Almura Development License.
 *
 * Aqualock is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * As an exception, all classes which do not reference GPL licensed code
 * are hereby licensed under the GNU Lesser Public License, as described
 * in Almura Development License.
 *
 * Aqualock is distributed in the hope that it will be useful,
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
package com.almuramc.bolt.registry.basic;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.almuramc.bolt.lock.Lock;
import com.almuramc.bolt.registry.Registry;

public class BasicLockRegistry implements Registry {
	private final HashSet<Lock> registry;

	public BasicLockRegistry() {
		registry = new HashSet<Lock>();
	}

	@Override
	public Registry addLock(Lock lock) {
		if (lock == null) {
			throw new NullPointerException("Trying to add a null lock to the registry!");
		}
		registry.add(lock);
		return this;
	}

	@Override
	public Registry addLocks(Collection<Lock> locks) {
		if (locks == null) {
			throw new NullPointerException("Trying to add a null collection of locks to the registry!");
		}
		registry.addAll(locks);
		return this;
	}

	@Override
	public Registry removeLock(Lock lock) {
		if (lock == null) {
			throw new NullPointerException("Trying to remove a null lock from the registry!");
		}
		registry.remove(lock);
		return this;
	}

	@Override
	public Registry removeLocks(Collection<Lock> locks) {
		if (locks == null) {
			throw new NullPointerException("Trying to remove a null collection of locks from the registry!");
		}
		registry.removeAll(locks);
		return this;
	}

	@Override
	public Set<Lock> getAll() {
		return registry;
	}
}