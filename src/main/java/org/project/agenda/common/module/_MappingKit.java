package org.project.agenda.common.module;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("agenda", "id", Agenda.class);
		arp.addMapping("collage", "id", Collage.class);
		arp.addMapping("competition", "id", Competition.class);
		arp.addMapping("enroll", "id", Enroll.class);
		arp.addMapping("file", "id", File.class);
		arp.addMapping("member", "id", Member.class);
		arp.addMapping("menu", "id", Menu.class);
		arp.addMapping("resource", "id", Resource.class);
		arp.addMapping("role", "id", Role.class);
		arp.addMapping("role_menu", "id", RoleMenu.class);
		arp.addMapping("subset", "id", Subset.class);
		arp.addMapping("team", "id", Team.class);
		arp.addMapping("user", "id", User.class);
		arp.addMapping("user_menu", "id", UserMenu.class);
		arp.addMapping("user_role", "id", UserRole.class);
		arp.addMapping("user_session", "id", UserSession.class);
	}
}

