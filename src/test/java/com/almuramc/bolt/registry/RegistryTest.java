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

import java.util.HashSet;

import com.almuramc.bolt.lock.Lock;
import com.almuramc.bolt.lock.type.PointLock;
import com.almuramc.bolt.registry.type.basic.SimpleLockRegistry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistryTest {
	private SimpleLockRegistry registry;

	@Before
	public void initialize() {
		registry = new SimpleLockRegistry();
	}

	@Test
	public void testRegistryAdd() {
		HashSet<Lock> locks = new HashSet<Lock>();
		for (int i = 0; i < 2; i++) {
			locks.add(new PointLock("Locksmith", null, i, i, i));
		}

		registry.addLocks(locks);
		assertEquals(locks, registry.getAll());
	}

	@Test
	public void testRegistrySubtract() {
		HashSet<Lock> locks = new HashSet<Lock>();
		for (int i = 0; i < 2; i++) {
			locks.add(new PointLock("Locksmith", null, i, i, i));
		}

		registry.addLocks(locks);
		registry.removeLocks(locks);
		assertTrue(registry.getAll().isEmpty());
	}
}
