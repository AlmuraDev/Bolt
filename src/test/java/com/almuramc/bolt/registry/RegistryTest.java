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

import com.almuramc.bolt.lock.type.BasicLock;
import com.almuramc.bolt.lock.type.IdLock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RegistryTest {
	private CommonRegistry registry;

	@Before
	public void initialize() {
		registry = new CommonRegistry();
	}

	@Test
	public void testRegistryAdd() {
		IdLock a = new IdLock("LockSmith", null, 1, 1, 1, 129);
		IdLock b = new IdLock("LockSmith", null, 1, 2, 1, 129);
		registry.addLock(a);
		registry.addLock(b);
		assertTrue(registry.getLock(1, 1, 1).equals(a));
		assertTrue(registry.getLock(1, 2, 1).equals(b));
		assertNull(registry.getLock(1, 3, 1));
	}

	@Test
	public void testRegistrySubtract() {
		for (int i = 0; i < 20000; i++) {
			registry.addLock(new BasicLock("Locksmith", null, i, i, i));
		}
		registry.removeLock(6543, 34, 7777);
		assertTrue(registry.getLock(6543, 34, 7777) == null);
		assertTrue(registry.getLock(20000, 20000, 20000) == null);
		assertTrue(registry.getLock(1, 1, 1) != null);
	}
}
