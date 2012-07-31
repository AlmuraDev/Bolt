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

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The blueprint for an object representing a lock. Implementations are welcome to provide their
 * own characteristics.
 */
public class Lock implements Serializable {
	//Core lock attributes
	private final int x, y, z;
	private String owner;
	private List<String> coowners;
	//Hashcode
	private boolean isHashed = false;
	private int hashcode = 0;

	public Lock(String owner, List<String> coowners, int x, int y, int z) {
		this.owner = owner;
		this.coowners = coowners;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Gets the owner of this lock
	 * @return The name of the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of this lock.
	 * @param owner The name of the new owner of this lock.
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Gets the Co-Owners of this lock. A null value indicates
	 * that the lock is available to everyone.
	 * @return List of names representing co-owners or null for everyone
	 */
	public List getCoOwners() {
		return coowners;
	}

	/**
	 * Sets the Co-Owners of this lock. A null value indicates
	 * that the lock will be available to everyone.
	 * @param coowners List of names representing co-owners
	 */
	public void setCoOwners(List<String> coowners) {
		this.coowners = coowners;
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

	/**
	 * Returns whether one object is equal to another.
	 * @param obj the object that will be compared for equality
	 * @return True if equals, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Lock)) {
			return false;
		}
		final Lock other = (Lock) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(this.owner, other.owner)
				.append(this.coowners, other.coowners)
				.append(this.x, other.x)
				.append(this.y, other.y)
				.append(this.z, other.z)
				.isEquals();
	}

	/**
	 * Returns a string representation of this object.
	 * @return String object representing detailed information of this object
	 */
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
}
