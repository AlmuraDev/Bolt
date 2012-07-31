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
package com.almuramc.bolt.lock;

import com.almuramc.bolt.lock.type.single.MaterialPointLock;
import com.almuramc.bolt.lock.type.PointLock;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LockTest {
	@Test
	public void lockEqualsTest() {
		PointLock a = new PointLock("Locksmith", null, 1, 1, 1);
		PointLock b = new PointLock("Locksmith", null, 1, 1, 1);
		assertEquals(a, b);

		MaterialPointLock c = new MaterialPointLock("Locksmith", null, 1, 1, 1, 1);
		MaterialPointLock d = new MaterialPointLock("Locksmith", null, 1, 1, 1, 1);
		assertEquals(c, d);
	}
}
