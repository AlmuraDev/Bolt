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

import java.util.UUID;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class WorldRegistryTest {
	UUID a = UUID.randomUUID();
	UUID b = UUID.randomUUID();
	CommonRegistry c = new CommonRegistry();

	@Test
	public void testWorldRegistryAdd() {
		WorldRegistry.addWorld(a, c);
		WorldRegistry.addWorld(b, c);
		assertThat(WorldRegistry.getRegistry(a), equalTo(WorldRegistry.getRegistry(b)));
	}

	@Test
	public void testWorldRegistryRemove() {
		WorldRegistry.removeWorld(b);
		assertNull(WorldRegistry.getRegistry(b));
	}
}
