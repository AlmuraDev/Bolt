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
package com.almuramc.bolt.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.almuramc.bolt.lock.Lock;
import com.almuramc.bolt.storage.sql.RegistryTable;
import com.alta189.simplesave.Configuration;
import com.alta189.simplesave.Database;
import com.alta189.simplesave.DatabaseFactory;
import com.alta189.simplesave.exceptions.ConnectionException;
import com.alta189.simplesave.exceptions.TableRegistrationException;
import com.alta189.simplesave.h2.H2Configuration;
import com.alta189.simplesave.mysql.MySQLConfiguration;
import com.alta189.simplesave.query.Query;
import com.alta189.simplesave.query.QueryType;
import com.alta189.simplesave.sqlite.SQLiteConfiguration;
import com.alta189.simplesave.sqlite.SQLiteConstants;

public class SqlStorage implements Storage {
	private final RegistryTable table = new RegistryTable();
	private Database db;
	private Configuration config;
	private String dbName, hostName, username, password;
	private int port;

	public SqlStorage(Configuration config, String dbName, String hostName, String username, String password, int port) {
		this.config = config;
		this.dbName = dbName;
		this.hostName = hostName;
		this.username = username;
		this.password = password;
		this.port = port;
	}


	@Override
	public void initialize() {
		if (config instanceof SQLiteConfiguration) {

		} else if (config instanceof H2Configuration) {

		} else {
			MySQLConfiguration mysql = (MySQLConfiguration) config;
			mysql
					.setDatabase(dbName)
					.setHost(hostName)
					.setUser(username)
					.setPassword(password)
					.setPort(port);
		}
		db = DatabaseFactory.createNewDatabase(config);

		try {
			db.registerTable(RegistryTable.class);
		} catch (TableRegistrationException e) {
			e.printStackTrace();
		}

		try {
			db.connect();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Storage addLock(Lock lock) {
		if (lock == null) {
			throw new NullPointerException("Trying to add a null lock to the storage backend!");
		}
		table.id = 1;
		table.lock = lock;
		db.save(RegistryTable.class, table);
		return this;
	}

	@Override
	public Storage removeLock(Lock lock) {
		if (lock == null) {
			throw new NullPointerException("Trying to remove a null lock to the storage backend!");
		}
		table.id = 1;
		table.lock = lock;
		db.remove(RegistryTable.class, table);
		return this;
	}

	@Override
	public Collection<Lock> getAll() {
		ArrayList<Lock> locks = new ArrayList<Lock>();
		for (Lock lock : db.select(Lock.class).execute().find()) {
			locks.add(lock);
		}
		return Collections.unmodifiableCollection(locks);
	}
}
