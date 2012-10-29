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
package com.almuramc.bolt.lock.type;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.almuramc.bolt.lock.Lock;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The basic lock stored at a singular x, y, z
 */
public class BasicLock implements Lock {
	//Core lock attributes
	private final UUID worldIdentifier;
	private final int x, y, z;
	private String owner;
	private List<String> coowners;
	private List<String> users;
	//Hashcode
	private boolean isHashed = false;
	private int hashcode = 0;

	public BasicLock(String owner, List<String> coowners, List<String> users, UUID worldIdentifier, int x, int y, int z) {
		this.owner = owner;
		this.coowners = coowners;
		this.users = users;
		this.worldIdentifier = worldIdentifier;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String getOwner() {
		return owner;
	}

	@Override
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public List getCoOwners() {
		return coowners;
	}

	@Override
	public void setCoOwners(String... coowners) {
		this.coowners = Arrays.asList(coowners);
	}

	@Override
	public List<String> getUsers() {
		return users;
	}

	@Override
	public void setUsers(String... users) {
		this.users = Arrays.asList(users);
	}

	@Override
	public UUID getWorld() {
		return worldIdentifier;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BasicLock)) {
			return false;
		}
		final BasicLock other = (BasicLock) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(this.owner, other.owner)
				.append(this.coowners, other.coowners)
				.append(this.users, users)
				.append(this.worldIdentifier, other.worldIdentifier)
				.append(this.x, other.x)
				.append(this.y, other.y)
				.append(this.z, other.z)
				.isEquals();
	}

	@Override
	public int hashCode() {
		if (!isHashed) {
			hashcode = new HashCodeBuilder(7, 11).append(getX()).append(getY()).append(getZ()).toHashCode();
			isHashed = true;
		}
		return hashcode;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("owner", owner)
				.append("coowners", coowners)
				.append("users", users)
				.append("worlduuid", worldIdentifier)
				.append("x", x)
				.append("y", y)
				.append("z", z)
				.toString();
	}
}
