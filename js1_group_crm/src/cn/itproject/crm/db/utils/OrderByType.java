package cn.itproject.crm.db.utils;

public enum OrderByType {
	ASC
	{
		@Override
		public String getValue() {
			return "asc";
		}
	},	DESC
	{
		@Override
		public String getValue() {
			return "desc";
		}
	};
	public abstract String getValue();
}
