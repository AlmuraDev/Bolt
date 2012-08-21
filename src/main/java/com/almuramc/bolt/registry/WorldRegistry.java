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

import java.util.HashMap;
import java.util.UUID;

import com.almuramc.bolt.registry.CommonRegistry;

/**
 * Maps CommonRegistries per world.
 */
public class WorldRegistry {
	private static final HashMap<UUID, CommonRegistry> registries;

	static {
	   registries = new HashMap<UUID, CommonRegistry>();
	}

	/**
	 * Adds a world to the map with an empty CommonRegistry.
	 * @param worldIdentifier The UUID of the world
	 */
	public static void addWorld(UUID worldIdentifier) {
		addWorld(worldIdentifier, new CommonRegistry());
	}

	/**
	 * Adds a world to the map with a CommonRegistry.
	 * @param worldIdentifier The UUID of the world
	 * @param registry The CommonRegistry for this world
	 */
	public static void addWorld(UUID worldIdentifier, CommonRegistry registry) {
		if (registry == null) {
			throw new NullPointerException("Trying to give a null registry for a world!");
		}
		registries.put(worldIdentifier, registry);
	}

	/**
	 *
	 * @param worldIdentifier
	 */
	public static void removeWorld(UUID worldIdentifier) {
		registries.remove(worldIdentifier);
	}

	/**
	 * Gets the CommonRegistry for the world's UUID specified.
	 * @param worldIdentifier The UUID of the world
	 * @return The CommonRegistry for this world
	 */
	public static CommonRegistry getRegistry(UUID worldIdentifier) {
		return registries.get(worldIdentifier);
	}

	/**
	 *
	 * @param worldIdentifier
	 * @return
	 */
	public static boolean contains(UUID worldIdentifier) {
		if (registries.containsKey(worldIdentifier)) {
			return true;
		}

		return false;
	}
}
