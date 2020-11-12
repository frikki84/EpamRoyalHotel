package by.epamtc.jwd2020.dziadkouskaya.dao;

import by.epamtc.jwd2020.dziadkouskaya.bean.Role;
/**
 * interface work with users roles from magichotel.roles
 * @author Yana Dziadkouskaya
 *
 */

public interface RoleDao {
/**
 * create new role in magichotel.roles
 * @param role new Role
 */
	public void createRole(Role role);
}
