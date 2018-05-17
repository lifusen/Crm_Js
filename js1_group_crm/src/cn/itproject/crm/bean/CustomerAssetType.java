package cn.itproject.crm.bean;

/**
 * 客户资产类型
 * @author MrLiu
 *
 */
public enum CustomerAssetType {
	/**0:房屋**/
	house{
		@Override
		public String getValue() {
			return "住房";
		}
	},	
	/**1:商铺**/
	store{
		@Override
		public String getValue() {
			return "商铺";
		}
	},
	/**2:写字楼**/
	office{
		@Override
		public String getValue() {
			return "写字楼";
		}
	},	
	/**3:土地**/
	land{
		@Override
		public String getValue() {
			return "土地";
		}
	},	
	/**4:汽车**/
	car{
		@Override
		public String getValue() {
			return "汽车";
		}
	},
	/**5:企业**/
	enterprise{
		@Override
		public String getValue() {
			return "企业";
		}
	},	
	/**6:信用卡**/
	creditCard{
		@Override
		public String getValue() {
			return "信用卡";
		}
	};	
	
	public abstract String getValue();
}
