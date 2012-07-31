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

import java.util.List;

import com.almuramc.bolt.lock.Lock;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A basic lock stored at a singular x, y, z
 */
public class PointLock extends Lock {
	//Core lock attributes
	private final int x, y, z;
	private String owner;
	private List<String> coowners;
	//Hashcode
	private boolean isHashed = false;
	private int hashcode = 0;

	public PointLock(String owner, List<String> coowners, int x, int y, int z) {
		this.owner = owner;
		this.coowners = coowners;
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
	public void setCoOwners(List<String> coowners) {
		this.coowners = coowners;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PointLock)) {
			return false;
		}
		final PointLock other = (PointLock) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(this.owner, other.owner)
				.append(this.coowners, other.coowners)
				.append(this.x, other.x)
				.append(this.y, other.y)
				.append(this.z, other.z)
				.isEquals();
	}

	/**
	 * Generates a unique hashcode for this object, used when comparing.
	 * @return the hashcode of this object.
	 */
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
				.append("x", x)
				.append("y", y)
				.append("z", z)
				.toString();
	}

	/**
	 * Gets the x coordinate of the position of this lock.
	 * @return The x coordinate of this lock's position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y coordinate of the position of this lock.
	 * @return The y coordinate of this lock's position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets the z coordinate of the position of this lock.
	 * @return The z coordinate of this lock's position
	 */
	public int getZ() {
		return z;
	}
}
