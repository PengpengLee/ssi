package cn.itcast.ssi.sysmgmt.module;

/**
 * <p>
 * 角色枚举类
 * </p>
 * 
 */
public enum Role {
	
	/**
	 * 普通用户
	 */
	NOMAL {
		@Override
		public String code() {
			return "20";
		}

		@Override
		public String getName() {
			return "普通用户";
		}
	},
	/**
	 * 管理员
	 */
	ADMIN {
		@Override
		public String code() {
			return "10";
		}
		
		@Override
		public String getName() {
			return "管理员";
		}
	},
	/**
	 * 权限管理员
	 */
	PERMISSION {
		@Override
		public String code() {
			return "5";
		}
		
		@Override
		public String getName() {
			return "权限管理员";
		}
	},
	/**
	 * 系统管理员
	 */
	SYSTEM {
		@Override
		public String code() {
			return "1";
		}
		
		@Override
		public String getName() {
			return "系统管理员";
		}
	};

	/**
	 * 获取数据库代号。
	 * 
	 * @return
	 */
	public abstract String code();

	/**
	 * 获取中文名字。
	 * 
	 * @return
	 */
	public abstract String getName();

}
